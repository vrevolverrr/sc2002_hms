package model.users;

import java.time.LocalDate;

import model.enums.Gender;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a pharmacist.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class Pharmacist extends User {
    /**
     * The constructor of a {@link Pharmacist}. Calls the constructor of {@link User}.
     * @param pharmacistId the unique ID of the pharmacist.
     * @param name the name of the pharmacist.
     * @param password the password of the pharmacist's user account.
     * @param gender the gender of the pharmacist.
     * @param dob the date of birth of the pharmacist.
     * @param phoneNumber the phone number of the pharmacist.
     * @param emailAddress the email address of the pharmacist.
     */
    public Pharmacist(String pharmacistId, String name, int age, String password, Gender gender, LocalDate dob, 
                      String phoneNumber, String emailAddress) {
        super(pharmacistId, UserRole.PHARMACIST, password, name, age, gender, dob, phoneNumber, emailAddress);
    }

    /**
     * Gets the ID of the pharmacist.
     * @return the ID of the pharmacist.
     */
    public String getPharmacistId() {
        return super.getId();
    }

    /**
     * Creates a copy of the current {@link Pharmacist} instance.
     * @return the exact copy (shallow) of the {@link Pharmacist}.
     */
    @Override
    public Pharmacist copy() {
        Pharmacist pharmacist = new Pharmacist(getPharmacistId(), getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress());
        pharmacist.setDefaultPassword(isDefaultPassword());
        
        return pharmacist;
    }

}


