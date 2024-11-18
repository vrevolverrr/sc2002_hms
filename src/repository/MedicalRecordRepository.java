package repository;
import java.util.List;

import model.medrecord.MedicalRecordEntry;
import repository.interfaces.IMedicalRecordRepository;
import repository.interfaces.IRepository;

/**
 * An implementation of {@link IRepository} that operates on {@link MedicalRecordEntry} data models.
 * This repository provides methods to manage medical records, including finding records by patient ID or doctor ID.
 * 
 * @see <a href="https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/">Dependency Injection</a>
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
     * Generates a unique ID for a new {@link MedicalRecordEntry}.
     * 
     * @return the generated ID for the {@link MedicalRecordEntry}.
     */
    @SuppressWarnings("unused")
	@Override
    public String generateId() {
        return "M" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Finds all medical record entries associated with a specific patient ID.
     * The results are sorted in descending order by the date the records were created.
     * 
     * @param patientId the ID of the patient whose medical records are to be retrieved.
     * @return a sorted {@link List} of {@link MedicalRecordEntry} objects for the specified patient.
     */
    public List<MedicalRecordEntry> findByPatientId(String patientId) {
        return getItems().values().stream()
        .filter(entry -> entry.getPatientId().equals(patientId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }

    /**
     * Finds all medical record entries associated with a specific doctor ID.
     * The results are sorted in descending order by the date the records were created.
     * 
     * @param doctorId the ID of the doctor whose medical records are to be retrieved.
     * @return a sorted {@link List} of {@link MedicalRecordEntry} objects for the specified doctor.
     */
    public List<MedicalRecordEntry> findByDoctorId(String doctorId) {
        return getItems().values().stream()
        .filter(entry -> entry.getDoctorId().equals(doctorId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }
}
