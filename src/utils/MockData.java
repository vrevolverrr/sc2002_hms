package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

import model.appointments.Appointment;
import model.appointments.AppointmentOutcomeRecord;
import model.appointments.TimeSlot;
import model.availability.Availability;
import model.availability.TimePeriod;
import model.enums.AppointmentStatus;
import model.enums.BloodType;
import model.enums.DosageUnit;
import model.enums.Gender;
import model.enums.MedicalService;
import model.enums.MedicineFrequency;
import model.enums.Specialisation;
import model.inventory.InventoryItem;
import model.medrecord.MedicalRecordEntry;
import model.prescriptions.MedicineDosage;
import model.prescriptions.Prescription;
import model.users.Admin;
import model.users.Doctor;
import model.users.Patient;
import model.users.Pharmacist;
import repository.AdminRepository;
import repository.AppointmentRepository;
import repository.DoctorRepository;
import repository.InventoryRepository;
import repository.MedicalRecordRepository;
import repository.PatientRepository;
import repository.PharmacistRepository;
import repository.UserRepository;

/**
 * The utility class to mock data for the application.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-16
 */
public final class MockData {
    /**
     * Mocks all the data for the application.
     */
    public static void mockAllData() {
        mockPatientData();
        mockAdminData();
        mockInventoryData();
        mockPharmacistData();
        mockDoctorData();
        mockAppointmentData();
        mockMedicalRecordData();
    }

    /**
     * Mocks patient data for the application.
     */
    public static void mockPatientData() {
        final UserRepository userRepository = new UserRepository();

        PatientRepository patientRepository = new PatientRepository(userRepository);
        patientRepository.clear();
        
        patientRepository.save(new Patient("P1001", "Alice Brown", 44, "123", Gender.FEMALE, LocalDate.of(1980, 5, 14), 45.7, 162.3, "81888888", "alice.brown@example.com", BloodType.A_POSITIVE));
        patientRepository.save(new Patient("P1002", "John Smith", 32, "abc123", Gender.MALE, LocalDate.of(1992, 8, 10), 75.5, 178.2, "81812345", "john.smith@example.com", BloodType.O_NEGATIVE));
        patientRepository.save(new Patient("P1003", "Emily Davis", 39, "secure!1", Gender.FEMALE, LocalDate.of(1985, 3, 22), 58.3, 165.7, "82765432", "emily.davis@example.com", BloodType.B_POSITIVE));
        patientRepository.save(new Patient("P1004", "Michael Johnson", 46, "michaelPass", Gender.MALE, LocalDate.of(1978, 11, 5), 82.1, 180.5, "83654321", "michael.j@example.com", BloodType.A_NEGATIVE));
        patientRepository.save(new Patient("P1005", "Sarah Lee", 24, "Lee$2020", Gender.FEMALE, LocalDate.of(2000, 6, 15), 52.8, 160.4, "81987654", "sarah.lee@example.com", BloodType.AB_POSITIVE));
        patientRepository.save(new Patient("P1006", "James Brown", 29, "jBrown123", Gender.MALE, LocalDate.of(1995, 9, 18), 78.4, 175.8, "81098765", "james.brown@example.com", BloodType.B_NEGATIVE));
        patientRepository.save(new Patient("P1007", "Laura Wilson", 35, "laura_W!lson", Gender.FEMALE, LocalDate.of(1989, 1, 27), 61.2, 168.9, "81345678", "laura.wilson@example.com", BloodType.O_POSITIVE));
        patientRepository.save(new Patient("P1008", "Robert Miller", 52, "millerPass!", Gender.MALE, LocalDate.of(1972, 12, 2), 85.6, 182.3, "81823456", "robert.miller@example.com", BloodType.A_POSITIVE));
        patientRepository.save(new Patient("P1009", "Jessica Taylor", 34, "Jessie321", Gender.FEMALE, LocalDate.of(1990, 7, 30), 55.9, 163.5, "81234567", "jessica.taylor@example.com", BloodType.AB_NEGATIVE));
        patientRepository.save(new Patient("P1010", "Charles Moore", 41, "charlieM0re", Gender.MALE, LocalDate.of(1983, 4, 6), 79.8, 177.4, "81456789", "charles.moore@example.com", BloodType.B_POSITIVE));
        patientRepository.save(new Patient("P1011", "Megan Clark", 49, "meganC!ark", Gender.FEMALE, LocalDate.of(1975, 10, 12), 63.4, 166.8, "81876543", "megan.clark@example.com", BloodType.O_NEGATIVE));
    }

