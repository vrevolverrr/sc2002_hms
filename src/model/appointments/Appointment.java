package model.appointments;

import model.BaseModel;
import model.enums.AppointmentStatus;

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
    private TimeSlot dateTime;

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
     */
    public Appointment(String appointmentId, AppointmentStatus status, TimeSlot dateTime, String doctorId, String patientId) {
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
     * Gets whether the appointment has been requested and pending approval.
     * @return whether the appointment is requested.
     */
    public boolean isRequested() {
        return status == AppointmentStatus.REQUESTED;
    }

    /**
     * Gets whether the appointment has been scheduled.
     * @return whether the appointment is scheduled.
     */
    public boolean isScheduled() {
        return status == AppointmentStatus.SCHEDULED;
    }

    /**
     * Gets whether the appointment is cancelled.
     * @return whether the appointment is cancelled.
     */
    public boolean isCancelled() {
        return status == AppointmentStatus.CANCELLED;
    }
    
    public boolean isFulfilled() {
        return status == AppointmentStatus.FULFILLED;
    }

    public boolean isCompleted() {
        return status == AppointmentStatus.COMPLETED;
    }

    /**
     * Gets the date and time of the appointment.
     * @return the date and time of the appointment.
     */
    public TimeSlot getDateTime() { 
        return dateTime; 
    }

    /**
     * Sets the date and time of the appointment.
     * @param newDateTime the new date and time of the appointment.
     */
    public void setDateTime(TimeSlot newDateTime) {
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
     * Sets the outcome record of the appointment.
     * @param outcome the new outcome record.
     */
    public void setOutcomeRecord(AppointmentOutcomeRecord outcome) {
        if (outcome != null) {
            this.status = AppointmentStatus.COMPLETED;
        }

        this.outcome = outcome;
    }

    /**
     * Creates a copy of the current {@link Appointment} instance.
     * @return the exact copy (shallow) of the {@link Appointment}.
     */
    @Override
    public Appointment copy() {
        final Appointment newAppt = new Appointment(getAppointmentId(), getStatus(), getDateTime(), getDoctorId(), getPatientId());
        newAppt.setOutcomeRecord(getOutcomeRecord());
        
        return newAppt;
    }

}
