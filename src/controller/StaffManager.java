package controller;

import java.util.List;

import model.enums.Gender;
import model.enums.UserRole;
import model.users.User;
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
        );
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
