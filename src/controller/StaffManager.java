package controller;

import java.time.LocalDate;
import java.util.List;

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

public class StaffManager extends Manager<StaffManager> {
    private final UserRepository userRepository = UserRepository.getInstance();

    protected StaffManager() {}

    public List<User> getAllStaff() {
        return userRepository.findBy((user) -> isStaff(user));
    }

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

    public void addDoctor(String name, int age, String password, Gender gender, LocalDate dob,
            String emailAddress, String phoneNumber, Specialisation specialisation) {
        
        final Doctor user = new Doctor(null, name, age, password, gender, dob, phoneNumber, emailAddress, specialisation);
        final DoctorRepository repository = new DoctorRepository(UserRepository.getInstance());
        
        repository.save(user);
    }

    public void addAdmin(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber) {
       
        final Admin user = new Admin(null, name, age, password, gender, dob, phoneNumber, emailAddress);
        userRepository.save(user);
    }

    public void addPharmacist(String name, int age, String password, Gender gender, LocalDate dob, 
            String emailAddress, String phoneNumber) {

        final Pharmacist user = new Pharmacist(null, name, age, password, gender, dob, phoneNumber, emailAddress);   
        final PharmacistRepository repository = new PharmacistRepository(UserRepository.getInstance());
        
        repository.save(user);
    }

    public void updateStaff(User user) {
        userRepository.save(user);
    }

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
