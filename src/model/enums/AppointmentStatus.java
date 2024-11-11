package model.enums;

/**
 * The enum representing the status of an appointment.
 */
public enum AppointmentStatus {
    /**
     * The status of an appointment when it is requested and pending 
     * approval from the doctor.
     */
    REQUESTED("Requested"),

    /**
     * The status of an appointment when it is approved and scheduled.
     */
    SCHEDULED("Scheduled"),

    /**
     * The status of an appointment when it is fulfilled, but not yet completed
     * (ie: awaiting appointment outcome update).
     */
    FULFILLED("Fulfilled"),

    /**
     * The status of an appointment when it is completed.
     */
    COMPLETED("Completed"),

    /**
     * The status of an appointment when it is cancelled.
     * A cancelled appointment is not shown to the patient, but indicated to the doctor.
     */
    CANCELLED("Cancelled");
    
    final private String value;

    private AppointmentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}