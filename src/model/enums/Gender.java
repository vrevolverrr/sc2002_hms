package model.enums;

public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    private final String value;

    private Gender(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}
