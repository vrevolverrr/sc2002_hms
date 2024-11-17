package model.enums;


/**
 * The enum corresponding to the frequency of a {@link MedicineDosage}
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum MedicineFrequency {
    /**
     * The possible frequencies.
     */
    AS_NEEDED("As Needed"),

    AFTER_MEALS("After Meals"),
    BEFORE_MEALS("Before Meals"),

    DAILY("Daily"),
    TWICE_DAILY("Twice Daily"),
    THRICE_DAILY("Thrice Daily"),
    EVERY_OTHER_DAY("Every Other Day"),

    EVERY_4_HOURS("Every 4 Hours"),
    EVERY_6_HOURS("Every 6 Hours"),
    EVERY_8_HOURS("Every 8 Hours");

    
    private String value;

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private MedicineFrequency(String value) {
        this.value = value;
    }

    /**
     * Returns the {@code String} value of the enum.
     * @return the {@code String} value of the enum.
     */
    public String getValue() {
        return value;
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
