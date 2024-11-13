package model.enums;

public enum DosageUnit {
    TABLET("tablet"),
    MILLILITER("ml"),
    TABLESPOON("tblsp"),
    TEASPOON("tsp"),
    DROP("drop"),
    SPRAY("spray");

    private final String value;

    private DosageUnit(String value) {
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
