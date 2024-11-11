/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-27
 */

package model.users;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.BaseModel;
import model.enums.Gender;
import model.enums.UserRole;

/**
 * The base abstract class of a user. Subclasses of {@link User} should 
 * correspond to the specific users of the application and implement the 
 * {@link BaseModel#copy()} method.
 */
public abstract class User extends BaseModel {
    /**
     * The {@link UserRole} of the user that decides the user's privilege. 
     */
    private final UserRole role;

    /**
     * The hashed password of the user. 
     */
    private String password;

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

    /**
     * The age of the user.
    */
    private int age;

    /**
     * The constructor of a {@link User}. Calls the constructor of {@link BaseModel}.
     * @param id the unique ID of the user.
     * @param role the role of the user.
     * @param password the password of the user account.
     * @param name the name of the user.
     * @param gender the gender of the user.
     * @param dob the date of birth of the user.
     * @param phoneNumber the phone number of the user.
     * @param emailAddress the email address of the user.
     * @param age the age of the user.
     */
    public User(String id, UserRole role, String password, String name, int age, Gender gender, LocalDate dob, String phoneNumber, String emailAddress) {
        super(id);
    
        this.role = role;
        this.password = password;

        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.age = age;
    }
    /**
     * Gets the role of the user.
     * @return the role of the user.
     */
    public UserRole getRole() {
        return this.role;
    }
        
    /**
     * Gets the password of the user.
     * @return the password of the user.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password of the user.
     * @param password the new password of the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the name of the user.
     * @return the name of the user.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name of the user.
     * @param name the new name of the user.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the gender of the user.
     * @return the gender of the user.
     */
    public Gender getGender() {
        return this.gender;
    }

    /**
     * Sets the gender of the user.
     * @param gender the new gender of the user.
     */
    public void setGender(Gender gender) {
        this.gender = gender;
    }

    /**
     * Gets the date of birth of the user.
     * @return the date of birth of the user.
     */
    public LocalDate getDob() {
        return this.dob;
    }

    /**
     * Sets the date of birth of the user.
     * @param dob the new date of birth of the user.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Gets the formatted string for the date of birth of the user.
     * @return the formatted string of DOB.
     */
    public String getDobString() {
        return this.dob.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Gets the phone number of the user.
     * @return the phone number of the user.
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * Sets the phone number of the user.
     * @param phoneNumber the new phonne number of the user.
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * Gets the email address of the user.
     * @return the email address of the user.
     */
    public String getEmailAddress() {
        return this.emailAddress;
    }

    /**
     * Sets the email address of the user.
     * @param emailAddress the new email address of the user.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    /**
     * Gets the age of the user.
     * @return the age of the user.
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Sets the age of the user.
     * @param age the new age of the user.
     */
    public void setAge(int age) {
        this.age = age;
    }
}
