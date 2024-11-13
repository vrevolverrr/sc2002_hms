package model.enums;

public enum PrescriptionStatus {
    PENDING("Pending"),
    DISPENSED("Dispensed");

    private final String value;

    private PrescriptionStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
