package view.Pharmacist.prescription;

import java.util.List;
import controller.InventoryManager;
import controller.PharmacistManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentDetailsTable;
import view.Doctor.appointments.widgets.PrescriptionsTable;
import view.widgets.Title;

public class PharmacistPrescribeMedicineView extends View {
    private final PharmacistManager pharmacistManager = PharmacistManager.getInstance(PharmacistManager.class);
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final Appointment appointment;

    public PharmacistPrescribeMedicineView(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getViewName() {
       return "Prescribe Medicine";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Prescribe Medicine").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Appointment Details").paint(context);
        new DoctorAppointmentDetailsTable(appointment).paint(context);
        new VSpacer(1).paint(context);

        final List<Prescription> prescriptions = appointment.getOutcomeRecord().getPrescriptions();

        new Title("Dispensed Prescriptions").paint(context);
        
        new PrescriptionsTable(
            prescriptions.stream().filter(
                prescription -> prescription.getStatus() == PrescriptionStatus.DISPENSED).toList()
        ).paint(context);
        
        new VSpacer(1).paint(context);

        new Title("Undispensed Prescriptions").paint(context);
        final List<Prescription> undispensedPrescriptions = prescriptions.stream()
            .filter(prescription -> prescription.getStatus() == PrescriptionStatus.PENDING)
            .toList();
        
        new PrescriptionsTable(undispensedPrescriptions).paint(context);
        
        new VSpacer(1).paint(context);

        if (prescriptions.stream().allMatch(prescription -> prescription.getStatus() == PrescriptionStatus.DISPENSED)) {
            new Pause("All prescriptions has been dispensed. Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        TextInputField selectField = new TextInputField("Select a prescription to dispense medicine");
        new TextInput(selectField).read(context, "Select an undispensed prescription from the list above.",
            (input) ->
                InputValidators.validateRange(input, undispensedPrescriptions.size())
        );

        final Prescription selectedPrescription = undispensedPrescriptions.get(selectField.getOption());

        new VSpacer(1).paint(context);

        TextInputField confirmField = new TextInputField(
            String.format("Confirm dispense of %dx %s (Y/N)", 
                selectedPrescription.getQuantity(), getDrugNameById(selectedPrescription.getDrugId())));
        new TextInput(confirmField).read(context, "Y to Confirm. N to Cancel.",
            (input) -> InputValidators.validateYesNo(input));

        if (!confirmField.getYesNo()) {
            new Pause("Dispense cancelled. Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }
        
        pharmacistManager.dispensePrescription(appointment, selectedPrescription);
        repaint();
    }

    public String getDrugNameById(String id) {
        return inventoryManager.getItem(id).getItemName();
    }

}