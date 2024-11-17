package model.appointments;

import model.users.Doctor;

/**
 * Represents an appointment slot that links a {@link Doctor} with a {@link TimeSlot}.
 * 
 * This class encapsulates the doctor assigned to the appointment and the time slot 
 * allocated for it. It provides methods to retrieve detailed information such as the 
 * doctor's name, date, and time of the appointment.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class AppointmentSlot {
    /**
     * The doctor assigned to the appointment.
     */
    private Doctor doctor;

    /**
     * The time slot allocated for the appointment.
     */
    private TimeSlot timeSlot;
    
    /**
     * Constructs an instance of {@link AppointmentSlot}.
     * 
     * @param doctor the {@link Doctor} assigned to the appointment.
     * @param timeSlot the {@link TimeSlot} allocated for the appointment.
     */
    public AppointmentSlot(Doctor doctor, TimeSlot timeSlot) {
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }

    /**
     * Gets the {@link Doctor} assigned to the appointment slot.
     * 
     * @return the {@link Doctor} assigned to the appointment.
     */
    public Doctor getDoctor() {
        return this.doctor;
    }

    /**
     * Gets the name of the {@link Doctor} assigned to the appointment slot.
     * 
     * @return a {@link String} representing the doctor's name.
     */
    public String getDoctorName() {
        return doctor.getName();
    }

    /**
     * Gets the {@link TimeSlot} allocated for the appointment.
     * 
     * @return the {@link TimeSlot} of the appointment.
     */
    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    /**
     * Gets the formatted date of the appointment from the {@link TimeSlot}.
     * 
     * @return a {@link String} representing the formatted date of the appointment.
     */
    public String getDate() {
        return timeSlot.getFormattedDate();
    }

    /**
     * Gets the formatted time of the appointment from the {@link TimeSlot}.
     * 
     * @return a {@link String} representing the formatted time of the appointment.
     */
    public String getTime() {
        return timeSlot.getFormattedTime();
    }
}
