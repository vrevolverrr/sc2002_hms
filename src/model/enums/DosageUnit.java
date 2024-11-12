package model.enums;

public enum DosageUnit {
    TABLESPOON("tblsp"),
    TEASPOON("tsp"),
    MILLILITER("ml"),
    DROP("drop"),
    TABLET("tablet"),
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