    /**
     * Mocks admin data for the application.
     */
    public static void mockAdminData() {
        final UserRepository userRepository = new UserRepository();

        AdminRepository adminRepository = new AdminRepository(userRepository);
        adminRepository.clear();
        
        Admin a1 = new Admin("A1001", "David Johnson", 45, "adminPass1", Gender.MALE, LocalDate.of(1978, 1, 15), "81234567", "david.johnson@example.com");
        a1.setDefaultPassword(false);
        adminRepository.save(a1);
        adminRepository.save(new Admin("A1002", "Sophia Martinez", 38, "adminPass2", Gender.FEMALE, LocalDate.of(1985, 3, 22), "82345678", "sophia.martinez@example.com"));
        adminRepository.save(new Admin("A1003", "James Anderson", 50, "adminPass3", Gender.MALE, LocalDate.of(1973, 5, 30), "83456789", "james.anderson@example.com"));
    }

    /**
     * Mocks pharmacist data for the application.
     */
    public static void mockPharmacistData() {
        final UserRepository userRepository = new UserRepository();

        PharmacistRepository pharmacistRepository = new PharmacistRepository(userRepository);
        pharmacistRepository.clear();

        pharmacistRepository.save(new Pharmacist("F1001", "Bryan Lee", 35, "bryan123", Gender.MALE, LocalDate.of(1989, 6, 10), "81234567", "bryan.lee@gmail.com"));
        pharmacistRepository.save(new Pharmacist("F1002", "Anna Kim", 28, "annaK!m", Gender.FEMALE, LocalDate.of(1995, 4, 22), "81234568", "anna.kim@example.com"));
        pharmacistRepository.save(new Pharmacist("F1003", "David Wong", 40, "davidWong456", Gender.MALE, LocalDate.of(1983, 11, 5), "81234569", "david.wong@example.com"));
        pharmacistRepository.save(new Pharmacist("F1004", "Sophia Tan", 32, "sophiaT@an", Gender.FEMALE, LocalDate.of(1991, 8, 15), "81234570", "sophia.tan@example.com"));
        pharmacistRepository.save(new Pharmacist("F1005", "Michael Chan", 45, "michaelChan789", Gender.MALE, LocalDate.of(1978, 2, 10), "81234571", "michael.chan@example.com"));
    }

    /**
     * Mocks inventory data for the application.
     */
    public static void mockInventoryData() {
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.clear();
        
        inventoryRepository.save(new InventoryItem("I1001", "Paracetamol", 200, 30));
        inventoryRepository.save(new InventoryItem("I1002", "Ibuprofen", 200, 30));
        inventoryRepository.save(new InventoryItem("I1003", "Amoxicillin", 150, 20));
        inventoryRepository.save(new InventoryItem("I1004", "Ciprofloxacin", 30, 40));
        inventoryRepository.save(new InventoryItem("I1005", "Metformin", 300, 25));
        inventoryRepository.save(new InventoryItem("I1006", "Amlodipine", 80, 35));
        inventoryRepository.save(new InventoryItem("I1007", "Omeprazole", 120, 45));
        inventoryRepository.save(new InventoryItem("I1008", "Simvastatin", 220, 60));
        inventoryRepository.save(new InventoryItem("I1009", "Lisinopril", 100, 55));
        inventoryRepository.save(new InventoryItem("I1010", "Levothyroxine", 20, 50));
    }

    /**
     * Mocks doctor data for the application.
     */
    public static void mockDoctorData() {
        final UserRepository userRepository = new UserRepository();

        DoctorRepository doctorRepository = new DoctorRepository(userRepository);
        doctorRepository.clear();

        Doctor d1 = new Doctor("D1001", "John Doe", 45, "johnDoe123", Gender.MALE, LocalDate.of(1978, 1, 15), "81234567", "johndoe@email.com", Specialisation.CARDIOLOGIST);
        Availability d1A = new Availability();
        d1A.setAvailability(LocalDate.now().plusDays(5), new TimePeriod(LocalTime.of(14, 0), LocalTime.of(18, 30)));
        d1A.setAvailability(LocalDate.now().plusDays(4), new TimePeriod(LocalTime.of(8, 0), LocalTime.of(12, 0)));
        d1.setAvailability(d1A);
        doctorRepository.save(d1);
        
        doctorRepository.save(new Doctor("D1002", "Jane Smith", 38, "janeSmith456", Gender.FEMALE, LocalDate.of(1985, 2, 20), "81234568", "janesmith@email.com", Specialisation.DERMATOLOGIST));
        doctorRepository.save(new Doctor("D1003", "Robert Brown", 50, "robertBrown789", Gender.MALE, LocalDate.of(1973, 3, 25), "81234569", "robertbrown@email.com", Specialisation.NEUROLOGIST));
        doctorRepository.save(new Doctor("D1004", "Emily White", 42, "emilyWhite101", Gender.FEMALE, LocalDate.of(1981, 4, 30), "81234570", "emilywhite@email.com", Specialisation.GYNECOLOGIST));
        doctorRepository.save(new Doctor("D1005", "Michael Green", 47, "michaelGreen202", Gender.MALE, LocalDate.of(1976, 5, 15), "81234571", "michaelgreen@email.com", Specialisation.PEDIATRIST));
        doctorRepository.save(new Doctor("D1006", "Laura Black", 35, "lauraBlack303", Gender.FEMALE, LocalDate.of(1988, 6, 10), "81234572", "laurablack@email.com", Specialisation.GYNECOLOGIST));
    }

