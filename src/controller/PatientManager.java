package controller;

import java.util.List;

import controller.interfaces.IPatientManager;
import model.appointments.Appointment;
import model.users.Doctor;
import model.users.Patient;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.IPatientRepository;

public class PatientManager implements IPatientManager {
    private final IAppointmentRepository appointmentRepository;
    private final IPatientRepository patientRepository;

    public PatientManager(IPatientRepository patientRepository, IAppointmentRepository appointmentRepository) {
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    };

    public Patient getPatient(String patientId) {
        return patientRepository.findById(patientId);
    }

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
