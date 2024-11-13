package view.Patient.records;

import model.users.Patient;
import view.View;

public class PatientMedicalRecordView extends View {
    private final Patient patient;
    
    public PatientMedicalRecordView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
        return "Medical Records";
    }

    @Override
    public void render() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'render'");
    }
    
}
