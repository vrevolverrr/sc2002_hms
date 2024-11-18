package view.Doctor.appointments;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
import model.appointments.AppointmentOutcomeRecord;
import model.enums.DosageUnit;
import model.enums.MedicalService;
import model.enums.MedicineFrequency;
import model.inventory.InventoryItem;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentDetailsTable;
import view.Doctor.appointments.widgets.AppointmentUpdateOutcomeTable;
import view.Doctor.appointments.widgets.PatientDetailsTable;
import view.widgets.Title;
import model.prescriptions.MedicineDosage;
import model.prescriptions.Prescription;
import model.users.Doctor;
import model.users.Patient;
import services.Navigator;
import utils.InputValidators;
import services.ServiceLocator;
import controller.interfaces.IUserManager;
import controller.interfaces.IMedicalRecordManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IAppointmentManager;

/**
 * {@link DoctorUpdateOutcomeDetailsView} is a {@link View} that allows doctors to update
 * the outcomes of fulfilled appointments.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorUpdateOutcomeDetailsView extends View {
    /**
     * An instance of {@link UserManager} used to manage users.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * An instance of {@link MedicalRecordManager} used to manage medical records.
     */
    private final IMedicalRecordManager recordManager = ServiceLocator.getService(IMedicalRecordManager.class);

    /**
     * An instance of {@link InventoryManager} used to manage inventory items.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);

    /**
     * An instance of {@link AppointmentManager} used to manage appointments.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The {@link Appointment} for which the outcome is being updated.
     */
    private final Appointment appointment;

    /**
     * The {@link Patient} associated with the appointment.
     */
    private final Patient patient;

    /**
     * The {@link Doctor} associated with the appointment.
     */
    private final Doctor doctor;

    /**
     * The consultation notes recorded during the appointment.
     */
    private String consultationNotes = null;

    /**
     * A list of {@link Prescription} objects representing the prescriptions issued during the appointment.
     */
    private List<Prescription> prescriptions = new ArrayList<Prescription>();

    /**
     * A list of {@link MedicalService} objects representing the medical services provided during the appointment.
     */
    private List<MedicalService> services = new ArrayList<MedicalService>();

    /**
     * A flag indicating whether the prescription have been recorded.
     */
    private boolean donePrescriptions = false;

    /**
     * A flag indicating whether the services have been done.
     */
    private boolean doneServices = false;

    /**
     * Constructs a new {@link DoctorUpdateOutcomeDetailsView} for a given appointment.
     *
     * @param appointment the {@link Appointment} for which the outcome is being updated.
     */
    public DoctorUpdateOutcomeDetailsView(Appointment appointment) {
        this.appointment = appointment;
        this.patient = (Patient) userManager.getUser(appointment.getPatientId());
        this.doctor = (Doctor) userManager.getUser(appointment.getDoctorId());
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Update Appointment Outcome".
     */
    @Override
    public String getViewName() {
        return "Update Appointment Outcome";
    }

    /**
     * Renders the "Update Appointment Outcome" view for the doctor.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Appointment Outcome").paint(context);
        
        new VSpacer(1).paint(context);

        new Title("Patient Details").paint(context);
        new PatientDetailsTable(patient).paint(context);

        new VSpacer(1).paint(context);

        new Title("Appointment Details").paint(context);
        new DoctorAppointmentDetailsTable(appointment).paint(context);

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

        AppointmentOutcomeRecord outcomeRecord =
             appointmentManager.updateAppointmentOutcome(appointment, consultationNotes, prescriptions, services);
        
        recordManager.createMedicalRecordFromOutcome(patient, doctor, outcomeRecord);
        
        new Pause("Completed. Press any key to go back.").pause(context);
        Navigator.pop();
    }

    /**
     * Prompts the doctor to enter consultation notes for the appointment.
     */
    private void promptConsultationNotes() {
        TextInputField consultationNotesField = new TextInputField("Consultation Notes");
        new TextInput(consultationNotesField).read(context, "Enter non-empty consultation notes.", input -> !input.isBlank());

        this.consultationNotes = consultationNotesField.getValue();
    }

    /**
     * Prompts the doctor to enter prescriptions for the appointment.
     */
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

    /**
     * Prompts the doctor to enter dosage details for a given drug.
     *
     * @param drug the {@link InventoryItem} representing the drug to prescribe.
     * @return a {@link Prescription} object representing the prescription.
     */
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

    /**
     * Prompts the doctor to select the medical service provided during the appointment.
     */
    private void promptServices() {
        final MedicalService[] serviceToAdd = {null};

        new Text(String.format("Select the services provided (%d for Done):", MedicalService.values().length + 1), 
            TextStyle.BOLD).paint(context);
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
