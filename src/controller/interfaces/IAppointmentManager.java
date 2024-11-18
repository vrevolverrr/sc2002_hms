package controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.appointments.Appointment;
import model.appointments.AppointmentOutcomeRecord;
import model.appointments.AppointmentSlot;
import model.enums.MedicalService;
import model.prescriptions.Prescription;
import model.users.Doctor;
import model.users.Patient;

/**
 * This interface provides methods to manage appointments in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IAppointmentManager {
    /**
     * Updates the appointment in the repository.
     * @param appointment the appointment to update.
     */
    void updateAppointment(Appointment appointment);

    /**
     * Creates an appointment request, for doctor approval.
     * @param slot the appointment slot to schedule the appointment.
     * @param patient the patient to schedule the appointment for.
     */
    void scheduleAppointment(AppointmentSlot slot, Patient patient);

    /**
     * Cancels a scheduled appointment.
     * @param appointment the appointment to cancel.
     */
    void cancelAppointment(Appointment appointment);

    /**
     * Reschedules an appointment to a new appointment slot.
     * @param initialAppointment the initial appointment to reschedule.
     * @param newSlot the new appointment slot.
     */
    void rescheduleAppointment(Appointment initialAppointment, AppointmentSlot newSlot);

    /**
     * Accepts a requested appointment, marking it as scheduled.
     * @param appointment the appointment to accept.
     */
    void acceptAppointment(Appointment appointment);

    /**
     * Declines a requested appointment, marking it as cancelled.
     * @param appointment the appointment to decline.
     */
    void declineAppointment(Appointment appointment);

    /**
     * Marks an appointment as fulfilled.
     * @param appointment the appointment to mark as fulfilled.
     */
    AppointmentOutcomeRecord updateAppointmentOutcome(Appointment appointment, String consultationNotes,
                                                      List<Prescription> prescriptions, List<MedicalService> services);

    /**
     * Gets the available time slots for an appointment of a doctor on a given date.
     * @param date the date to get the available slots for.
     * @param doctor the doctor.
     * @return the list of available time slots.
     */
    List<AppointmentSlot> getAvailableSlotsByDoctor(LocalDate date, Doctor doctor);

    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    List<Appointment> getAllAppointments();

    /**
     * Gets all the appointments of a doctor, ordered by most recent first.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    List<Appointment> getAppointments(Doctor doctor);

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    List<Appointment> getScheduledAppointments(Doctor doctor);

    /**
     * Gets the upcoming scheduled appointments of a doctor scheduled on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    List<Appointment> getScheduledAppointments(Doctor doctor, LocalDate date);

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    List<Appointment> getScheduledAppointments(Patient patient);

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    List<Appointment> getPendingAppointments(Patient patient);

    /**
     * Gets upcoming appointments of a patient, that is either scheduled or requested,
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    List<Appointment> getUpcomingAppointments(Patient patient);

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    List<Appointment> getPendingAppointments(Doctor doctor);

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    List<Appointment> getFulfilledAppointments(Doctor doctor);

    /**
     * Gets the completed appointments of a doctor, that is with outcome recorded.
     * @param doctor the doctor to get the appointments for.
     * @return the list of completed appointments, ordered by most recent first.
     */
    List<Appointment> getCompletedAppointments(Doctor doctor);

    /**
     * Gets the past appointments of a patient, that is either completed or fulfilled.
     * @param patient the patient to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    List<Appointment> getPastAppointments(Patient patient);

    /**
     * Gets the past appointments of a doctor, that is either completed or fulfilled.
     * @param doctor the doctor to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    List<Appointment> getPastAppointments(Doctor doctor);

    /**
     * Gets the undispensed appointments, that is completed appointments with pending prescriptions.
     * @return the list of undispensed appointments, ordered by most recent first.
     */
    List<Appointment> getUndispensedAppointments();
}
