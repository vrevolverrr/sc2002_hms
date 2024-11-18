package model.enums;

/**
 * The enum corresponding to the status of a Prescription.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum PrescriptionStatus {
    /**
     * The status of the prescription is pending.
     */
    PENDING("Pending"),

    /**
     * The status of the prescription is dispensed.
     */
    DISPENSED("Dispensed");

    private final String value;

    /**
     * The constructor for the {@link PrescriptionStatus} enum.
     * @param value the {@code String} value of the enum.
     */
    private PrescriptionStatus(String value) {
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
