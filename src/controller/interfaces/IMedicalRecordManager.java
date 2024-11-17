package controller.interfaces;

import java.util.List;
import model.appointments.AppointmentOutcomeRecord;
import model.medrecord.MedicalRecordEntry;
import model.users.Doctor;
import model.users.Patient;

public interface IMedicalRecordManager {
    public List<MedicalRecordEntry> getRecords(Patient patient);
    
    public void updateRecord(MedicalRecordEntry record);

    public void createMedicalRecordFromOutcome(Patient patient, Doctor doctor, AppointmentOutcomeRecord outcomeRecord);
    
}
