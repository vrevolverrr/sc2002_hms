package repository;

import java.time.LocalDate;
import java.util.*;

import model.Admin;
import model.enums.Gender;

public class AdminMockData {
    public static void mockAdmin0Data() {
        UserRepository userRepository = new UserRepository();
        AdminRepository adminRepository = new AdminRepository(userRepository);
        
        List<Admin> mockData = new ArrayList<Admin>();
        mockData.add(new Admin("A1001", "Admin1", "123", Gender.FEMALE, LocalDate.of(1980, 5, 14), "81888888", "alice.brown@example.com", "44"));
        mockData.add(new Admin("A1002", "Admin2", "abc123", Gender.MALE, LocalDate.of(1992, 8, 10), "81812345", "john.smith@example.com", "32"));
       
        adminRepository.save(mockData);
    }
}
