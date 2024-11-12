package model.enums;

public enum MedicalService {
    CONSULTATION("Consultation"),
    BLOOD_TEST("Blood Test"),
    X_RAY("X-Ray"),
    MRI("MRI"),
    ULTRASOUND("Ultrasound"),
    VACCINATION("Vaccination"),
    PHYSIOTHERAPY("Physiotherapy"),
    SURGERY("Surgery"),
    PSYCHIATRY("Psychiatry");

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
