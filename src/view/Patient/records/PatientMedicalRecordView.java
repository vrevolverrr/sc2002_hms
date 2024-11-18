package view.Patient.records;

import java.util.List;

import controller.interfaces.IMedicalRecordManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.medrecord.MedicalRecordEntry;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Patient.records.widgets.PatientMedicalDetailsTable;
import view.Patient.records.widgets.PatientMedicalRecordsTable;
import view.widgets.Title;

/**
 * View for displaying a patient's medical records.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientMedicalRecordView extends View {
    /**
     * Manager for handling medical record-related operations.
     */
    private final IMedicalRecordManager recordManager = ServiceLocator.getService(IMedicalRecordManager.class);

    /**
     * The patient whose medical records are being displayed.
     */
    private final Patient patient;

    /**
     * Context for building the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(110);
    
    /**
     * Constructs a new view for displaying a patient's medical records.
     * @param patient The patient whose medical records are being displayed.
     */
    public PatientMedicalRecordView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Medical Record";
    }

    /**
     * Renders the view to display the patient's medical records.
     * The view shows a table with the patient's medical details and a table 
     * with the patient's medical records.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Record").paint(context);
        new PatientMedicalDetailsTable(patient).paint(context);

        new VSpacer(1).paint(context);

        final List<MedicalRecordEntry> medicalRecods = recordManager.getRecords(patient);

        new Title("Medical History").paint(context);
        new PatientMedicalRecordsTable(medicalRecods).paint(context);

        if (medicalRecods.isEmpty()) {
            new Pause("No medical records found. Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        TextInputField entryField = new TextInputField("Choose medical record to view details");
        new TextInput(entryField).read(context, "Choose a medical record entry from the list.",
            input -> InputValidators.validateRange(input, medicalRecods.size()));

        final MedicalRecordEntry selectedEntry = medicalRecods.get(entryField.getOption());

        Navigator.navigateTo(new PatientMedicalRecordDetailsView(selectedEntry));
    }
    
}
