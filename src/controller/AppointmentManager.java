package controller;

import model.appointments.Appointment;
import model.enums.AppointmentStatus;
import model.enums.Gender;
import model.enums.Specialisation;
import model.users.Doctor;
import model.users.Patient;
import repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager extends Manager<AppointmentManager> {
    private AppointmentRepository appointmentRepository = new AppointmentRepository();

    protected AppointmentManager() {}

    public void scheduleAppointment(LocalDateTime scheduledTime, Doctor doctor, Patient patient) {
        // Appointment newAppointment = new Appointment("A1001", AppointmentStatus.SCHEDULED, scheduledTime, doctor.getDoctorId(), patient.getPatientId());
    }

    public List<AppointmentSlot> getAvailableSlots() {
        List<AppointmentSlot> availableSlots = new ArrayList<AppointmentSlot>();
        Doctor dummyDoctor = new Doctor("D1001", "John Doe", 45, "password", Gender.MALE, LocalDate.of(1970, 5, 9), "81908164", "test@gmail.com", Specialisation.CARDIOLOGIST);
        availableSlots.add(new AppointmentSlot(dummyDoctor, LocalDateTime.now()));
        availableSlots.add(new AppointmentSlot(dummyDoctor, LocalDateTime.now()));

        return availableSlots;
    }

    public List<Appointment> getAppointmentsByPatientId(String patientId) {
        // return appointmentRepository.getAppointmentsByPatientId(patientId);
        List<Appointment> appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment("A1001", AppointmentStatus.SCHEDULED, LocalDateTime.now(), "D1001","P1001"));
        appointments.add(new Appointment("A1002", AppointmentStatus.SCHEDULED, LocalDateTime.now(), "D1001","P1001"));

        return appointments;
    }


    // // Method to reschedule an existing appointment
    // public void reschedule() {
    //     System.out.println("Rescheduling an appointment...");

    //     Appointment selectedAppointment = selectAppointment();
    //     if (selectedAppointment == null) {
    //         System.out.println("No appointment selected. Returning to menu.");
    //         return;
    //     }

    //     LocalDate newDate = selectDate();
    //     if (newDate == null) {
    //         System.out.println("No date selected. Returning to menu.");
    //         return;
    //     }

    //     LocalTime newTime = selectTimeSlot();
    //     if (newTime == null) {
    //         System.out.println("No time slot selected. Returning to menu.");
    //         return;
    //     }

    //     selectedAppointment.setDate(newDate);
    //     selectedAppointment.setSlot(newTime);

    //     System.out.println("Appointment rescheduled to " + newDate + " at " + newTime + ".");
    // }

    // // Method to select a time slot
    // private LocalTime selectTimeSlot() {
    //     System.out.println("Please enter the appointment time (HH:MM):");
    //     String timeInput = getUserInputAsString();
    //     try {
    //         return LocalTime.parse(timeInput);
    //     } catch (DateTimeParseException e) {
    //         System.out.println("Invalid time format. Please use HH:MM.");
    //         return null;
    //     }
    // }

    // // Method to get integer input from user
    // private int getUserInputAsInt() {
    //     Scanner scanner = new Scanner(System.in);
    //     try {
    //         return scanner.nextInt();
    //     } catch (InputMismatchException e) {
    //         System.out.println("Invalid input. Please enter a valid integer.");
    //         scanner.next(); // Clear invalid input
    //         return -1;
    //     }
    // }

    // // Method to get string input from user
    // private String getUserInputAsString() {
    //     Scanner scanner = new Scanner(System.in);
    //     return scanner.nextLine();
    // }
}
