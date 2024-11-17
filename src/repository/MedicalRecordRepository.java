package repository;
import java.util.List;

import model.inventory.InventoryItem;
import model.medrecord.MedicalRecordEntry;

/**
 * An implementation of {@link Repository} that on {@link MedicalReocrdEntry} data models.
 * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/ 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public class MedicalRecordRepository extends BaseRepository<MedicalRecordEntry> {
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
    public List<MedicalRecordEntry> findByPatientId(String patientId) {
        return getItems().values().stream()
        .filter(entry -> entry.getPatientId().equals(patientId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }

    /**
     * Generates an ID for a {@link MedicalRecordEntry}.
     * @return the generated ID for a {@link MedicalRecordEntry}.
     */
    public List<MedicalRecordEntry> findByDoctorId(String doctorId) {
        return getItems().values().stream()
        .filter(entry -> entry.getDoctorId().equals(doctorId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
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
}
