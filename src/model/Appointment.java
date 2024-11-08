package model;

import model.enums.AppointmentStatus;
import java.time.LocalDate;
import java.time.LocalTime;

public class Appointment {
    // Private attributes
    private String appointmentId;
    private AppointmentStatus status; 
    private LocalDateTime dateTime;
    private String doctorID;
    private String patientID;

    // Appointment constructor
    public Appointment(String appointmentId, AppointmentStatus status, LocalDateTime dateTime, String doctorId, String patientId) {
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.status = status;
    }

    // Method to set appointment status
    public void setStatus(AppointmentStatus status) { 
        this.status = status; 
    }
    
    // Methods to get information about the appointment
    public String getDoctorId() { 
        return doctorId; 
    }
    
    public String getPatientId() { 
        return patientId; 
    }

    public AppointmentStatus getStatus() { 
        return status; 
    }

    public LocalDateTime getDateTime() { 
        return dateTime; 
    }

}
