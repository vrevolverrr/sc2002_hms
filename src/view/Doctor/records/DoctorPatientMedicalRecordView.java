package view.Doctor.records;

import java.util.List;

import controller.interfaces.IMedicalRecordManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.medrecord.MedicalRecordEntry;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.records.widget.DoctorMedicalRecordsTable;
import view.Patient.records.widgets.PatientMedicalDetailsTable;
import view.widgets.Title;

/**
 * The {@link DoctorPatientMedicalRecordView} class is a view for doctors to view the medical record of a patient.
 * The view displays the patient's details and their medical record, and allows the doctor to update the medical record.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorPatientMedicalRecordView extends View {
    /**
     * An instance of {@link MedicalRecordManager} used to manage medical records.
     */
    private final IMedicalRecordManager medicalRecordManager = ServiceLocator.getService(IMedicalRecordManager.class);

    /**
     * The {@link Patient} whose medical record is being viewed.
     */
    private final Patient patient;

    /**
     * The {@link BuildContext} used to render the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(110);

    /**
     * Constructs a new {@link DoctorPatientMedicalRecordView} for the given patient.
     *
     * @param patient the {@link Patient} whose medical record is being viewed.
     */
    public DoctorPatientMedicalRecordView(Patient patient) {
        this.patient = patient;
    }   

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Patient Medical Record".
     */
    @Override
    public String getViewName() {
        return "Patient Medical Record";
    }

    /**
     * Renders the view for the doctor to view the patient's medical record.
     * The view displays the patient's details and their medical record, and allows the doctor to update the medical record.
     * <p>
     * This method sets up the following components in the user interface:
     * <ol>
     * <li>Displaying the patient's details.</li>
     * <li>Displaying the patient's medical record.</li>
     * <li>Providing an option to update the medical record.</li>
     * </ol>
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Record").paint(context);
        new VSpacer(1).paint(context);

        new Title("Patient Details").paint(context);
        new PatientMedicalDetailsTable(patient).paint(context);
        new VSpacer(1).paint(context);

        List<MedicalRecordEntry> medicalRecord = medicalRecordManager.getRecords(patient);

        new Title("Medical Record").paint(context);
        new DoctorMedicalRecordsTable(medicalRecord).paint(context);
        new VSpacer(1).paint(context);

        TextInputField entryField = new TextInputField("Choose medical record to update or view details");
        new TextInput(entryField).read(context, input -> InputValidators.validateRange(input, medicalRecord.size()));

        final MedicalRecordEntry selectedEntry = medicalRecord.get(entryField.getOption());

        Navigator.navigateTo(new DoctorUpdateMedicalRecordDetailsView(selectedEntry));
    }
    
}
