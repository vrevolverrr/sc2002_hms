package model.enums;

public enum MedicineFrequency {
    DAILY("Daily"),
    AFTER_MEALS("After Meals"),
    WEEKLY("Weekly"),
    BEFORE_MEALS("Before Meals"),
    TWICE_DAILY("Twice Daily"),
    THRICE_DAILY("Thrice Daily"),
    EVERY_OTHER_DAY("Every Other Day"),
    MONTHLY("Monthly"),
    AS_NEEDED("As Needed"),
    EVERY_4_HOURS("Every 4 Hours"),
    EVERY_6_HOURS("Every 6 Hours"),
    EVERY_8_HOURS("Every 8 Hours");

    private String value;

    private MedicineFrequency(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
