package model;

import java.time.LocalDate;

import model.enums.BloodType;
import model.enums.Gender;
import model.enums.UserRole;

public class Patient extends User {
    private final String patientId;
    private BloodType bloodType;

    public Patient(String patientId, String name, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, BloodType bloodType) {
        super(patientId, UserRole.PATIENT, password, name, gender, dob, phoneNumber, emailAddress);
        this.patientId = patientId;
        this.bloodType = bloodType;
    }

    public String getPatientId() {
        return patientId;
    }

    public BloodType getBloodType() {
        return this.bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }

    /**
     * Creates a copy of the current {@link Patient} instance.
     */
    @Override
    public Patient copy() {
        return new Patient(patientId, getName(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getBloodType());
    }

}
