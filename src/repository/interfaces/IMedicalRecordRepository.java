package repository.interfaces;

import java.util.List;

import model.medrecord.MedicalRecordEntry;

/**
 * The interface that defines the contract for a medical record repository.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IMedicalRecordRepository extends IRepository<MedicalRecordEntry> {
    /**
     * Find all medical record entries by patient ID.
     * @param patientId the patient ID.
     * @return the list of medical record entries.
     */
    public List<MedicalRecordEntry> findByPatientId(String patientId);
     
    /**
     * Find all medical record entries by doctor ID.
     * @param doctorId the doctor ID.
     * @return the list of medical record entries.
     */
    public List<MedicalRecordEntry> findByDoctorId(String doctorId);
}
