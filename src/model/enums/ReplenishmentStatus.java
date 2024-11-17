package model.enums;

/**
 * The enum corresponding to the status of a {@link ReplenishmentRequest}
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum ReplenishmentStatus {
    /**
     * The possible replenishment statuses.
     */
    NULL("None"),
    PENDING("Pending"),
    APPROVED("Approved"),
    REJECTED("Rejected");


    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private final String value;

    /**
     * The constructor for the ReplenishmentStatus enum.
     * @param value the {@code String} value of the enum.
     */
    private ReplenishmentStatus(String value) {
        this.value = value;
    }

    /**
     * The getter for the {@code String} value of the enum.
     * @return the {@code String} value.
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
