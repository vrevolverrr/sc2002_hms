/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */

package model.users;

import java.time.LocalDate;
import java.util.Objects;

import model.enums.BloodType;
import model.enums.Gender;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a patient.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-28
 */
public class Patient extends User {
    /*
     * The weight of the patient in kg.
     */
    private double weight;

    /**
     * The height of the patient in cm.
     */
    private double height;

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
     * @param weight the weight of the patient in kg.
     * @param height the height of the patient in cm.
     * @param phoneNumber the phone number of the patient.
     * @param emailAddress the email address of the patient.
     * @param bloodType the blood type of the patient.
     */
    public Patient(
        String patientId, String name, int age, String password, 
        Gender gender, LocalDate dob, double weight, double height,
        String phoneNumber, String emailAddress, BloodType bloodType) {
        
        super(patientId, UserRole.PATIENT, password, name, age, gender, dob, phoneNumber, emailAddress);

        this.weight = weight;
        this.height = height;
        this.bloodType = bloodType;
    }

    /**
     * Gets the ID of the patient.
     * @return the ID of the patient.
     */
    public String getPatientId() {
        return super.getId();
    }

    /**
     * Sets the ID of the patient.
     * @param id the ID of the patient.
     */
    public void setPatientId(String id) {
        super.setId(id);
    }

    /**
     * Gets the weight (in kg) of the patient.
     * @return the weight of the patient.
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * Sets the weight (in kg) of the patient.
     * @param weight the new weight of the patient.
     */
    public void setWeight(double weight) {
        this.weight = weight;
    }

    /**
     * Gets the height (in cm) of the patient.
     * @return the height of the patient.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Sets the height of the patient.
     * @param height the new height (in cm) of the patient.
     */
    public void setHeight(double height) {
        this.height = height;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Patient)) {
            return false;
        }

        Patient patient = (Patient) obj;
        // Compare the ID of the patient.
        // from the repository's perspective, same ID means same object.
        return patient.getId().equals(getId());
    }

    /**
     * Generates a hash code for the {@link Patient} object.
     * @return the hash code generated based on the ID of the patient.
     */
    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }

    /**
     * Creates a copy of the current {@link Patient} instance.
     * @return the exact deep copy of the {@link Patient}.
     */
    @Override
    public Patient copy() {
        Patient patient = new Patient(
            getId(), getName(), getAge(), getPassword(), getGender(), getDob(),
            getWeight(), getHeight(),
            getPhoneNumber(), getEmailAddress(), getBloodType());

        patient.setDefaultPassword(isDefaultPassword());
        return patient;
    }

}
