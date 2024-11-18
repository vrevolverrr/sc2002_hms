package controller.interfaces;

import java.util.List;
import model.appointments.AppointmentOutcomeRecord;
import model.medrecord.MedicalRecordEntry;
import model.users.Doctor;
import model.users.Patient;

/**
 * This interface provides methods to manage medical records in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IMedicalRecordManager {
    /**
     * Retrieves all medical records of a patient.
     *
     * @param patient the {@link Patient} whose medical records are being retrieved.
     * @return a {@link List} of all {@link MedicalRecordEntry} of the patient.
     */
    public List<MedicalRecordEntry> getRecords(Patient patient);
    
    /**
     * Update a medical record.
     * @param record the{@link MedicalRecordEntry} to update.
     */
    public void updateRecord(MedicalRecordEntry record);

    /**
     * Creates a medical record from an appointment outcome.
     * 
     * @param patient the {@link Patient} whose record is being created.
     * @param doctor the {@link Doctor} who treated the patient.
     * @param outcomeRecord the outcome record of the appointment.
     */
    public void createMedicalRecordFromOutcome(Patient patient, Doctor doctor, AppointmentOutcomeRecord outcomeRecord);
    
}
