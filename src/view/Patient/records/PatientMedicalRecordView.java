package view.Patient.records;

import controller.MedicalRecordManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.VSpacer;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.records.widgets.PatientMedicalDetailsTable;
import view.Patient.records.widgets.PatientMedicalRecordsTable;
import view.widgets.Title;

public class PatientMedicalRecordView extends View {
    private final MedicalRecordManager recordManager = MedicalRecordManager.getInstance(MedicalRecordManager.class);
    private final Patient patient;

    private final BuildContext context = BuildContext.unboundedVertical(110);
    
    public PatientMedicalRecordView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
        return "Medical Record";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Patient Medical Record").paint(context);
        new PatientMedicalDetailsTable(patient).paint(context);

        new VSpacer(1).paint(context);

        new Title("Medical History").paint(context);
        new PatientMedicalRecordsTable(recordManager.getRecords(patient)).paint(context);

        new Pause("Press any key to go back.").pause(context);
        Navigator.pop();
    }
    
}
