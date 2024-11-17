package controller.interfaces;

import java.util.List;

import model.users.Doctor;
import model.users.Patient;

/**
 * This interface provides methods to manage patients in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IPatientManager {
    /**
     * Retrieves patient by their ID.
     *
     * @param patientId the ID of the {@link Patient}.
     */
    public Patient getPatient(String patientId);

    /**
     * Updates the patient in the repository.
     * 
     * @param patient the {@link Patient} to update.
     */
    public void updatePatient(Patient patient);

    /**
     * Get all patients under a {@link Doctor}'s' care.
     * @param doctor the doctor to get the patients under care for.
     * @return a {@link List} of {@link Patient} under the doctor's care.
     */
    public List<Patient> getPatientsUnderDoctorCare(Doctor doctor);
}
