package model;

import java.time.LocalDate;

import model.enums.Gender;
import model.enums.UserRole;

public abstract class User extends BaseModel {
    /**
     * The unique user ID of the user. 
     */
    private final String userId;

    /**
     * The {@link UserRole} of the user that decides the user's privilege. 
     */
    private final UserRole role;

    /**
     * The hashed password of the user. 
     */
    private final String password;

    /**
     * The name of the user.
    */
    private String name;

    /**
     * The gender of the user.
    */
    private Gender gender;

    /**
     * The date of birth of the user.
     */
    private LocalDate dob;

    /**
     * The phone number of the user.
    */
    private String phoneNumber;

    /**
     * The email address of the user.
    */
    private String emailAddress;

    public User(String id, UserRole role, String password, String name, Gender gender, LocalDate dob, String phoneNumber, String emailAddress) {
        super(id);
        
        this.userId = id;

        this.role = role;
        this.password = password;

        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
    }

    public String getUserId() {
        return this.userId;
    }

    public UserRole getRole() {
        return this.role;
    }
        
    public String getPassword() {
        return password;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return this.gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDate getDob() {
        return this.dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return this.emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
