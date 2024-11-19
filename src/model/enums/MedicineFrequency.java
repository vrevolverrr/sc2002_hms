package model.enums;


/**
 * The enum corresponding to the frequency of a medicine.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum MedicineFrequency {
    /**
     * Consumed as needed.
     */
    AS_NEEDED("As Needed"),

    /**
     * Consumed after meals.
     */
    AFTER_MEALS("After Meals"),

    /**
     * Consumed before meals.
     */
    BEFORE_MEALS("Before Meals"),

    /**
     * Consumed daily.
     */
    DAILY("Daily"),

    /**
     * Consumed twice daily.
     */
    TWICE_DAILY("Twice Daily"),

    /**
     * Consumed thrice daily.
     */
    THRICE_DAILY("Thrice Daily"),

    /**
     * Consumed every other day.
     */
    EVERY_OTHER_DAY("Every Other Day"),


    /**
     * Consumed every 4 hours.
     */
    EVERY_4_HOURS("Every 4 Hours"),

    /**
     * Consumed every 6 hours.
     */
    EVERY_6_HOURS("Every 6 Hours"),

    /**
     * Consumed every 8 hours.
     */
    EVERY_8_HOURS("Every 8 Hours");

    /**
     * The value of the enum.
     */
    private String value;

    /**
     * The string value of the enum. This value is used for display.
     * @param value the string value of the enum.
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
