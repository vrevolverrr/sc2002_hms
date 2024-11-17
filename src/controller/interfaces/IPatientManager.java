package controller.interfaces;

import java.util.List;

import model.users.Doctor;
import model.users.Patient;

public interface IPatientManager {
    public Patient getPatient(String patientId);

    public void updatePatient(Patient patient);

    /**
     * Get all patients under a {@link Doctor}'s' care.
     * @param doctor the doctor to get the patients under care for.
     * @return a list of patients under the doctor's care.
     */
    public List<Patient> getPatientsUnderDoctorCare(Doctor doctor);
}
