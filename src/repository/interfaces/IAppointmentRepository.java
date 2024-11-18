package repository.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.appointments.Appointment;
import model.appointments.TimeSlot;
import model.users.Doctor;
import model.users.Patient;

/**
 * The interface that defines the contract for an appointment repository.
 * 
 * @see AppointmentRepository
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IAppointmentRepository extends IRepository<Appointment> {
    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments();

    /**
     * Gets all the appointments of a doctor, ordered by most recent first.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments(Doctor doctor);

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Patient patient);

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Doctor doctor);

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor);

    /**
     * Gets the upcoming scheduled appointments of a doctor scheduled on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor, LocalDate date);

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getScheduledAppointments(Patient patient);

    /**
     * Gets both upcoming {@code SCHEDULED} and {@code REQUESTED} appointments of a patient, 
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getUpcomingAppointments(Patient patient);

    /**
     * Gets the scheduled appointments that are overdue.
     * @return the list of appointments that are overdue.
     */
    public List<Appointment> getOverdueAppointments();

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    public List<Appointment> getFulfilledAppointments(Doctor doctor);

    /**
     * Gets the completed appointments of a doctor, that is with outcome recorded.
     * @param doctor the doctor to get the appointments for.
     * @return the list of completed appointments, ordered by most recent first.
     */
    public List<Appointment> getCompletedAppointments(Doctor doctor);

    /**
     * Gets the past appointments of a patient, that is either completed or fulfilled.
     * @param patient the patient to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Patient patient);

    /**
     * Gets the past appointments of a doctor, that is either completed or fulfilled.
     * @param doctor the doctor to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Doctor doctor);

    /**
     * Gets the undispensed appointments, that is completed appointments with pending prescriptions.
     * @return the list of undispensed appointments, ordered by most recent first.
     */
    public List<Appointment> getUndispensedAppointments();

    /**
     * Checks whether a time slot is available for an appointment with a doctor.
     * @param slot the time slot to check.
     * @param doctor the doctor to check the availability for.
     * @return whether the slot is available.
     */
    public boolean isSlotAvailable(Doctor doctor, TimeSlot slot);
}
