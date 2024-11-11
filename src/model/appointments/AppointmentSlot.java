package model.appointments;

import model.users.Doctor;

public class AppointmentSlot {
    private Doctor doctor;
    private TimeSlot timeSlot;
    
    public AppointmentSlot(Doctor doctor, TimeSlot timeSlot) {
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public String getDoctorName() {
        return doctor.getName();
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public String getDate() {
        return timeSlot.getFormattedDate();
    }

    public String getTime() {
        return timeSlot.getFormattedTime();
    }
}
