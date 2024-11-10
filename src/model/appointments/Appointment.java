package model.appointments;

import model.BaseModel;
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
     * The outcome of the appointment.
     */
    private AppointmentOutcomeRecord outcome;

    /**
     * The constructor of an {@link Appointment}. Calls the constructor of {@link BaseModel}.
     * @param appointmentId
     * @param status
     * @param dateTime
     * @param doctorId
     * @param patientId
     * @param outcome
     */
    public Appointment(String appointmentId, AppointmentStatus status, LocalDateTime dateTime, String doctorId, String patientId) {
        super(appointmentId);

        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.status = status;

        this.outcome = null;
    }

    /**
     * Gets the ID of the appointment.
     * @return the ID of the appointment.
     */
    public String getAppointmentId() {
        return this.appointmentId;
    }

        /**
     * Gets the status of the appointment.
     * @return the status of the appointment.
     */
    public AppointmentStatus getStatus() { 
        return status; 
    }

    /**
     * Sets the status of the appointment.
     * @param status the new status of the appointment.
     */
    public void setStatus(AppointmentStatus status) { 
        this.status = status; 
    }

        /**
     * Gets the date and time of the appointment.
     * @return the date and time of the appointment.
     */
    public LocalDateTime getDateTime() { 
        return dateTime; 
    }

    /**
     * Sets the date and time of the appointment.
     * @param newDateTime the new date and time of the appointment.
     */
    public void setDateTime(LocalDateTime newDateTime) {
        this.dateTime = newDateTime;
    }
    
    /**
     * Sets the ID of the doctor assigned to the appointment.
     * @param newDoctorId the ID of the doctor.
     */
    public void getDoctorId(String newDoctorId) { 
        this.doctorId = newDoctorId; 
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
     * Sets the ID of the patient assigned to the appointment.
     * @param newPatientId
     */
    public void setPatientId(String newPatientId) {
        this.patientId = newPatientId;
    }

    /**
     * Gets the outcome record of the appointment.
     * @return the appointment outcome record.
     */
    public AppointmentOutcomeRecord getOutcomeRecord() {
        return outcome;
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
