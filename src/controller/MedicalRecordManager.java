package controller;

import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IInventoryManager;
import controller.interfaces.IMedicalRecordManager;
import model.appointments.AppointmentOutcomeRecord;
import model.medrecord.MedicalRecordEntry;
import model.users.Doctor;
import model.users.Patient;
import repository.MedicalRecordRepository;

public class MedicalRecordManager implements IMedicalRecordManager {
    private final IInventoryManager inventoryManager;

    private final MedicalRecordRepository repository;

    public MedicalRecordManager(IInventoryManager inventoryManager, MedicalRecordRepository repository) {
        this.repository = repository;
        this.inventoryManager = inventoryManager;
    }

    public List<MedicalRecordEntry> getRecords(Patient patient) {
        return repository.findByPatientId(patient.getId());
    }

    public void createMedicalRecordFromOutcome(Patient patient, Doctor doctor, AppointmentOutcomeRecord outcomeRecord) {
        final String treatmentPlan = "Prescribed " + outcomeRecord.getPrescriptions().stream()
            .map(prescription -> inventoryManager.getItem(prescription.getDrugId()).getItemName())
            .collect(Collectors.joining(", "));
            

        final MedicalRecordEntry record = new MedicalRecordEntry(
            repository.generateId(), outcomeRecord.getRecordedDate(), patient.getPatientId(), doctor.getDoctorId(),
            outcomeRecord.getConsultationNotes(), treatmentPlan, outcomeRecord.getPrescriptions(),
            outcomeRecord.getServices()
        );

        repository.save(record);
    }

    public void updateRecord(MedicalRecordEntry record) {
        repository.save(record);
    }
}
