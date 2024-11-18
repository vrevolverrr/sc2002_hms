/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-27
 */

package model.enums;

import model.users.User;

/**
 * The enum corresponding to the role of a {@link User}
 */
public enum UserRole {
    /**
     * Patient role.
     */
    PATIENT("Patient"), 

    /**
     * Doctor role.
     */
    DOCTOR("Doctor"), 

    /**
     * Pharmacist role.
     */
    PHARMACIST("Pharmacist"), 

    /**
     * Admin role.
     */
    ADMIN("Admin");

    /**
     * The {@code String} value of the enum. This value is used for display.
     */
    private String value;

    /**
     * The constructor for the {@link UserRole} enum.
     * @param value the {@code String} value of the enum.
     */
    private UserRole(String value) {
        this.value = value;
    }

    /**
     * The getter for the {@code String} value of the enum.
     * @return the {@code String} value.
     */
    public String getValue() {
        return value;
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
