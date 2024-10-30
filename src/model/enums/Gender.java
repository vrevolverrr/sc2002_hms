/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */

package model.enums;

/**
 * The enum corresponding to the different genders of a {@link User}.
 */
public enum Gender {
    MALE("Male"),
    FEMALE("Female"),
    OTHER("Other");

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private final String value;

    /**
     * The constructor for the Gender enum.
     * @param value the {@code String} value of the enum.
     */
    private Gender(String value) {
        this.value = value;
    }

    /**
     * The getter for the {@code String} value of the enum.
     * @return the {@code String} value.
     */
    public String getValue() {
        return this.value;
    }
}
