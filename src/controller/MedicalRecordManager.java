package controller;

import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IMedicalRecordManager;
import model.appointments.AppointmentOutcomeRecord;
import model.medrecord.MedicalRecordEntry;
import model.users.Doctor;
import model.users.Patient;
import repository.interfaces.IInventoryRepository;
import repository.interfaces.IMedicalRecordRepository;

/**
 * Manages operations related to medical records.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class MedicalRecordManager implements IMedicalRecordManager {
    /**
     * Repository for accessing inventory data.
     */
    private final IInventoryRepository inventoryRepository;
    
    /**
     * Repository for accessing medical record data.
     */
    private final IMedicalRecordRepository repository;

    /**
     * Creates a new MedicalRecordManager.
     * @param repository the repository for accessing medical record data.
     * @param inventoryRepository the repository for accessing inventory data.
     */
    public MedicalRecordManager(IMedicalRecordRepository repository, IInventoryRepository inventoryRepository) {
        this.repository = repository;
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves all medical records for a specific {@link Patient}.
     *
     * @param patient the {@link Patient}.
     * @return a list of {@link MedicalRecordEntry} instances representing the medical records of the patient.
     */
    public List<MedicalRecordEntry> getRecords(Patient patient) {
        return repository.findByPatientId(patient.getId());
    }

    /**
     * Creates a new {@link MedicalRecordEntry} from an {@link AppointmentOutcomeRecord}.
     *
     * @param patient the {@link Patient}.
     * @param doctor the {@link Doctor}.
     * @param outcomeRecord the {@link AppointmentOutcomeRecord}.
     */
    public void createMedicalRecordFromOutcome(Patient patient, Doctor doctor, AppointmentOutcomeRecord outcomeRecord) {
        final String treatmentPlan = "Prescribed " + outcomeRecord.getPrescriptions().stream()
            .map(prescription -> inventoryRepository.findById(prescription.getDrugId()).getItemName())
            .collect(Collectors.joining(", "));
            
        final MedicalRecordEntry record = new MedicalRecordEntry(
            repository.generateId(), outcomeRecord.getRecordedDate(), patient.getPatientId(), doctor.getDoctorId(),
            outcomeRecord.getConsultationNotes(), treatmentPlan, outcomeRecord.getPrescriptions(),
            outcomeRecord.getServices()
        );

        repository.save(record);
    }

    /**
     * Updates a medical record.
     *
     * @param record the {@link MedicalRecordEntry} to update.
     */
    public void updateRecord(MedicalRecordEntry record) {
        repository.save(record);
    }
}
