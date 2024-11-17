package view.Doctor.records;

import java.util.List;

import controller.PatientManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.users.Doctor;
import model.users.Patient;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Doctor.records.widget.DoctorPatientCareTable;
import view.widgets.Title;

/**
 * {@link DoctorPatientsUnderCareView} is a {@link View} that allows doctors to view the patients under their care.
 * Doctors can select a patient to view and manage their medical records.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorPatientsUnderCareView extends View {
    /**
     * An instance of {@link PatientManager} used to manage patients.
     */
    private final PatientManager patientManager = PatientManager.getInstance(PatientManager.class);

    /**
     * The {@link List} of {@link Patient} under the doctor's care.
     */
    private final List<Patient> patientsUnderCare;

    /**
     * Constructs a new {@link DoctorPatientsUnderCareView} for the given doctor.
     *
     * @param doctor the {@link Doctor} whose patients are being viewed.
     */
    public DoctorPatientsUnderCareView(Doctor doctor) {
        patientsUnderCare = patientManager.getPatientsUnderDoctorCare(doctor);
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Patient Medical Records".
     */
    @Override
    public String getViewName() {
        return "Patient Medical Record";
    }

    /**
     * Renders the doctor view for managing patient medical records.
     * <p>
     * This method sets up the following components in the user interface:
     * <ul>
     *   <li>Breadcrumb navigation to indicate the current view context.</li>
     *   <li>A title widget displaying "Patient Medical Records".</li>
     *   <li>A title widget displaying "Patients Under Care".</li>
     *   <li>A table showing a list of patients under the doctor's care, created by {@link DoctorPatientCareTable}.</li>
     *   <li>A text input field allowing the doctor to choose a patient from the list to manage medical records. This uses {@link TextInputField} and validates the selected patient index.</li>
     *   <li>Upon selecting a patient, it navigates to the {@link DoctorPatientMedicalRecordView} to manage the selected patient's medical records.</li>
     * </ul>
     * </p>
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Records").paint(context);
        new VSpacer(1).paint(context);

        new Title("Patients Under Care").paint(context);
        new DoctorPatientCareTable(patientsUnderCare).paint(context);
        new VSpacer(1).paint(context);

        TextInputField patientField = new TextInputField("Choose patient to manage medical records");
        new TextInput(patientField).read(context, "Choose a patient from the list.",
            input -> InputValidators.validateRange(input, patientsUnderCare.size()));

        Patient selectedPatient = patientsUnderCare.get(patientField.getOption());

        Navigator.navigateTo(new DoctorPatientMedicalRecordView(selectedPatient));
    }
    
}
