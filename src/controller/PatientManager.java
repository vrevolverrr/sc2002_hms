package controller;

import java.util.List;

import controller.interfaces.IPatientManager;
import model.appointments.Appointment;
import model.users.Doctor;
import model.users.Patient;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.IPatientRepository;

/**
 * Manages operations related to patients.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class PatientManager implements IPatientManager {
    /**
     * Repository for accessing appointment data.
     */
    private final IAppointmentRepository appointmentRepository;

    /**
     * Repository for accessing patient data.
     */
    private final IPatientRepository patientRepository;

    /**
     * Creates a new {@link PatientManager}.
     * @param patientRepository the repository for accessing patient data.
     * @param appointmentRepository the repository for accessing appointment data.
     */
    public PatientManager(IPatientRepository patientRepository, IAppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    };

    /**
     * Retrieves a patient by their ID.
     *
     * @param patientId the ID of the {@link Patient}.
     * @return the {@link Patient} with the specified ID.
     */
    public Patient getPatient(String patientId) {
        return patientRepository.findById(patientId);
    }

    /**
     * Updates the patient in the repository.
     * 
     * @param patient the {@link Patient} to update.
     */
    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }

    /**
     * Get all patients under a {@link Doctor}'s' care.
     * @param doctor the doctor to get the patients under care for.
     * @return a list of patients under the doctor's care.
     */
    public List<Patient> getPatientsUnderDoctorCare(Doctor doctor) {
        List<Appointment> dAppointments = appointmentRepository.getPastAppointments(doctor);
        List<Patient> patientsUnderCare = 
            dAppointments.stream()
            .map(appointment -> getPatient(appointment.getPatientId()))
            .distinct()
            .toList();

        return patientsUnderCare;
    }
}
