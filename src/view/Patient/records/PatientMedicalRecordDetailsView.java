package view.Patient.records;

import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.VSpacer;
import model.medrecord.MedicalRecordEntry;
import services.Navigator;
import view.View;
import view.Patient.records.widgets.PatientDetailedMedicalRecordEntryTable;
import view.widgets.Title;

public class PatientMedicalRecordDetailsView extends View {
    private final MedicalRecordEntry entry;

    public PatientMedicalRecordDetailsView(MedicalRecordEntry entry) {
        this.entry = entry;
    }

    @Override
    public String getViewName() {
        return "Patient Medical Record Details";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Record Details").paint(context);

        new PatientDetailedMedicalRecordEntryTable(entry).paint(context);

        Pause.goBack().pause(context);
        Navigator.pop();
    }
    
}
