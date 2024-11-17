package view.Doctor.records;

import java.util.List;

import controller.interfaces.IPatientManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.users.Doctor;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Doctor.records.widget.DoctorPatientCareTable;
import view.widgets.Title;

public class DoctorPatientsUnderCareView extends View {
    private final IPatientManager patientManager = ServiceLocator.getService(IPatientManager.class);

    private final List<Patient> patientsUnderCare;

    public DoctorPatientsUnderCareView(Doctor doctor) {
        patientsUnderCare = patientManager.getPatientsUnderDoctorCare(doctor);
    }

    @Override
    public String getViewName() {
        return "Patient Medical Record";
    }

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
