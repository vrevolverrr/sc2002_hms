package repository;

import java.time.LocalDate;
import java.util.*;

import model.Patient;
import model.enums.BloodType;
import model.enums.Gender;

public class MockData {
    public static void mockPatientData() {
        UserRepository userRepository = new UserRepository();
        PatientRepository patientRepository = new PatientRepository(userRepository);
        
        List<Patient> mockData = new ArrayList<Patient>();
        mockData.add(new Patient("P1001", "Alice Brown", "123", Gender.FEMALE, LocalDate.of(1980, 5, 14), "81888888", "alice.brown@example.com", "44", BloodType.A_POSITIVE));
        mockData.add(new Patient("P1002", "John Smith", "abc123", Gender.MALE, LocalDate.of(1992, 8, 10), "81812345", "john.smith@example.com", "32", BloodType.O_NEGATIVE));
        mockData.add(new Patient("P1003", "Emily Davis", "secure!1", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82765432", "emily.davis@example.com", "39", BloodType.B_POSITIVE));
        mockData.add(new Patient("P1004", "Michael Johnson", "michaelPass", Gender.MALE, LocalDate.of(1978, 11, 5), "83654321", "michael.j@example.com", "46", BloodType.A_NEGATIVE));
        mockData.add(new Patient("P1005", "Sarah Lee", "Lee$2020", Gender.FEMALE, LocalDate.of(2000, 6, 15), "81987654", "sarah.lee@example.com", "24", BloodType.AB_POSITIVE));
        mockData.add(new Patient("P1006", "James Brown", "jBrown123", Gender.MALE, LocalDate.of(1995, 9, 18), "81098765", "james.brown@example.com", "29", BloodType.B_NEGATIVE));
        mockData.add(new Patient("P1007", "Laura Wilson", "laura_W!lson", Gender.FEMALE, LocalDate.of(1989, 1, 27), "81345678", "laura.wilson@example.com", "35", BloodType.O_POSITIVE));
        mockData.add(new Patient("P1008", "Robert Miller", "millerPass!", Gender.MALE, LocalDate.of(1972, 12, 2), "81823456", "robert.miller@example.com", "52", BloodType.A_POSITIVE));
        mockData.add(new Patient("P1009", "Jessica Taylor", "Jessie321", Gender.FEMALE, LocalDate.of(1990, 7, 30), "81234567", "jessica.taylor@example.com", "34", BloodType.AB_NEGATIVE));
        mockData.add(new Patient("P1010", "Charles Moore", "charlieM0re", Gender.MALE, LocalDate.of(1983, 4, 6), "81456789", "charles.moore@example.com", "41", BloodType.B_POSITIVE));
        mockData.add(new Patient("P1011", "Megan Clark", "meganC!ark", Gender.FEMALE, LocalDate.of(1975, 10, 12), "81876543", "megan.clark@example.com", "49", BloodType.O_NEGATIVE));

        patientRepository.save(mockData);
    }
}
