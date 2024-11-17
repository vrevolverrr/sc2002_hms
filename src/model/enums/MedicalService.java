package model.enums;

/**
 * The enum corresponding to the medical services provided by a {@link model.appointments.Appointment}.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public enum MedicalService {
    /**
     * The possible medical services.
     */
    CONSULTATION("Consultation"),
    BLOOD_TEST("Blood Test"),
    X_RAY("X-Ray"),
    MRI("MRI"),
    ULTRASOUND("Ultrasound"),
    VACCINATION("Vaccination"),
    PHYSIOTHERAPY("Physiotherapy"),
    SURGERY("Surgery"),
    PSYCHIATRY("Psychiatry");

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private MedicalService(String value) {
        this.value = value;
    }

    private String value;

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
