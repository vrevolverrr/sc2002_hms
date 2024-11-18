/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */

package model.enums;

/**
 * The enum corresponding to the different genders of a user.
 */
public enum Gender {
    /**
     * Male gender.
     */
    MALE("Male"),

    /**
     * Female gender.
     */
    FEMALE("Female");

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
     * Returns the {@code String} value of the enum.
     * @return the {@code String} value of the enum.
     */
    @Override
    public String toString() {
        return this.value;
    }
}
