package model.users;

import java.time.LocalDate;

import model.appointments.TimeSlot;
import model.availability.Availability;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a doctor.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class Doctor extends User {
    /**
     * The specialisation of the doctor.
     */
    private Specialisation specialisation;

    /**
     * The availability of the doctor.
     */
    Availability availability;

    /**
     * Constructor for a {@link Doctor}. Calls the constructor of {@link User}.
     * @param doctorId the ID of the doctor.
     * @param name the name of the doctor.
     * @param age the age of the doctor.
     * @param password the password of the doctor.
     * @param gender the gender of the doctor.
     * @param dob the date of birth of the doctor.
     * @param phoneNumber the phone number of the doctor.
     * @param emailAddress the email address of the doctor.
     * @param specialisation the specialisation of the doctor.
     */
    public Doctor(String doctorId, String name, int age, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, Specialisation specialisation) { 
        super(doctorId, UserRole.DOCTOR, password, name, age, gender, dob, phoneNumber, emailAddress);
                
        this.specialisation = specialisation;
        this.availability = new Availability();
    }

    /**
     * Gets the ID of the doctor.
     * @return the ID of the doctor.
     */
    public String getDoctorId() {
        return super.getId();
    }

    /**
     * Sets the ID of the doctor.
     * @param id the ID of the doctor.
     */
    public void setDoctorId(String id) {
        super.setId(id);
    }

    /**
     * Get the specialisation of the doctor.
     * @return the specialisation of the doctor.
     */
    public Specialisation getSpecialisation() {
        return this.specialisation;
    }

    /**
     * Set the specialisation of the doctor. 
     * @param specialisation the specialisation of the doctor.
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    /**
     * Get the availability of the doctor.
     * @return the availability of the doctor.
     */
    public Availability getAvailability() {
        return availability.copy();
    }

    /**
     * Set the availability of the doctor.
     * @param availability the availability of the doctor.
     */
    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
    /**
     * Checks if the doctor is available at a specific time slot.
     * @param slot the time slot to check.
     * @return true if the doctor is available, false otherwise.
     */
    public boolean isAvailable(TimeSlot slot) {
        return availability.isAvailable(slot);
    }

    /**
     * Creates a copy of the current {@link Doctor} instance.
     * @return the exact copy (shallow) of the {@link Doctor}.
     */
    @Override
    public Doctor copy() {
        Doctor newDoctor = new Doctor(getDoctorId(), getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getSpecialisation());
        newDoctor.setAvailability(getAvailability());
        newDoctor.setDefaultPassword(isDefaultPassword());

        return newDoctor;
    }
}
