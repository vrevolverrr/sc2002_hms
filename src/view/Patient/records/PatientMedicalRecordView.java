package view.Patient.records;

import java.util.List;

import controller.MedicalRecordManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.medrecord.MedicalRecordEntry;
import model.users.Patient;
import services.Navigator;
import utils.InputValidators;
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

        final List<MedicalRecordEntry> medicalRecods = recordManager.getRecords(patient);

        new Title("Medical History").paint(context);
        new PatientMedicalRecordsTable(medicalRecods).paint(context);

        TextInputField entryField = new TextInputField("Choose medical record to view details");
        new TextInput(entryField).read(context, "Choose a medical record entry from the list.",
            input -> InputValidators.validateRange(input, medicalRecods.size()));

        final MedicalRecordEntry selectedEntry = medicalRecods.get(entryField.getOption());

        Navigator.navigateTo(new PatientMedicalRecordDetailsView(selectedEntry));
    }
    
}
