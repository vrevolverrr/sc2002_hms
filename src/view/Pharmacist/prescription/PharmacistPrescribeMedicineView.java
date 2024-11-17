package view.Pharmacist.prescription;

import java.util.List;
import controller.InventoryManager;
import controller.PharmacistManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IPharmacistManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentDetailsTable;
import view.Doctor.appointments.widgets.PrescriptionsTable;
import view.widgets.Title;

/**
 * This view allows the pharmacist to prescribe medicine for a selected appointment.
 * It displays the appointment details and the list of prescriptions.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public final class PharmacistPrescribeMedicineView extends View {
    /**
     * An instance of the {@link PharmacistManager} class. Used to manage prescriptions.
     */
    private final IPharmacistManager pharmacistManager = ServiceLocator.getService(PharmacistManager.class);
    
    /**
     * An instance of the {@link InventoryManager} class. Used to retrieve inventory items.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(InventoryManager.class);
    
    /**
     * The appointment for which the medicine is being prescribed.
     */
    private final Appointment appointment;

    /**
     * Constructor for the view.
     * @param appointment the appointment for which the medicine is being prescribed.
     */
    public PharmacistPrescribeMedicineView(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
       return "Prescribe Medicine";
    }

    /**
     * Renders the view.
     */
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

    /**
     * Gets the name of the drug by its ID.
     * @param id the ID of the drug.
     * @return the name of the drug.
     */
    public String getDrugNameById(String id) {
        return inventoryManager.getItem(id).getItemName();
    }

}