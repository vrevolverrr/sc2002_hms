package controller.interfaces;

import java.time.LocalDate;
import java.util.List;

import model.enums.Gender;
import model.enums.Specialisation;
import model.users.User;

/**
 * This interface provides methods to manage staff in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IStaffManager {

        /**
         * Retrieves all staff.
         * 
         * @return a {@link List} {@link User} of all staff.
         */    
        public List<User> getAllStaff();

        /**
         * For a given keyword, retrieves all staff whose name contains the keyword.
         * 
         * @param keyword the keyword to search for.
         * @return a {@link List} {@link User} of all staff whose name contains the keyword.
         */
        public List<User> findStaffByKeywords(String keyword);

        /**
         * Adds a doctor to the system.
         * 
         * @param name the name of the doctor.
         * @param age the age of the doctor.
         * @param password the password of the doctor.
         * @param gender the gender of the doctor.
         * @param dob the date of birth of the doctor.
         * @param emailAddress the email address of the doctor.
         * @param phoneNumber the phone number of the doctor.
         * @param specialisation the specialisation of the doctor.
         */
        public void addDoctor(String name, int age, String password, Gender gender, LocalDate dob,
                String emailAddress, String phoneNumber, Specialisation specialisation);

        /**
         * Add a admin to the system
         * @param name the name of the admin.
         * @param age the age of the admin.
         * @param password the password of the admin.
         * @param gender the gender of the admin.
         * @param dob the date of birth of the admin.
         * @param emailAddress the email address of the admin.
         * @param phoneNumber the phone number of the admin.
         */
        public void addAdmin(String name, int age, String password, Gender gender, LocalDate dob, 
                String emailAddress, String phoneNumber);

        /**
         * Add a pharmacist to the system
         * @param name the name of the pharmacist.
         * @param age the age of the pharmacist.
         * @param password the password of the pharmacist.
         * @param gender the gender of the pharmacist.
         * @param dob the date of birth of the pharmacist.
         * @param emailAddress the email address of the pharmacist.
         * @param phoneNumber the phone number of the pharmacist.
         */
        public void addPharmacist(String name, int age, String password, Gender gender, LocalDate dob, 
                String emailAddress, String phoneNumber);

        /**
         * Update a staff member.
         * @param user the {@link User} staff member to update.
         */
        public void updateStaff(User user);

        /**
         * Delete a staff member.
         * @param user the {@link User} staff member to delete.
         */
        public void deleteStaff(User user);
}
