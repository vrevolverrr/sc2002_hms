package model.enums;

/**
 * The enum corresponding to the different specialisations of a doctor.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum Specialisation {
    /**
     * The specialisation of a cardiologist.
     */
    CARDIOLOGIST("Cardiologist"),

    /**
     * The specialisation of a dentist.
     */
    SURGEON("Surgeon"),

    /**
     * The specialisation of a general practitioner.
     */
    PEDIATRIST("Pediatrist"),

    /**
     * The specialisation of a neurologist.
     */
    NEUROLOGIST("Neurologist"),

    /**
     * The specialisation of a radiologist.
     */
    RADIOLOGIST("Radiologist"),

    /**
     * The specialisation of a dermatologist.
     */
    DERMATOLOGIST("Dermatologist"),

    /**
     * The specialisation of a gynecologist.
     */
    GYNECOLOGIST("Gynecologist");

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private String value;

    /**
     * The constructor for the Specialisation enum.
     * @param value
     */
    private Specialisation(String value) {
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
