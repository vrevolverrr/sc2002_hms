package repository;

import java.util.List;

import model.medrecord.MedicalRecordEntry;

public class MedicalRecordRepository extends BaseRepository<MedicalRecordEntry> {
    private final static String FILENAME = "records.dat";

    public MedicalRecordRepository() {
        super(FILENAME);
    }

    public List<MedicalRecordEntry> findByPatientId(String patientId) {
        return getItems().values().stream()
        .filter(entry -> entry.getPatientId().equals(patientId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }

    public List<MedicalRecordEntry> findByDoctorId(String doctorId) {
        return getItems().values().stream()
        .filter(entry -> entry.getDoctorId().equals(doctorId))
        .sorted((b, a) -> a.getDateRecorded().compareTo(b.getDateRecorded()))
        .toList();
    }

    @Override
    public String generateId() {
        return "M" + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }
}
