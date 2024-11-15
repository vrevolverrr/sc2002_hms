package controller;

import java.util.List;

import model.appointments.Appointment;
import model.users.Doctor;
import model.users.Patient;
import repository.PatientRepository;
import repository.UserRepository;

public class PatientManager extends Manager<PatientManager> {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    
    private final PatientRepository patientRepository = new PatientRepository(UserRepository.getInstance());

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
        List<Appointment> dAppointments = appointmentManager.getPastAppointments(doctor);
        List<Patient> patientsUnderCare = 
            dAppointments.stream()
            .map(appointment -> getPatient(appointment.getPatientId()))
            .distinct()
            .toList();
        System.out.println(patientsUnderCare.size());
        return patientsUnderCare;
    }
}
