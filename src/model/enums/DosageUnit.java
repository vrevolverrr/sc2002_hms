package model.enums;

/**
 * The enum corresponding to the dosage unit of a {@link MedicineDosage}
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public enum DosageUnit {
    /**
     * The possible dosage units.
     */
    TABLET("tablet"),
    MILLILITER("ml"),
    TABLESPOON("tblsp"),
    TEASPOON("tsp"),
    DROP("drop"),
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
