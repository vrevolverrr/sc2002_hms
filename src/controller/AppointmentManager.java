package controller;

import model.appointments.Appointment;
import model.appointments.AppointmentOutcomeRecord;
import model.appointments.AppointmentSlot;
import model.appointments.TimeSlot;
import model.enums.MedicalService;
import model.prescriptions.Prescription;
import model.users.Doctor;
import model.users.Patient;
import repository.interfaces.IAppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import controller.interfaces.IAppointmentManager;

public class AppointmentManager implements IAppointmentManager {
    private final IAppointmentRepository appointmentRepository;

    public final static int START_HOUR = 8;
    public final static int END_HOUR = 17;
    public final static int SLOT_DURATION = 30;

    public AppointmentManager(IAppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
        
        // Every time the AppointmentManager is instantiated, it will check for past appointments
        // and mark them as fulfilled if it is overdue.
        fulfillPastAppointments();
    }

    /**
     * Marks all past appointments as fulfilled if they are overdue.
     * This method is called every time the {@link AppointmentManager} is instantiated.
     */
    private void fulfillPastAppointments() {
        appointmentRepository.getOverdueAppointments().forEach(appointment -> {
            appointment.markAsFulfilled();
            appointmentRepository.save(appointment);
        });
    }

    /**
     * Updates the appointment in the repository.
     * @param appointment the appointment to update.
     */
    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    /**
     * Creates an appointment request, for doctor approval.
     * @param slot the appointment slot to schedule the appointment.
     * @param patient the patient to schedule the appointment for.
     */
    public void scheduleAppointment(AppointmentSlot slot, Patient patient) {
        Appointment newAppointment = Appointment.schedule(
            appointmentRepository.generateId(), slot.getTimeSlot(), slot.getDoctor().getDoctorId(), patient.getPatientId());

        appointmentRepository.save(newAppointment);
    }

    /**
     * Cancels a scheduled appointment.
     * @param appointment the appointment to cancel.
     */
    public void cancelAppointment(Appointment appointment) {
        appointment.markAsCancelled();
        appointmentRepository.save(appointment);
    }

    /**
     * Reschedules an appointment to a new appointment slot.
     * @param initialAppointment the initial appointment to reschedule.
     * @param newSlot the new appointment slot.
     */
    public void rescheduleAppointment(Appointment initialAppointment, AppointmentSlot newSlot) {
        initialAppointment.markAsCancelled();
        appointmentRepository.save(initialAppointment);

        Appointment newAppointment = Appointment.schedule(
            appointmentRepository.generateId(), newSlot.getTimeSlot(),
             newSlot.getDoctor().getDoctorId(), initialAppointment.getPatientId());

        appointmentRepository.save(newAppointment);
    }

    /**
     * Accepts a requested appointment, marking it as scheduled.
     * @param appointment
     */
    public void acceptAppointment(Appointment appointment) {
        appointment.markAsScheduled();
        appointmentRepository.save(appointment);
    }

    /**
     * Declines a requested appointment, marking it as cancelled.
     * @param appointment
     */
    public void declineAppointment(Appointment appointment) {
        appointment.markAsCancelled();
        appointmentRepository.save(appointment);
    }

    /**
     * Marks an appointment as fulfilled.
     * @param appointment the appointment to mark as fulfilled.
     */
    public AppointmentOutcomeRecord updateAppointmentOutcome(
        Appointment appointment, String consultationNotes,
        List<Prescription> prescriptions, List<MedicalService> services) {
        
        AppointmentOutcomeRecord outcomeRecord = 
            appointment.createOutcomeRecord(prescriptions, services, consultationNotes);
        
        appointmentRepository.save(appointment);

        return outcomeRecord;
    }

    /**
     * Gets the available time slots for an appointment of a doctor on a given date.
     * @param date the date to get the available slots for.
     * @param doctor the doctor.
     * @return the list of avaialble time slots.
     */
    public List<AppointmentSlot> getAvailableSlotsByDoctor(LocalDate date, Doctor doctor) {
        List<AppointmentSlot> availableSlots = new ArrayList<AppointmentSlot>();

        for (TimeSlot slot : generateTimeSlots(date)) {
            // Check if the time slot is in the past.
            if (slot.getDateTime().isBefore(LocalDateTime.now())) {
                continue;
            }

            // Check if there is already an exisitng appointment at this time slot.
            if (!appointmentRepository.isSlotAvailable(doctor, slot)) {
                continue;
            }

            // Check if the doctor is available at this time slot.
            if (!doctor.isAvailable(slot)) {
                continue;
            }

            availableSlots.add(new AppointmentSlot(doctor, slot));
        }

        return availableSlots;
    }

    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getAppointments();
    }

    /**
     * Gets all the appointments of a doctor, ordered by most recent first.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments(Doctor doctor) {
        return appointmentRepository.getAppointments(doctor);
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor) {
        return appointmentRepository.getScheduledAppointments(doctor);
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor scheduled on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor, LocalDate date) {
        return appointmentRepository.getScheduledAppointments(doctor, date);
    }

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getScheduledAppointments(Patient patient) {
        return appointmentRepository.getScheduledAppointments(patient);
    }

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Patient patient) {
        return appointmentRepository.getPendingAppointments(patient);
    }

    /**
     * Gets upcoming appointments of a patient, that is either scheduled or requested, 
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getUpcomingAppointments(Patient patient) {
        return appointmentRepository.getUpcomingAppointments(patient);
    }

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Doctor doctor) {
        return appointmentRepository.getPendingAppointments(doctor);
    }

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    public List<Appointment> getFulfilledAppointments(Doctor doctor) {
        return appointmentRepository.getFulfilledAppointments(doctor);
    }

    /**
     * Gets the completed appointments of a doctor, that is with outcome recorded.
     * @param doctor the doctor to get the appointments for.
     * @return the list of completed appointments, ordered by most recent first.
     */
    public List<Appointment> getCompletedAppointments(Doctor doctor) {
        return appointmentRepository.getCompletedAppointments(doctor);
    }

    /**
     * Gets the past appointments of a patient, that is either completed or fulfilled.
     * @param patient the patient to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Patient patient) {
        return appointmentRepository.getPastAppointments(patient);
    }

    /**
     * Gets the past appointments of a doctor, that is either completed or fulfilled.
     * @param doctor the doctor to get the appointments for.
     * @return the list of past appointments, ordered by most recent first.
     */
    public List<Appointment> getPastAppointments(Doctor doctor) {
        return appointmentRepository.getPastAppointments(doctor);
    }

    /**
     * Gets the undispensed appointments, that is completed appointments with pending prescriptions.
     * @return the list of undispensed appointments, ordered by most recent first.
     */
    public List<Appointment> getUndispensedAppointments() {
        return appointmentRepository.getUndispensedAppointments();
    }

    /**
     * Generates all possible time slots for appointments on a given date.
     * @param date the date to generate the time slots for.
     * @return the list of time slots.
     */
    public static List<TimeSlot> generateTimeSlots(LocalDate date) {
        // The time slots start at 8:00am, and the last slot start at 5:30pm.
        // for a total of 20 time slots per day.
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (int hour = START_HOUR; hour < END_HOUR; hour++) {
            for (int minute = 0; minute < 60; minute += SLOT_DURATION) {
                timeSlots.add(new TimeSlot(date.atTime(hour, minute)));
            }
        }

        return timeSlots;
    }
}
