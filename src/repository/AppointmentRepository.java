package repository;

import java.time.LocalDate;
import java.util.List;

import model.appointments.Appointment;
import model.appointments.TimeSlot;
import model.enums.PrescriptionStatus;
import model.users.Doctor;
import model.users.Patient;
import repository.interfaces.IAppointmentRepository;

public class AppointmentRepository extends BaseRepository<Appointment> implements IAppointmentRepository {
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

    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments() {
        return getItems().values()
            .stream().sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets all the appointments of a doctor, ordered by most recent first.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments(Doctor doctor) {
        return findBy((appointment) -> appointment.getDoctorId().equals(doctor.getDoctorId()))
            .stream()
            .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot()))
            .toList();
    }

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Patient patient) {
        return findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Doctor doctor) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isScheduled()
        );
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor scheduled on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor, LocalDate date) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.getTimeSlot().getDate().equals(date) &&
            appointment.isScheduled()
        );
    }

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getScheduledAppointments(Patient patient) {
        return findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isScheduled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets both upcoming {@code SCHEDULED} and {@code REQUESTED} appointments of a patient, 
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getUpcomingAppointments(Patient patient) {
        return findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            (appointment.isScheduled() || appointment.isRequested())
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the scheduled appointments that are overdue.
     * @return the list of appointments that are overdue.
     */
    public List<Appointment> getOverdueAppointments() {
        return findBy((appointment) -> 
            appointment.isScheduled() && appointment.isOverdue()
        );
    }

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    public List<Appointment> getFulfilledAppointments(Doctor doctor) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the completed appointments of a doctor, that is with outcome recorded.
     * @param doctor the doctor to get the appointments for.
     * @return the list of completed appointments, ordered by most recent first.
     */
    public List<Appointment> getCompletedAppointments(Doctor doctor) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) &&
            appointment.isCompleted()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the past appointments of a patient, that is either completed or fulfilled.
     * @param patient the patient to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Patient patient) {
        return findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            appointment.isCompleted() || appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the past appointments of a doctor, that is either completed or fulfilled.
     * @param doctor the doctor to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Doctor doctor) {
        return findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.isCompleted() || appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the undispensed appointments, that is completed appointments with pending prescriptions.
     * @return the list of undispensed appointments, ordered by most recent first.
     */
    public List<Appointment> getUndispensedAppointments() {
        return findBy((appointment) -> 
            appointment.isCompleted() && 
            appointment.getOutcomeRecord() != null && // this condition is kept for redudnancy checks
            appointment.getOutcomeRecord().getPrescriptions().stream()
            .anyMatch(prescription -> prescription.getStatus() == PrescriptionStatus.PENDING)
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Checks whether a time slot is available for an appointment with a doctor.
     * @param slot the time slot to check.
     * @param doctor the doctor to check the availability for.
     * @return whether the slot is available.
     */
    public boolean isSlotAvailable(Doctor doctor, TimeSlot slot) {
        List<Appointment> scheduledAppointments = getScheduledAppointments(doctor, slot.getDateTime().toLocalDate());

        for (Appointment appointment : scheduledAppointments) {
            if (appointment.getTimeSlot().equals(slot)) {
                return false;
            }
        }

        return true;
    }
}
