package controller;

import model.users.Patient;
import repository.PatientRepository;
import repository.UserRepository;

public class PatientManager extends Manager<PatientManager> {
    private final PatientRepository patientRepository = new PatientRepository(UserRepository.getInstance());

    public Patient getPatient(String patientId) {
        return patientRepository.findById(patientId);
    }

    public void updatePatient(Patient patient) {
        patientRepository.save(patient);
    }
}
