package controller;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import controller.interfaces.IPatientManager;
import model.appointments.Appointment;
import model.users.Doctor;
import model.users.Patient;
import repository.PatientRepository;

public class PatientManager implements IPatientManager {
    private final IAppointmentManager appointmentManager;
    
    private final PatientRepository patientRepository;

    public PatientManager(IAppointmentManager appointmentManager, PatientRepository patientRepository) {
        this.appointmentManager = appointmentManager;
        this.patientRepository = patientRepository;
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
        List<Appointment> dAppointments = appointmentManager.getPastAppointments(doctor);
        List<Patient> patientsUnderCare = 
            dAppointments.stream()
            .map(appointment -> getPatient(appointment.getPatientId()))
            .distinct()
            .toList();

        return patientsUnderCare;
    }
}
