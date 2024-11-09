/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */

package model;

import java.time.LocalDate;

import model.enums.BloodType;
import model.enums.Gender;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a patient.
 */
public class Patient extends User {
    /**
     * The unique ID of the patient.
     */
    private final String patientId;

    /**
     * The blood type of the patient.
     */
    private BloodType bloodType;

    /**
     * The constructor of a {@link Patient}. Calls the constructor of {@link User}.
     * @param patientId the unique ID of the patient.
     * @param name the name of the patient.
     * @param age the age of the patient.
     * @param password the password of the patient's user account.
     * @param gender the gender of the patient.
     * @param dob the date of birth of the patient.
     * @param phoneNumber the phone number of the patient.
     * @param emailAddress the email address of the patient.
     * @param bloodType the blood type of the patient.
     */
    public Patient(String patientId, String name, int age, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, BloodType bloodType) {
        super(patientId, UserRole.PATIENT, password, name, age, gender, dob, phoneNumber, emailAddress);

        this.patientId = patientId;
        this.bloodType = bloodType;
    }

    /**
     * Gets the ID of the patient.
     * @return the ID of the patient.
     */
    public String getPatientId() {
        return patientId;
    }

    /**
     * Gets the blood type of the patient.
     * @return the blood type of the patient.
     */
    public BloodType getBloodType() {
        return this.bloodType;
    }

    /**
     * Sets the blood type of the patient.
     * @param bloodType the new blood type of the patient.
     */
    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Creates a copy of the current {@link Patient} instance.
     * @return the exact copy (shallow) of the {@link Patient}.
     */
    @Override
    public Patient copy() {
        return new Patient(getId(), getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getBloodType());
    }

}
