package repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import controller.appointments.TimeSlot;
import model.appointments.Appointment;
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

    public List<Appointment> getAppointmentsByDateAndDoctor(LocalDate date, Doctor doctor) {
        return new ArrayList<Appointment>();
    }

    public List<Appointment> getAppointmentsByPatient(Patient patient) {
        return new ArrayList<Appointment>();
    }

    public List<Appointment> getAppointmentsByDateAndPatient(LocalDate date, Patient patient) {
        return new ArrayList<Appointment>();
    }

    public boolean isAppointmentSlotAvailable(TimeSlot slot, Doctor doctor) {
        List<Appointment> scheduledAppointments = getAppointmentsByDateAndDoctor(slot.getDateTime().toLocalDate(), doctor);

        for (Appointment appointment : scheduledAppointments) {
            if (appointment.getDateTime().equals(slot)) {
                return false;
            }
        }

        return true;
    }
}
