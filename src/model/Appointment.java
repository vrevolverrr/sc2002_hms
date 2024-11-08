package model;

import model.enums.AppointmentStatus;
import java.time.LocalDateTime;

/**
 * The class representing a medical appointment.
 */
public class Appointment extends BaseModel {
    /**
     * The ID of the appointment.
     */
    private String appointmentId;

    /**
     * The status of the appointment.
     */
    private AppointmentStatus status; 

    /**
     * The date and time of the appointment.
     */
    private LocalDateTime dateTime;

    /**
     * The IDs of the doctor assigned to the appointment.
     */
    private String doctorId;

    /**
     * The ID of the patient assigned to the appointment.
     */
    private String patientId;

    /**
     * The constructor of an {@link Appointment}. Calls the constructor of {@link BaseModel}.
     * @param appointmentId
     * @param status
     * @param dateTime
     * @param doctorId
     * @param patientId
     */
    public Appointment(String appointmentId, AppointmentStatus status, LocalDateTime dateTime, String doctorId, String patientId) {
        super(appointmentId);

        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.status = status;
    }

    /**
     * Gets the ID of the appointment.
     * @return the ID of the appointment.
     */
    public String getAppointmentId() {
        return this.appointmentId;
    }

    /**
     * Sets the status of the appointment.
     * @param status the new status of the appointment.
     */
    public void setStatus(AppointmentStatus status) { 
        this.status = status; 
    }
    
    /**
     * Gets the ID of the doctor assigned to the appointment.
     * @return the ID of the doctor.
     */
    public String getDoctorId() { 
        return doctorId; 
    }
    
    /**
     * Gets the ID of the patient assigned to the appointment.
     * @return the ID of the patient.
     */
    public String getPatientId() { 
        return patientId; 
    }

    /**
     * Gets the status of the appointment.
     * @return the status of the appointment.
     */
    public AppointmentStatus getStatus() { 
        return status; 
    }

    /**
     * Gets the date and time of the appointment.
     * @return the date and time of the appointment.
     */
    public LocalDateTime getDateTime() { 
        return dateTime; 
    }

    /**
     * Creates a copy of the current {@link Appointment} instance.
     * @return the exact copy (shallow) of the {@link Appointment}.
     */
    @Override
    public Appointment copy() {
        return new Appointment(appointmentId, status, dateTime, doctorId, patientId);
    }

}
