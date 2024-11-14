package view.Doctor;

import controller.UserManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import services.Navigator;
import view.LoginView;
import view.View;


// for the functions


public class DoctorView extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private void viewPatientMedicalRecords(){
        
    }

    private void updatePatientMedicalRecords(){
        
    }

    private void viewPersonalSchedule(){
        // Print list of appointments
        
        // Then, print list of all availabilities
        
    }

    private void setAvailabilityforAppointments(){
        // First, ask for date of availability
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<LocalDateTime> Availability = new ArrayList<>();
        LocalDate date = null;
        LocalTime time = null;

        // First, ask for date of availability
        while (date == null) {
            System.out.print("Enter date of availability (in format yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            // error handling if date format is incorrect
            try {
                date = LocalDate.parse(dateInput, formatter);
                // TOCHECK: error handling if data is in the past
                if (!date.isAfter(LocalDate.now())) {
                    System.out.println("The date must be in the future. Please try again.");
                    date = null;  // Reset the date to trigger another input attempt
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid format, please try again.");
            }
        }

        // Second, ask for time of availability
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        System.out.print("Enter time of availability (in format HH:mm): ");

        while (time == null) {
            System.out.print("Enter a valid time (HH:mm): ");
            String timeInput = scanner.nextLine();

            try {
                // Parse the input into LocalTime
                time = LocalTime.parse(timeInput, timeFormatter);

                // Validation: ensure time is within operating hours (9AM-6PM)
                if (time.isBefore(LocalTime.of(9, 0)) || time.isAfter(LocalTime.of(18, 0))) {
                    System.out.println("Please enter a time between 09:00 and 18:00.");
                    time = null; // Reset time to prompt user again
                }

            } catch (DateTimeParseException e) {
                System.out.println("Invalid time format. Please use HH:mm.");
            }
        }
        
        // Display all collected date-times
        LocalDateTime dateTime = LocalDateTime.of(date, time);
        Availability.add(dateTime);

        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        System.out.println("\nList of Indicated Availabilities:");
        
        for (LocalDateTime dt : Availability) {
            System.out.println(dt.format(outputFormatter));
        }
    }

    private void acceptOrDeclineAppointmentRequests(){
        
    }

    private void viewUpcomingAppointments(){
       
    }

    private void RecordAppointmentOutcome(){
        
    }

    private void logOut(){
        Navigator.navigateTo(new LoginView());
    }

    @Override
    public String getViewName() {
        return("DoctorView");
    }

    @Override
    public void render() {

        BuildContext context = new BuildContext(100, 10);

        final int[] validChoice = {-1};
        
        while(validChoice[0] != 1){
            new Menu(
                new MenuOption("View Patient Medical Records", () -> {
                    this.viewPatientMedicalRecords();; validChoice[0] = 1;}),

                new MenuOption("Update Patient Medical Records", () -> {
                    this.updatePatientMedicalRecords();; validChoice[0] = 1;}),

                new MenuOption("View Personal Schedule", () -> {
                    this.viewPersonalSchedule();; validChoice[0] = 1;}),

                new MenuOption("Set Availability for Appointments", () -> {
                    this.setAvailabilityforAppointments();; validChoice[0] = 1;}),

                new MenuOption("Accept or Decline Appointment Requests", () -> {
                    this.acceptOrDeclineAppointmentRequests();; validChoice[0] = 1;}),

                new MenuOption("View Upcoming Appointments", () -> {
                    this.viewUpcomingAppointments();; validChoice[0] = 1;}),

                new MenuOption("Record Appointment Outcome", () -> {
                    this.RecordAppointmentOutcome();; validChoice[0] = 1;}),
                
                new MenuOption("Log Out", () -> {
                    this.logOut();; validChoice[0] = 1;})

            ).readOption(context);

            if(validChoice[0] != 1){
                new Text("Invalid choice. Please select a valid option.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(2);
                View.clearLines(1);
            }
        }
    }
    
}
