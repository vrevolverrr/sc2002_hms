package view.Patient.records;

import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import model.medrecord.MedicalRecordEntry;
import services.Navigator;
import view.View;
import view.Patient.records.widgets.PatientDetailedMedicalRecordEntryTable;
import view.widgets.Title;

/**
 * View for displaying details of a specific medical record entry.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientMedicalRecordDetailsView extends View {
    /**
     * The medical record entry being displayed.
     */
    private final MedicalRecordEntry entry;

    /**
     * Constructs a new view for displaying details of a specific medical record entry.
     * @param entry the medical record entry being displayed.
     */
    public PatientMedicalRecordDetailsView(MedicalRecordEntry entry) {
        this.entry = entry;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Patient Medical Record Details";
    }

    /**
     * Renders the view to display details of the medical record entry.
     * The view shows the a table with the details of the medical record entry.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Record Details").paint(context);

        new PatientDetailedMedicalRecordEntryTable(entry).paint(context);

        Pause.goBack().pause(context);
        Navigator.pop();
    }
    
}
