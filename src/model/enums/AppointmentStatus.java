package model.enums;

/**
 * The enum representing the status of an appointment.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
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

    /**
     * The constructor for the {@link AppointmentStatus} enum.
     * @param value the {@code String} value of the enum.
     */
    private AppointmentStatus(String value) {
        this.value = value;
    }

    /**
     * Gets the {@code String} value of the enum.
     * @return the {@code String} value of the enum.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Returns the {@code String} value of the enum.
     * @return the {@code String} value of the enum.
     */
    @Override
    public String toString() {
        return this.value;
    }
}