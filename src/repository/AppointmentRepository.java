package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.appointments.Appointment;

/**
 * The repository for {@link Appointment} objects.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class AppointmentRepository extends BaseRepository<Appointment> {
    /**
     * The filename of the file to store the {@link Appointment} objects.
     */
    private final static String FILENAME = "appointments.dat";
    
    /**
     * The prefix for the ID of an {@link Appointment}.
     */
    public static final String ID_PREFIX = "Y";

    /**
     * Constructor for the {@link AppointmentRepository} class.
     */
    public AppointmentRepository() {
        super(FILENAME);
    }

    /**
     * Generates an ID for an {@link Appointment}.
     * @return the generated ID for an {@link Appointment}.
     */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    /**
     * Gets all the {@link Appointment} objects stored in the repository.
     * @return the entries of {@link Appointment} stored.
     */
    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return new ArrayList<Appointment>();
    }

    /**
     * Saves an {@link Appointment} object to the repository.
     * @param item the {@link Appointment} object to save.
     * @return the saved {@link Appointment} object.
     */
    @Override
    public Appointment save(Appointment item) {
        if (item.getId() == null || item.getId().isBlank()) { // If the doctor does not have an ID
            item.setId(generateId()); // Generate a new ID for the doctor
        }

        return super.save(item);
    }
}
