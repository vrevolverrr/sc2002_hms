/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-27
 */

package model.enums;

import model.Patient;

/**
 * The enum corresponding to the bloodtype of a {@link Patient}
 */
public enum BloodType {
    A_POSITIVE("A+"), A_NEGATIVE("A-"),
    B_POSITIVE("B+"), B_NEGATIVE("B-"),
    O_POSITIVE("O+"), O_NEGATIVE("O-"), 
    AB_POSITIVE("AB+"), AB_NEGATIVE("AB-");

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private final String value;

     /**
     * The constructor for the {@link BloodType} enum.
     * @param value the {@code String} value of the enum.
     */
    private BloodType(String value) {
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