package controller;

import java.time.LocalDateTime;

import model.users.Doctor;

public class AppointmentSlot {
    private Doctor doctor;
    private LocalDateTime timeSlot;
    
    public AppointmentSlot(Doctor doctor, LocalDateTime timeSlot) {
        this.doctor = doctor;
        this.timeSlot = timeSlot;
    }

    public Doctor getDoctor() {
        return this.doctor;
    }

    public LocalDateTime getTimeSlot() {
        return this.timeSlot;
    }
}
