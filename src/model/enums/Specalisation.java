package model.enums;

public enum Specialisation {
    // Enum corresponding to the doctor's specialisation
    CARDIOLOGIST("Cardiologist"),
    SURGEON("Surgeon"),
    PEDIATRIST("Pediatrist"),
    NEUROLOGIST("Neurologist"),
    RADIOLOGIST("Radiologist"),
    DERMATOLOGIST("Dermatologist"),
    GYNECOLOGIST("Gynecologist");

    private String value;

    // Constructor for the enum
    private Specialisation(String value) {
        this.value = value;
    }

    // Return specialisation
    public String getValue() {
        return this.value;
    }
}