    /**
     * Mocks appointment data for the application.
     */
    public static void mockAppointmentData() {
        AppointmentRepository appointmentRepository = new AppointmentRepository();
        appointmentRepository.clear();

        LocalDate today = LocalDate.now();

        // Same doctor same patient
        appointmentRepository.save(new Appointment("Y1001", AppointmentStatus.SCHEDULED, new TimeSlot(today.atTime(8, 30)), "D1001", "P1001"));
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
        appointmentRepository.save(new Appointment("Y1010", AppointmentStatus.REQUESTED, new TimeSlot(today.plusDays(2).atTime(16, 30)), "D1002", "P1007"));
        appointmentRepository.save(new Appointment("Y1011", AppointmentStatus.REQUESTED, new TimeSlot(today.plusDays(2).atTime(10, 0)), "D1002", "P1008"));

        // Fulfilled appointment
        appointmentRepository.save(new Appointment("Y1012", AppointmentStatus.FULFILLED, new TimeSlot(today.minusDays(1).atTime(13, 0)), "D1001", "P1001"));
        
        appointmentRepository.save(new Appointment("Y1014", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(3).atTime(9, 30)), "D1001", "P1001"));
        appointmentRepository.save(new Appointment("Y1015", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(5).atTime(16, 30)), "D1001", "P1001"));
        appointmentRepository.save(new Appointment("Y1016", AppointmentStatus.SCHEDULED, new TimeSlot(today.plusDays(6).atTime(13, 0)), "D1001", "P1001"));

        // Completed appointment
        Appointment pA1 = new Appointment("Y1013", AppointmentStatus.COMPLETED, new TimeSlot(today.minusDays(2).atTime(11, 30)), "D1001", "P1001");
        Prescription[] pA1Ps = {
            new Prescription("I1001", 14, new MedicineDosage(2, DosageUnit.TABLET), MedicineFrequency.AFTER_MEALS),
            new Prescription("I1002", 8, new MedicineDosage(1, DosageUnit.TABLET), MedicineFrequency.AS_NEEDED)
        };
        MedicalService[] pA1MSs = {MedicalService.CONSULTATION, MedicalService.BLOOD_TEST};
        AppointmentOutcomeRecord pA1O = new AppointmentOutcomeRecord(
            today, 
            Arrays.asList(pA1Ps), 
            Arrays.asList(pA1MSs), 
            "Patient has mild fever. Advised to rest and drink plenty of fluids.");
        pA1.setOutcomeRecord(pA1O);
        appointmentRepository.save(pA1);

        System.out.println(appointmentRepository.findById("Y1013").getOutcomeRecord().getConsultationNotes());
    }

    /**
     * Mocks medical record data for the application.
     */
    public static void mockMedicalRecordData() {
        MedicalRecordRepository medicalRecordRepository = new MedicalRecordRepository();
        medicalRecordRepository.clear();

        LocalDate today = LocalDate.now();

        Prescription[] pA1Ps = {
            new Prescription("I1001", 14, new MedicineDosage(2, DosageUnit.TABLET), MedicineFrequency.AFTER_MEALS),
            new Prescription("I1002", 8, new MedicineDosage(1, DosageUnit.TABLET), MedicineFrequency.AS_NEEDED)
        };
        MedicalService[] pA1MSs = {MedicalService.CONSULTATION, MedicalService.BLOOD_TEST};
        medicalRecordRepository.save(new MedicalRecordEntry("M1001", today, "P1001", "D1001",
            "Patient has mild fever. Advised to rest and drink plenty of fluids.", "Prescribed Paracetamol for the fever and Ibuprofen as needed to cope with headache.", 
            Arrays.asList(pA1Ps), Arrays.asList(pA1MSs)));
    }
}
