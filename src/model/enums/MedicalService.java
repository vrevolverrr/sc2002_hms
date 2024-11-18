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
     * Consultation service.
     */
    CONSULTATION("Consultation"),

    /**
     * Blood test service.
     */
    BLOOD_TEST("Blood Test"),

    /**
     * X-ray service.
     */
    X_RAY("X-Ray"),

    /**
     * MRI service.
     */
    MRI("MRI"),

    /**
     * Ultrasound service.
     */
    ULTRASOUND("Ultrasound"),

    /**
     * Vaccination service.
     */
    VACCINATION("Vaccination"),

    /**
     * Physiotherapy service.
     */
    PHYSIOTHERAPY("Physiotherapy"),
    
    /**
     * Surgery service.
     */
    SURGERY("Surgery"),

    /**
     * Psychiatry service.
     */
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
