package repository;
import java.util.List;

import model.medrecord.MedicalRecordEntry;
import repository.interfaces.IMedicalRecordRepository;
import repository.interfaces.IRepository;

/**
 * An implementation of {@link IRepository} that on {@link MedicalReocrdEntry} data models.
 * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/ 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public class MedicalRecordRepository extends BaseRepository<MedicalRecordEntry> implements IMedicalRecordRepository {
    /**
     * The filename of the file to store the {@link MedicalRecordEntry} objects.
     */
    private final static String FILENAME = "records.dat";

    /**
     * Constructor for the {@link MedicalRecordRepository} class.
     */
    public MedicalRecordRepository() {
        super(FILENAME);
    }

    /**
     * Generates an ID for a {@link MedicalRecordEntry}.
     * @return the generated ID for a {@link MedicalRecordEntry}.
     */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return "M" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Find all medical record entries by patient ID.
     * @param patientId the patient ID.
     * @return the list of medical record entries.
     */
    public List<MedicalRecordEntry> findByPatientId(String patientId) {
        return getItems().values().stream()
        .filter(entry -> entry.getPatientId().equals(patientId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }

    /**
     * Find all medical record entries by doctor ID.
     * @param doctorId the doctor ID.
     * @return the list of medical record entries.
     */
    public List<MedicalRecordEntry> findByDoctorId(String doctorId) {
        return getItems().values().stream()
        .filter(entry -> entry.getDoctorId().equals(doctorId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }
}
