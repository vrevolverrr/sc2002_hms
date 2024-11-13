package model.enums;

public enum ReplenishmentStatus {
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

    @Override
    public String toString() {
        return this.value;
    }
}
