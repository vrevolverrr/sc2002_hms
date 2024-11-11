package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.appointments.Appointment;
import model.appointments.TimeSlot;
import model.users.Doctor;
import model.users.Patient;

public class AppointmentRepository extends BaseRepository<Appointment> {
    private final static String FILENAME = "appointments.dat";
    
    /**
     * The prefix for the ID of an {@link Appointment}.
     */
    public static final String ID_PREFIX = "Y";

    public AppointmentRepository() {
        super(FILENAME);
    }

    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    public List<Appointment> getAppointmentsByDate(LocalDate date) {
        return new ArrayList<Appointment>();
    }
}
