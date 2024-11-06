package model;

import java.time.LocalDate;

import model.enums.Gender;
import model.enums.UserRole;

/**
 * @author Joyce
 * @version 1.0
 * @since 2024-11-03
 */


public class Admin extends User{

    /**
     * The unique ID of the patient.
     */
    private final String adminId;

    /**
     * The constructor of a {@link Admin}. Calls the constructor of {@link User}.
     * @param adminId the unique ID of the admin.
     * @param name the name of the admin.
     * @param password the password of the admin's user account.
     * @param gender the gender of the admin.
     * @param dob the date of birth of the admin.
     * @param phoneNumber the phone number of the admin.
     * @param emailAddress the email address of the admin.
     * @param age the age of the admin.
     */
    
    public Admin(String adminId, String name, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, String age) {
        super(adminId, UserRole.ADMIN, password, name, gender, dob, phoneNumber, emailAddress, age);
        this.adminId = adminId;
    }

    /**
     * Gets the ID of the admin.
     * @return the ID of the admin.
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * Creates a copy of the current {@link Admin} instance.
     * @return the exact copy (shallow) of the {@link Admin}.
     */
    @Override
    public Admin copy() {
        return new Admin(adminId, getName(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getAge());
    }

}
