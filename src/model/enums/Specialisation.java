package model.enums;

/**
 * The enum corresponding to the different specialisations of a {@link Doctor}.
 */
public enum Specialisation {
    CARDIOLOGIST("Cardiologist"),
    SURGEON("Surgeon"),
    PEDIATRIST("Pediatrist"),
    NEUROLOGIST("Neurologist"),
    RADIOLOGIST("Radiologist"),
    DERMATOLOGIST("Dermatologist"),
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
     * @return
     */
    public String getValue() {
        return this.value;
    }
}
