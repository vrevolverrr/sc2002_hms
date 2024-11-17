package controller;

import java.time.LocalDate;
import java.util.List;

import controller.interfaces.IStaffManager;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;
import model.users.Admin;
import model.users.Doctor;
import model.users.Pharmacist;
import model.users.User;
import repository.DoctorRepository;
import repository.PharmacistRepository;
import repository.UserRepository;

/**
 * Manages operations related to staff members.
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class StaffManager implements IStaffManager {
    /**
     * Repository for accessing user data.
     */
    private final UserRepository userRepository;
 
    /**
     * Constructs a new instance of {@link StaffManager}.
     */
    public StaffManager(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Retrieves all staff members.
     *
     * @return a list of {@link User} of all staff members.
     */
    public List<User> getAllStaff() {
        return userRepository.findBy((user) -> isStaff(user));
    }

    /**
     * Finds and retrieves a list of staff members whose attributes match the specified keyword.
     * This method filters staff based on the following conditions:
     * - If the keyword is "male", it retrieves all male staff members.
     * - If the keyword is "female", it retrieves all female staff members.
     * - For any other keyword, it performs a case-insensitive search across concatenated 
     *   staff attributes, including their ID, name, age, role, date of birth, and email address.
     * 
     * The results are filtered to include only users identified as staff and are sorted by 
     * their IDs in ascending order.
     *
     * @param keyword the keyword to filter staff members; case-insensitive and trimmed of whitespace.
     * @return a list of {@link User} instances representing staff members that match the given keyword.
     */
    public List<User> findStaffByKeywords(String keyword) {
        final String keywords = keyword.trim().toLowerCase();
        
        if (keywords.equals("male")) {
            return userRepository.findBy((user) -> 
                user.getGender() == Gender.MALE && isStaff(user));
        }

        if (keywords.equals("female")) {
            return userRepository.findBy((user) -> 
                user.getGender() == Gender.FEMALE && isStaff(user));
        }

        return userRepository.findBy((user) -> 
        String.format("%s %s %s %s %s %s", 
            user.getId().toLowerCase(), user.getName(), user.getAge(), 
            user.getRole(), user.getDob(), user.getEmailAddress())
        .toLowerCase().contains(keywords) && isStaff(user)
        )
        .stream().sorted((a, b) -> a.getId().compareTo(b.getId()))
        .toList();
    }

    /**
     * Adds a new {@link Doctor} to the staff.
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
            String emailAddress, String phoneNumber, Specialisation specialisation) {
        
        final Doctor user = new Doctor(null, name, age, password, gender, dob, phoneNumber, emailAddress, specialisation);
        final DoctorRepository repository = new DoctorRepository(userRepository);
        
        repository.save(user);
    }

    /**
     * Adds a new {@link Admin} to the staff.
     *
     * @param name the name of the admin.
     * @param age the age of the admin.
     * @param password the password of the admin.
     * @param gender the gender of the admin.
     * @param dob the date of birth of the admin.
     * @param emailAddress the email address of the admin.
     * @param phoneNumber the phone number of the admin.
     */
    public void addAdmin(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber) {
       
        final Admin user = new Admin(null, name, age, password, gender, dob, phoneNumber, emailAddress);
        final AdminRepository repository = new AdminRepository(userRepository);

        repository.save(user);
    }

    public void addPharmacist(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber) {

        final Pharmacist user = new Pharmacist(null, name, age, password, gender, dob, phoneNumber, emailAddress);   
        final PharmacistRepository repository = new PharmacistRepository(userRepository);
        
        repository.save(user);
    }

    /**
     * Updates the details of a staff member.
     *
     * @param user the staff member.
     */
    public void updateStaff(User user) {
        userRepository.save(user);
    }
    
    /**
     * Deletes a staff member.
     *
     * @param user the staff member.
     */
    public void deleteStaff(User user) {
        userRepository.deleteById(user.getId());
    }

    /**
     * Checks if a user is a staff member.
     *
     * @param user the {@link User}.
     * @return true if the user is a staff member, false otherwise.
     */
    public static boolean isStaff(User user) {
        UserRole[] staffRoles = {UserRole.ADMIN, UserRole.DOCTOR, UserRole.PHARMACIST};

        for (UserRole role : staffRoles) {
            if (user.getRole() == role) {
                return true;
            }
        }

        return false;
    }
}
