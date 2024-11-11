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
     * Schedules an appointment for a patient.
     * @param slot the appointment slot to schedule the appointment.
     * @param patient the patient to schedule the appointment for.
     */
    public void scheduleAppointment(AppointmentSlot slot, Patient patient) {
        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.SCHEDULED, slot.getTimeSlot(), slot.getDoctor().getDoctorId(), patient.getPatientId());
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

        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.SCHEDULED, newSlot.getTimeSlot(), newSlot.getDoctor().getDoctorId(), initialAppointment.getPatientId());
        appointmentRepository.save(newAppointment);
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
            if (appointmentRepository.isAppointmentSlotAvailable(slot, doctor)) {
                availableSlots.add(new AppointmentSlot(doctor, slot));
            }
        }

        return availableSlots;
    }

    public List<Appointment> getAppointments(Doctor doctor) {
        return appointmentRepository.getUpcomingAppointmentsByDoctor(doctor);
    }

    /**
     * Gets the appointments of a doctor on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments(Doctor doctor, LocalDate date) {
        return appointmentRepository.getUpcomingAppointmentsByDateAndDoctor(date, doctor);
    }

    /**
     * Gets the upcoming appointments of the patient.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getAppointments(Patient patient) {
        return appointmentRepository.getUpcomingAppointmentsByPatient(patient);
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
