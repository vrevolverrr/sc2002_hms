package model;

import java.time.LocalDate;

import model.enums.BloodType;
import model.enums.Gender;
import model.enums.UserRole;

public class Doctor {

    // Private attributes
    private final String doctorId;
    private Specialisation specialisation;

    // Patient contructor, with all patient information as input parameters
    public Doctor(String doctorId, String name, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, Specialisation specialisation) { 
        super(doctorId, UserRole.DOCTOR, password, name, gender, dob, phoneNumber, emailAddress);
        this.doctorId = doctorId;
        this.specialisation = specialisation;
    }

    // Method: to retrieve information about the doctor -> functions are self-explanatory
    public String getDoctorId() {
        return doctorId;
    }

    public BloodType getSpecialisation() {
        return this.specialisation;
    }

    public void updateSpecialisation(Specialisation new_specialisation) {
        this.specialisation = new_specialisation;
    }

    @Override
    public Doctor copy() {
        return new Doctor(doctorId, getName(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getSpecialisation());
    }
}
