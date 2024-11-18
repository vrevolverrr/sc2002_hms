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
         * @param name the name of the {@link Doctor}.
         * @param age the age of the {@link Doctor}.
         * @param password the password of the {@link Doctor}.
         * @param gender the gender of the {@link Doctor}.
         * @param dob the date of birth of the {@link Doctor}.
         * @param emailAddress the email address of the {@link Doctor}.
         * @param phoneNumber the phone number of the {@link Doctor}.
         * @param specialisation the specialisation of the {@link Doctor}.
         */
        public void addDoctor(String name, int age, String password, Gender gender, LocalDate dob,
                String emailAddress, String phoneNumber, Specialisation specialisation);

        /**
         * Add a admin to the system
         * @param name the name of the {@link Admin}.
         * @param age the age of the {@link Admin}.
         * @param password the password of the {@link Admin}.
         * @param gender the gender of the {@link Admin}.
         * @param dob the date of birth of the {@link Admin}.
         * @param emailAddress the email address of the {@link Admin}.
         * @param phoneNumber the phone number of the {@link Admin}.
         */
        public void addAdmin(String name, int age, String password, Gender gender, LocalDate dob, 
                String emailAddress, String phoneNumber);

        /**
         * Add a pharmacist to the system
         * @param name the name of the {@link Pharmacist}.
         * @param age the age of the {@link Pharmacist}.
         * @param password the password of the {@link Pharmacist}.
         * @param gender the gender of the {@link Pharmacist}.
         * @param dob the date of birth of the {@link Pharmacist}.
         * @param emailAddress the email address of the {@link Pharmacist}.
         * @param phoneNumber the phone number of the {@link Pharmacist}.
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
