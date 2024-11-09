package model;

import java.time.LocalDate;

import model.enums.Gender;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a pharmacist.
 */
public class Pharmacist extends User {
    /**
     * The unique ID of the pharmacist.
     */
    private final String pharmacistId;

    /**
     * The license number of the pharmacist.
     */
    private String licenseNumber;

    /**
     * The years of experience the pharmacist has.
     */
    private int yearsExperience;

    /**
     * The constructor of a {@link Pharmacist}. Calls the constructor of {@link User}.
     * @param pharmacistId the unique ID of the pharmacist.
     * @param name the name of the pharmacist.
     * @param password the password of the pharmacist's user account.
     * @param gender the gender of the pharmacist.
     * @param dob the date of birth of the pharmacist.
     * @param phoneNumber the phone number of the pharmacist.
     * @param emailAddress the email address of the pharmacist.
     * @param licenseNumber the license number of the pharmacist.
     * @param yearsExperience the years of experience of the pharmacist.
     */
    public Pharmacist(String pharmacistId, String name, String password, Gender gender, LocalDate dob, 
                      String phoneNumber, String emailAddress, String licenseNumber, int yearsExperience) {
        super(pharmacistId, UserRole.PHARMACIST, password, name, gender, dob, phoneNumber, emailAddress);
        this.pharmacistId = pharmacistId;
        this.licenseNumber = licenseNumber;
        this.yearsExperience = yearsExperience;
    }

    /**
     * Gets the ID of the pharmacist.
     * @return the ID of the pharmacist.
     */
    public String getPharmacistId() {
        return pharmacistId;
    }

    /**
     * Gets the license number of the pharmacist.
     * @return the license number of the pharmacist.
     */
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the license number of the pharmacist.
     * @param licenseNumber the new license number of the pharmacist.
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Gets the years of experience of the pharmacist.
     * @return the years of experience of the pharmacist.
     */
    public int getYearsExperience() {
        return yearsExperience;
    }

    /**
     * Sets the years of experience of the pharmacist.
     * @param yearsExperience the new years of experience of the pharmacist.
     */
    public void setYearsExperience(int yearsExperience) {
        this.yearsExperience = yearsExperience;
    }

    /**
     * Creates a copy of the current {@link Pharmacist} instance.
     * @return the exact copy (shallow) of the {@link Pharmacist}.
     */
    @Override
    public Pharmacist copy() {
        return new Pharmacist(pharmacistId, getName(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getLicenseNumber(), getYearsExperience());
    }

}

