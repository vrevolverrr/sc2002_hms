package utils;

import java.time.LocalDate;

import model.InventoryItem;
import model.appointments.Appointment;
import model.appointments.TimeSlot;
import model.enums.AppointmentStatus;
import model.enums.BloodType;
import model.enums.Gender;
import model.enums.ReplenishmentStatus;
import model.enums.Specialisation;
import model.users.Admin;
import model.users.Doctor;
import model.users.Patient;
import repository.AdminRepository;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.InventoryRepository;
import repository.PatientRepository;
import repository.UserRepository;

public class MockData {
    public static void mockAllData() {
        mockPatientData();
        mockAdminData();
        mockInventoryData();
        mockDoctorData();
        mockAppointmentData();
    }

    public static void mockPatientData() {
        PatientRepository patientRepository = new PatientRepository(UserRepository.getInstance());
        
        patientRepository.save(new Patient("P1001", "Alice Brown", 44, "123", Gender.FEMALE, LocalDate.of(1980, 5, 14), "81888888", "alice.brown@example.com", BloodType.A_POSITIVE));
        patientRepository.save(new Patient("P1002", "John Smith", 32, "abc123", Gender.MALE, LocalDate.of(1992, 8, 10), "81812345", "john.smith@example.com", BloodType.O_NEGATIVE));
        patientRepository.save(new Patient("P1003", "Emily Davis", 39, "secure!1", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82765432", "emily.davis@example.com", BloodType.B_POSITIVE));
        patientRepository.save(new Patient("P1004", "Michael Johnson", 46, "michaelPass", Gender.MALE, LocalDate.of(1978, 11, 5), "83654321", "michael.j@example.com", BloodType.A_NEGATIVE));
        patientRepository.save(new Patient("P1005", "Sarah Lee", 24, "Lee$2020", Gender.FEMALE, LocalDate.of(2000, 6, 15), "81987654", "sarah.lee@example.com", BloodType.AB_POSITIVE));
        patientRepository.save(new Patient("P1006", "James Brown", 29, "jBrown123", Gender.MALE, LocalDate.of(1995, 9, 18), "81098765", "james.brown@example.com", BloodType.B_NEGATIVE));
        patientRepository.save(new Patient("P1007", "Laura Wilson", 35, "laura_W!lson", Gender.FEMALE, LocalDate.of(1989, 1, 27), "81345678", "laura.wilson@example.com", BloodType.O_POSITIVE));
        patientRepository.save(new Patient("P1008", "Robert Miller", 52, "millerPass!", Gender.MALE, LocalDate.of(1972, 12, 2), "81823456", "robert.miller@example.com", BloodType.A_POSITIVE));
        patientRepository.save(new Patient("P1009", "Jessica Taylor", 34, "Jessie321", Gender.FEMALE, LocalDate.of(1990, 7, 30), "81234567", "jessica.taylor@example.com", BloodType.AB_NEGATIVE));
        patientRepository.save(new Patient("P1010", "Charles Moore", 41, "charlieM0re", Gender.MALE, LocalDate.of(1983, 4, 6), "81456789", "charles.moore@example.com", BloodType.B_POSITIVE));
        patientRepository.save(new Patient("P1011", "Megan Clark", 49, "meganC!ark", Gender.FEMALE, LocalDate.of(1975, 10, 12), "81876543", "megan.clark@example.com", BloodType.O_NEGATIVE));
    }

    public static void mockAdminData() {
        AdminRepository adminRepository = new AdminRepository(UserRepository.getInstance());
        
        adminRepository.save(new Admin("A1001", "David Johnson", 45, "adminPass1", Gender.MALE, LocalDate.of(1978, 1, 15), "81234567", "david.johnson@example.com"));
        adminRepository.save(new Admin("A1002", "Sophia Martinez", 38, "adminPass2", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82345678", "sophia.martinez@example.com"));
        adminRepository.save(new Admin("A1003", "James Anderson", 50, "adminPass3", Gender.MALE, LocalDate.of(1973, 5, 30), "83456789", "james.anderson@example.com"));
    }

    public static void mockInventoryData() {
        InventoryRepository inventoryRepository = new InventoryRepository();
        
        inventoryRepository.save(new InventoryItem("I1001", "Paracetamol", 200, 30, ReplenishmentStatus.NULL));
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

    public static void mockDoctorData() {
        DoctorRepository doctorRepository = new DoctorRepository(UserRepository.getInstance());

        doctorRepository.save(new Doctor("D1001", "John Doe", 45, "johnDoe123", Gender.MALE, LocalDate.of(1978, 1, 15), "81234567", "johndoe@email.com", Specialisation.CARDIOLOGIST));
        doctorRepository.save(new Doctor("D1002", "Jane Smith", 38, "janeSmith456", Gender.FEMALE, LocalDate.of(1985, 2, 20), "81234568", "janesmith@email.com", Specialisation.DERMATOLOGIST));
        doctorRepository.save(new Doctor("D1003", "Robert Brown", 50, "robertBrown789", Gender.MALE, LocalDate.of(1973, 3, 25), "81234569", "robertbrown@email.com", Specialisation.NEUROLOGIST));
        doctorRepository.save(new Doctor("D1004", "Emily White", 42, "emilyWhite101", Gender.FEMALE, LocalDate.of(1981, 4, 30), "81234570", "emilywhite@email.com", Specialisation.GYNECOLOGIST));
        doctorRepository.save(new Doctor("D1005", "Michael Green", 47, "michaelGreen202", Gender.MALE, LocalDate.of(1976, 5, 15), "81234571", "michaelgreen@email.com", Specialisation.PEDIATRIST));
        doctorRepository.save(new Doctor("D1006", "Laura Black", 35, "lauraBlack303", Gender.FEMALE, LocalDate.of(1988, 6, 10), "81234572", "laurablack@email.com", Specialisation.GYNECOLOGIST));
    }

    public static void mockAppointmentData() {
        AppointmentRepository appointmentRepository = new AppointmentRepository();

        LocalDate today = LocalDate.now();

        // Same doctor same patient
        appointmentRepository.save(new Appointment("Y1001", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(10, 0)), "D1001", "P1001"));
        appointmentRepository.save(new Appointment("Y1002", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(13, 30)), "D1001", "P1001"));

        // Same doctor different patient
        appointmentRepository.save(new Appointment("Y1003", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(17, 0)), "D1002", "P1002"));
        appointmentRepository.save(new Appointment("Y1004", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(17, 0)), "D1002", "P1003"));
        appointmentRepository.save(new Appointment("Y1005", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(17, 0)), "D1002", "P1004"));
        
        // Different doctor same patient
        appointmentRepository.save(new Appointment("Y1006", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(1).atTime(11, 30)), "D1003", "P1005"));
        appointmentRepository.save(new Appointment("Y1007", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(1).atTime(12, 0)), "D1004", "P1005"));
        appointmentRepository.save(new Appointment("Y1008", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(1).atTime(13, 30)), "D1005", "P1005"));

        // Unapproved appointments
        appointmentRepository.save(new Appointment("Y1009", AppointmentStatus.REQUESTED, new TimeSlot(today.plusDays(2).atTime(15, 30)), "D1002", "P1006"));
        appointmentRepository.save(new Appointment("Y1011", AppointmentStatus.REQUESTED, new TimeSlot(today.plusDays(2).atTime(16, 30)), "D1002", "P1007"));
        appointmentRepository.save(new Appointment("Y1011", AppointmentStatus.REQUESTED, new TimeSlot(today.plusDays(2).atTime(10, 0)), "D1002", "P1008"));

    }
}
