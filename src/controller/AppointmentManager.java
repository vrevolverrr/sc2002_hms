package controller;

import model.appointments.Appointment;
import model.appointments.AppointmentSlot;
import model.appointments.TimeSlot;
import model.enums.AppointmentStatus;
import model.users.Doctor;
import model.users.Patient;
import repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager extends Manager<AppointmentManager> {
    private AppointmentRepository appointmentRepository = new AppointmentRepository();

    protected AppointmentManager() {}

    /**
     * Schedules an appointment for a patient. Marks the appointment as {@code REQUESTED} 
     * for doctor approval.
     * @param slot the appointment slot to schedule the appointment.
     * @param patient the patient to schedule the appointment for.
     */
    public void scheduleAppointment(AppointmentSlot slot, Patient patient) {
        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.REQUESTED, slot.getTimeSlot(), slot.getDoctor().getDoctorId(), patient.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    /**
     * Cancels a scheduled appointment.
     * @param appointment the appointment to cancel.
     */
    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    /**
     * Reschedules an appointment to a new appointment slot.
     * @param initialAppointment the initial appointment to reschedule.
     * @param newSlot the new appointment slot.
     */
    public void rescheduleAppointment(Appointment initialAppointment, AppointmentSlot newSlot) {
        initialAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(initialAppointment);

        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.REQUESTED, newSlot.getTimeSlot(), newSlot.getDoctor().getDoctorId(), initialAppointment.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    public void acceptAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
    }

    public void declineAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public void updateAppointmentOutcome(Appointment appointment, String outcome) {
        // appointment.getOutcomeRecord().setOutcome(outcome);
        
        appointment.setStatus(AppointmentStatus.COMPLETED);
        appointmentRepository.save(appointment);
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
            if (isAppointmentSlotAvailable(slot, doctor)) {
                availableSlots.add(new AppointmentSlot(doctor, slot));
            }
        }

        return availableSlots;
    }

    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getItems().values()
            .stream().sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getDateTime().getDate().isBefore(LocalDate.now()) &&
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
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.getDateTime().getDate().equals(date) &&
            appointment.isScheduled()
        );
    }

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getScheduledAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getDateTime().getDate().isBefore(LocalDate.now()) &&
            appointment.isScheduled()
        )
        .stream()
        .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getDateTime().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Gets both upcoming {@code SCHEDULED} and {@code REQUESTED} appointments of a patient, 
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getUpcomingAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getDateTime().getDate().isBefore(LocalDate.now()) &&
            (appointment.isScheduled() || appointment.isRequested())
        )
        .stream()
        .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getDateTime().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    public List<Appointment> getFulfilledAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getDateTime().compareTo(b.getDateTime())).toList();
    }

    /**
     * Checks whether a time slot is available for an appointment with a doctor.
     * @param slot the time slot to check.
     * @param doctor the doctor to check the availability for.
     * @return whether the slot is available.
     */
    public boolean isAppointmentSlotAvailable(TimeSlot slot, Doctor doctor) {
        List<Appointment> scheduledAppointments = getScheduledAppointments(doctor, slot.getDateTime().toLocalDate());

        for (Appointment appointment : scheduledAppointments) {
            if (appointment.getDateTime().equals(slot)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Generates all possible time slots for appointments on a given date.
     * @param date the date to generate the time slots for.
     * @return the list of time slots.
     */
    public static List<TimeSlot> generateTimeSlots(LocalDate date) {
        // The time slots start at 9am, and the last slot ends at 6pm.
        final int startHour = 9;
        final int endHour = 18;
        final int slotDuration = 30;

        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (int hour = startHour; hour <= endHour; hour++) {
            for (int minute = 0; minute < 60; minute += slotDuration) {
                timeSlots.add(new TimeSlot(date.atTime(hour, minute)));
            }
        }

        return timeSlots;
    }
}
