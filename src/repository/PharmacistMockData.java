package repository;

import java.time.LocalDate;
import java.util.*;

import model.Pharmacist;
import model.enums.Gender;

public class PharmacistMockData {
    public static void mockPharmacistData() {
        UserRepository userRepository = new UserRepository();
        PharmacistRepository pharmacistRepository = new PharmacistRepository(userRepository);
        
        List<Pharmacist> mockData = new ArrayList<>();
        mockData.add(new Pharmacist("P2001", "Pharmacist1", "password123", Gender.FEMALE, LocalDate.of(1985, 3, 22), "91234567", "sara.lee@example.com"));
        mockData.add(new Pharmacist("P2002", "Pharmacist2", "securePass", Gender.MALE, LocalDate.of(1990, 7, 15), "98765432", "michael.jones@example.com"));
        mockData.add(new Pharmacist("P2003", "Pharmacist3", "pharma321", Gender.FEMALE, LocalDate.of(1995, 12, 2), "92345678", "emma.wilson@example.com"));
        
        pharmacistRepository.save(mockData);
    }
}
