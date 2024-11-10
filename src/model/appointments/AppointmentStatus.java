package model.appointments;

// Enum corresponding to the appointment status
public enum AppointmentStatus {
    SCHEDULED("Scheduled"),
    COMPLETED("Completed"),
    CANCELLED("Cancelled");

    private String value;

    private AppointmentStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}