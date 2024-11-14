package view.Doctor.appointments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.AppointmentManager;
import controller.InventoryManager;
import controller.PatientManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.enums.DosageUnit;
import model.enums.MedicalService;
import model.enums.MedicineFrequency;
import model.inventory.InventoryItem;
import view.View;
import view.Doctor.appointments.widgets.AppointmentDetailsTable;
import view.Doctor.appointments.widgets.AppointmentUpdateOutcomeTable;
import view.Doctor.appointments.widgets.PatientDetailsTable;
import view.widgets.Title;
import model.prescriptions.MedicineDosage;
import model.prescriptions.Prescription;
import services.Navigator;
import utils.InputValidators;

public class DoctorUpdateOutcomeDetailsView extends View {
    private final PatientManager patientManager = PatientManager.getInstance(PatientManager.class);
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    private final Appointment appointment;

    private String consultationNotes = null;
    private List<Prescription> prescriptions = new ArrayList<Prescription>();
    private List<MedicalService> services = new ArrayList<MedicalService>();

    private boolean donePrescriptions = false;
    private boolean doneServices = false;

    public DoctorUpdateOutcomeDetailsView(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String getViewName() {
        return "Update Appointment Outcome";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Appointment Outcome").paint(context);
        
        new VSpacer(1).paint(context);

        new Title("Patient Details").paint(context);
        new PatientDetailsTable(patientManager.getPatient(appointment.getPatientId())).paint(context);

        new VSpacer(1).paint(context);

        new Title("Appointment Details").paint(context);
        new AppointmentDetailsTable(appointment).paint(context);

        new VSpacer(1).paint(context);

        new Title("Appointment Outcome").paint(context);
        new AppointmentUpdateOutcomeTable(consultationNotes, prescriptions, services).paint(context);

        new VSpacer(1).paint(context);
        
        if (consultationNotes == null) {
            promptConsultationNotes();
            repaint();
        }

        if (!donePrescriptions) {
            promptPrescriptions();
            repaint();
        }

        if (!doneServices) {
            promptServices();
            repaint();
        }        

        appointmentManager.updateAppointmentOutcome(appointment, consultationNotes, prescriptions, services);
        new Pause("Completed. Press any key to go back.").pause(context);
        Navigator.pop();
    }

    private void promptConsultationNotes() {
        TextInputField consultationNotesField = new TextInputField("Consultation Notes");
        new TextInput(consultationNotesField).read(context, "Enter non-empty consultation notes.", input -> !input.isBlank());

        this.consultationNotes = consultationNotesField.getValue();
    }

    private void promptPrescriptions() {
        final List<InventoryItem> drugList = inventoryManager.getAllItems();

        final Prescription[] prescription = {null};

        new Text(String.format("Select medicine to prescribe (%d for Done):", (drugList.size() + 1)), 
            TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        final List<MenuOption> drugOptions = 
                drugList.stream().map(drug ->
                        new MenuOption(drug.getItemName(),
                        () -> {
                            clearLines(drugList.size() + 5);
                            new Text("Selected Drug: " + drug.getItemName(), TextStyle.BOLD).paint(context);
                            prescription[0] = promptDosageDetails(drug);
                        }
                        ))
            .collect(Collectors.toCollection(ArrayList::new));
        
        drugOptions.add(new MenuOption("Done", () -> donePrescriptions = true));
        
        new Menu(
            drugOptions.toArray(MenuOption[]::new)
        ).readOption(context);

        if (prescription[0] != null) {
            this.prescriptions.add(prescription[0]);
        }
    }

    private Prescription promptDosageDetails(InventoryItem drug) {
        TextInputField[] dosageField = {null};
        final DosageUnit[] selectedUnit = {null};
        final MedicineFrequency[] selectedFreq = {null};

        final List<MenuOption> dosageOptions = 
            Stream.of(DosageUnit.values()).map(dosageOption ->
                new MenuOption(dosageOption.toString(), () -> {
                    selectedUnit[0] = dosageOption;

                    new VSpacer(1).paint(context);
                    dosageField[0] = new TextInputField(String.format("Enter dosage (%s)", dosageOption.toString()));
                    new TextInput(dosageField[0]).read(context, "Enter a valid dosage.", input -> InputValidators.validateRange(input, 99999));

                    clearLines(DosageUnit.values().length + 7);
                    new Text("Selected Dosage: " + dosageField[0].getInt() + " " + dosageOption.toString(), TextStyle.BOLD).paint(context);

                    new VSpacer(1).paint(context);
                    new Text("Enter frequency:", TextStyle.BOLD).paint(context);
                    new VSpacer(1).paint(context);

                    new Menu(
                        Stream.of(MedicineFrequency.values()).map(freq -> 
                            new MenuOption(freq.toString(), () -> {
                                selectedFreq[0] = freq;
                                clearLines(MedicineFrequency.values().length + 5);
                                new Text("Selected Frequency: "+ freq.toString(), TextStyle.BOLD).paint(context);
                            })

                        ).toArray(MenuOption[]::new)
                    ).readOption(context); 
                })
            ).toList();

        new VSpacer(1).paint(context);
        new Text("Select dosage unit:", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);
        new Menu(dosageOptions.toArray(MenuOption[]::new)).readOption(context);
        
        new VSpacer(1).paint(context);
        TextInputField prescribedQtyField = new TextInputField("Enter quantity to prescribe");
        new TextInput(prescribedQtyField).read(context, "Enter a valid item quantity.",
            (input) -> InputValidators.validateQuantity(input));

        MedicineDosage dosage = new MedicineDosage(dosageField[0].getInt(), selectedUnit[0]);
        return new Prescription(drug.getId(), prescribedQtyField.getInt(), dosage, selectedFreq[0]);
    }

    private void promptServices() {
        final MedicalService[] serviceToAdd = {null};

        new Text("Select the services provided:", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        final List<MenuOption> serviceOptions = 
                Stream.of(MedicalService.values()).map(service ->
                        new MenuOption(service.toString(), () -> 
                        serviceToAdd[0] = service
                )).collect(Collectors.toCollection(ArrayList::new));
        
        serviceOptions.add(new MenuOption("Done", () -> doneServices = true));
        
        new Menu(
            serviceOptions.toArray(MenuOption[]::new)
        ).readOption(context);

        if (serviceToAdd[0] != null && !this.services.contains(serviceToAdd[0])) {
            this.services.add(serviceToAdd[0]);
        }
    }
}
