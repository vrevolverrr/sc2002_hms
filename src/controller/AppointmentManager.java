package controller;

import model.users.Doctor;
import model.Appointment;
import java.time.LocalDate;
import java.time.LocalTime; 
import java.time.format.DateTimeParseException;
import java.util.*;

public class AppointmentManager extends Manager<AppointmentManager> {
    private static AppointmentManager instance;

    private List<Appointment> appointments = new ArrayList<>();

    private AppointmentManager() {}

    public static AppointmentManager getInstance() {
        if (instance == null) {
            instance = new AppointmentManager();
        }
        return instance;
    }

    // Method to schedule a new appointment
    public void schedule() {
        System.out.println("Scheduling a new appointment...");

        Doctor selectedDoctor = selectDoctor();
        if (selectedDoctor == null) {
            System.out.println("No doctor selected. Returning to menu.");
            return;
        }

        LocalDate selectedDate = selectDate();
        if (selectedDate == null) {
            System.out.println("No date selected. Returning to menu.");
            return;
        }

        LocalTime selectedTime = selectTimeSlot();
        if (selectedTime == null) {
            System.out.println("No time slot selected. Returning to menu.");
            return;
        }

        Appointment newAppointment = new Appointment(selectedDoctor, selectedDate, selectedTime);
        appointments.add(newAppointment); // Store the new appointment

        System.out.println("Appointment successfully scheduled with Dr. " + selectedDoctor.getName() +
                           " on " + selectedDate + " at " + selectedTime + ".");
    }

    // Method to reschedule an existing appointment
    public void reschedule() {
        System.out.println("Rescheduling an appointment...");

        Appointment selectedAppointment = selectAppointment();
        if (selectedAppointment == null) {
            System.out.println("No appointment selected. Returning to menu.");
            return;
        }

        LocalDate newDate = selectDate();
        if (newDate == null) {
            System.out.println("No date selected. Returning to menu.");
            return;
        }

        LocalTime newTime = selectTimeSlot();
        if (newTime == null) {
            System.out.println("No time slot selected. Returning to menu.");
            return;
        }

        selectedAppointment.setDate(newDate);
        selectedAppointment.setSlot(newTime);

        System.out.println("Appointment rescheduled to " + newDate + " at " + newTime + ".");
    }

    // Method to cancel an existing appointment
    public void cancel() {
        System.out.println("Canceling an appointment...");

        Appointment selectedAppointment = selectAppointment();
        if (selectedAppointment == null) {
            System.out.println("No appointment selected. Returning to menu.");
            return;
        }

        appointments.remove(selectedAppointment);
        System.out.println("Appointment successfully canceled.");
    }

    // Method to select an appointment
    private Appointment selectAppointment() {
        System.out.println("Please select an appointment to manage:");
        
        for (int i = 0; i < appointments.size(); i++) {
            Appointment appointment = appointments.get(i);
            System.out.println((i + 1) + ". Dr. " + appointment.getDoc().getName() + 
                               " on " + appointment.getDate() + " at " + appointment.getSlot());
        }

        int appointmentIndex = getUserInputAsInt() - 1;
        if (appointmentIndex >= 0 && appointmentIndex < appointments.size()) {
            return appointments.get(appointmentIndex);
        }
        System.out.println("Invalid selection.");
        return null;
    }

    // Method to select a doctor
    private Doctor selectDoctor() {
        System.out.println("Please select a doctor:");
        
        // This should ideally fetch active doctors; using a placeholder list for demonstration
        List<Doctor> doctors = UserManager.getAllDoctors(); // come back later
        
        for (Doctor doc : doctors) {
            System.out.println(doc.getDocId() + ". " + doc.getName()); //come back later
        }

        int doctorId = getUserInputAsInt();
        return doctors.stream().filter(doc -> doc.getId() == doctorId).findFirst().orElse(null);
    }

    // Method to select a date
    private LocalDate selectDate() {
        System.out.println("Please enter the appointment date (YYYY-MM-DD):");
        String dateInput = getUserInputAsString();
        try {
            return LocalDate.parse(dateInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Please use YYYY-MM-DD.");
            return null;
        }
    }

    // Method to select a time slot
    private LocalTime selectTimeSlot() {
        System.out.println("Please enter the appointment time (HH:MM):");
        String timeInput = getUserInputAsString();
        try {
            return LocalTime.parse(timeInput);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid time format. Please use HH:MM.");
            return null;
        }
    }

    // Method to get integer input from user
    private int getUserInputAsInt() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid integer.");
            scanner.next(); // Clear invalid input
            return -1;
        }
    }

    // Method to get string input from user
    private String getUserInputAsString() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
