/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-27
 */

package model.enums;

import model.users.Patient;

/**
 * The enum corresponding to the bloodtype of a {@link Patient}
 */
public enum BloodType {
    
    /**
     * A positive blood type.
     */
    A_POSITIVE("A+"), 
    
    /**
     * A negative blood type.
     */
    A_NEGATIVE("A-"),

    /**
     * B positive blood type.
     */
    B_POSITIVE("B+"), 
    
    /**
     * B negative blood type.
     */
    B_NEGATIVE("B-"),

    /**
     * O positive blood type.
     */
    O_POSITIVE("O+"), 
    
    /**
     * O negative blood type.
     */
    O_NEGATIVE("O-"), 

    /**
     * AB positive blood type.
     */
    AB_POSITIVE("AB+"), 
    
    /**
     * AB negative blood type.
     */
    AB_NEGATIVE("AB-");

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

    @Override
    public String toString() {
        return this.value;
    }
}