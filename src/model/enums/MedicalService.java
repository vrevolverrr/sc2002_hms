package model.enums;

public enum MedicalService {
    CONSULTATION("Consultation"),
    X_RAY("X-Ray"),
    BLOOD_TEST("Blood Test"),
    MRI("MRI"),
    ULTRASOUND("Ultrasound"),
    ECG("ECG"),
    VACCINATION("Vaccination"),
    PHYSIOTHERAPY("Physiotherapy"),
    SURGERY("Surgery"),
    DENTAL("Dental"),
    PSYCHIATRY("Psychiatry"),
    NUTRITION("Nutrition");

    private MedicalService(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
