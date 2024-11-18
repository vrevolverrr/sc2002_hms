package model.enums;

/**
 * The enum corresponding to the dosage unit of a medication.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public enum DosageUnit {
    
    /**
     * The dosage unit of a medication in tablets.
     */
    TABLET("tablet"),

    /**
     * The dosage unit of a medication in milliliters.
     */
    MILLILITER("ml"),

    /**
     * The dosage unit of a medication in tablespoonful.
     */
    TABLESPOON("tblsp"),

    /**
     * The dosage unit of a medication in teaspoonful.
     */
    TEASPOON("tsp"),

    /**
     * The dosage unit of a medication in drops.
     */
    DROP("drop"),

    /**
     * The dosage unit of a medication in spray.
     */
    SPRAY("spray");

    private final String value;

    /**
     * The constructor for the {@link DosageUnit} enum.
     * @param value the {@code String} value of the enum.
     */
    private DosageUnit(String value) {
        this.value = value;
    }

    /**
     * Gets the {@code String} value of the enum.
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
