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

public class DoctorPatientMedicalRecordView extends View {
    private final IMedicalRecordManager medicalRecordManager = ServiceLocator.getService(IMedicalRecordManager.class);
    private final Patient patient;

    private final BuildContext context = BuildContext.unboundedVertical(110);

    public DoctorPatientMedicalRecordView(Patient patient) {
        this.patient = patient;
    }   

    @Override
    public String getViewName() {
        return "Patient Medical Record";
    }

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
