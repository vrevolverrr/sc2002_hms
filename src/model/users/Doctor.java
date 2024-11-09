package model.users;

import java.time.LocalDate;

import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a doctor.
 */
public class Doctor extends User {

    /**
     * The unique ID of the doctor.
     */
    private String doctorId;

    /**
     * The specialisation of the doctor.
     */
    private Specialisation specialisation;

    /**
     * Constructor for a {@link Doctor}. Calls the constructor of {@link User}.
     * @param doctorId
     * @param name
     * @param age
     * @param password
     * @param gender
     * @param dob
     * @param phoneNumber
     * @param emailAddress
     * @param specialisation
     */
    public Doctor(String doctorId, String name, int age, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, Specialisation specialisation) { 
        super(doctorId, UserRole.DOCTOR, password, name, age, gender, dob, phoneNumber, emailAddress);
                
        this.doctorId = doctorId;
        this.specialisation = specialisation;
    }

    /**
     * Gets the ID of the doctor.
     * @return the ID of the doctor.
     */
    public String getDoctorId() {
        return doctorId;
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
     * @param specialisation
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    @Override
    public Doctor copy() {
        return new Doctor(doctorId, getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getSpecialisation());
    }
}
