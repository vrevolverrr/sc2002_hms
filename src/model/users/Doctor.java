package model.users;

import java.time.LocalDate;

import model.appointments.TimeSlot;
import model.availability.Availability;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a doctor.
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
     * @param specialisation
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    public Availability getAvailability() {
        return availability.copy();
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }
    
    public boolean isAvailable(TimeSlot slot) {
        return availability.isAvailable(slot);
    }

    @Override
    public Doctor copy() {
        Doctor newDoctor = new Doctor(getDoctorId(), getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getSpecialisation());
        newDoctor.setAvailability(getAvailability());
        newDoctor.setDefaultPassword(isDefaultPassword());

        return newDoctor;
    }
}
