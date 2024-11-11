package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.appointments.Appointment;

public class AppointmentRepository extends BaseRepository<Appointment> {
    private final static String FILENAME = "appointments.dat";
    
    /**
     * The prefix for the ID of an {@link Appointment}.
     */
    public static final String ID_PREFIX = "Y";

    public AppointmentRepository() {
        super(FILENAME);
    }

    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return new ArrayList<Appointment>();
    }

    @Override
    public Appointment save(Appointment item) {
        if (item.getId() == null || item.getId().isBlank()) { // If the doctor does not have an ID
            item.setId(generateId()); // Generate a new ID for the doctor
        }

        return super.save(item);
    }
}
