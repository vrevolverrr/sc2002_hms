package utils;

import java.time.LocalDate;
import java.util.*;

import model.InventoryItem;
import model.enums.BloodType;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.ReplenishmentStatus;
import model.users.Admin;
import model.users.Patient;
import model.users.Doctor;
import repository.AdminRepository;
import repository.InventoryRepository;
import repository.PatientRepository;
import repository.UserRepository;
import repository.DoctorRepository;

public class MockData {
    public static void mockAllData() {
        mockPatientData();
        mockAdminData();
        mockDoctorData();
    }

    public static void mockPatientData() {
        UserRepository userRepository = new UserRepository();
        PatientRepository patientRepository = new PatientRepository(userRepository);
        
        List<Patient> mockData = new ArrayList<Patient>();
        mockData.add(new Patient("P1001", "Alice Brown", 44, "123", Gender.FEMALE, LocalDate.of(1980, 5, 14), "81888888", "alice.brown@example.com", BloodType.A_POSITIVE));
        mockData.add(new Patient("P1002", "John Smith", 32, "abc123", Gender.MALE, LocalDate.of(1992, 8, 10), "81812345", "john.smith@example.com", BloodType.O_NEGATIVE));
        mockData.add(new Patient("P1003", "Emily Davis", 39, "secure!1", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82765432", "emily.davis@example.com", BloodType.B_POSITIVE));
        mockData.add(new Patient("P1004", "Michael Johnson", 46, "michaelPass", Gender.MALE, LocalDate.of(1978, 11, 5), "83654321", "michael.j@example.com", BloodType.A_NEGATIVE));
        mockData.add(new Patient("P1005", "Sarah Lee", 24, "Lee$2020", Gender.FEMALE, LocalDate.of(2000, 6, 15), "81987654", "sarah.lee@example.com", BloodType.AB_POSITIVE));
        mockData.add(new Patient("P1006", "James Brown", 29, "jBrown123", Gender.MALE, LocalDate.of(1995, 9, 18), "81098765", "james.brown@example.com", BloodType.B_NEGATIVE));
        mockData.add(new Patient("P1007", "Laura Wilson", 35, "laura_W!lson", Gender.FEMALE, LocalDate.of(1989, 1, 27), "81345678", "laura.wilson@example.com", BloodType.O_POSITIVE));
        mockData.add(new Patient("P1008", "Robert Miller", 52, "millerPass!", Gender.MALE, LocalDate.of(1972, 12, 2), "81823456", "robert.miller@example.com", BloodType.A_POSITIVE));
        mockData.add(new Patient("P1009", "Jessica Taylor", 34, "Jessie321", Gender.FEMALE, LocalDate.of(1990, 7, 30), "81234567", "jessica.taylor@example.com", BloodType.AB_NEGATIVE));
        mockData.add(new Patient("P1010", "Charles Moore", 41, "charlieM0re", Gender.MALE, LocalDate.of(1983, 4, 6), "81456789", "charles.moore@example.com", BloodType.B_POSITIVE));
        mockData.add(new Patient("P1011", "Megan Clark", 49, "meganC!ark", Gender.FEMALE, LocalDate.of(1975, 10, 12), "81876543", "megan.clark@example.com", BloodType.O_NEGATIVE));

        patientRepository.save(mockData);
    }

    public static void mockAdminData() {
        UserRepository userRepository = new UserRepository();
        AdminRepository adminRepository = new AdminRepository(userRepository);
        
        List<Admin> mockData = new ArrayList<Admin>();
        mockData.add(new Admin("A1001", "David Johnson", 45, "adminPass1", Gender.MALE, LocalDate.of(1978, 1, 15), "81234567", "david.johnson@example.com"));
        mockData.add(new Admin("A1002", "Sophia Martinez", 38, "adminPass2", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82345678", "sophia.martinez@example.com"));
        mockData.add(new Admin("A1003", "James Anderson", 50, "adminPass3", Gender.MALE, LocalDate.of(1973, 5, 30), "83456789", "james.anderson@example.com"));

        adminRepository.save(mockData);
    }

    public static void mockDoctorData() {
        UserRepository userRepository = new UserRepository();
        DoctorRepository doctorRepository = new DoctorRepository(userRepository);
        
        List<Doctor> mockData = new ArrayList<Doctor>();
        mockData.add(new Doctor("D1001", "Dr Alexander Smith", 46, "doctorPwd1", Gender.MALE, LocalDate.of(1978, 5, 24), "81892818", "alexander.smith@example.com", Specialisation.CARDIOLOGIST));
        mockData.add(new Doctor("D1002", "Dr Janice Teo", 51, "doctorPwd2", Gender.FEMALE, LocalDate.of(1973, 8, 12), "81923415", "janice.teo@example.com", Specialisation.SURGEON));
        mockData.add(new Doctor("D1003", "Dr Emily Chan", 31, "doctorPwd3", Gender.FEMALE, LocalDate.of(1993, 3, 29), "82298312", "emily.chan@example.com", Specialisation.PEDIATRIST));
        mockData.add(new Doctor("D1004", "Dr Will Santiago", 39, "doctorPwd4", Gender.MALE, LocalDate.of(1985, 11, 10), "81093321", "will.santiago@example.com", Specialisation.RADIOLOGIST));

        doctorRepository.save(mockData);
    public static void mockInventoryData() {
        InventoryRepository inventoryRepository = new InventoryRepository();
        
        inventoryRepository.save(new InventoryItem("I1002", "Ibuprofen", 200, 30, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1003", "Amoxicillin", 150, 20, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1004", "Ciprofloxacin", 120, 40, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1005", "Metformin", 300, 25, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1006", "Amlodipine", 250, 35, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1007", "Omeprazole", 180, 45, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1008", "Simvastatin", 220, 60, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1009", "Lisinopril", 160, 55, ReplenishmentStatus.NULL));
        inventoryRepository.save(new InventoryItem("I1010", "Levothyroxine", 140, 50, ReplenishmentStatus.NULL));
    }
}
