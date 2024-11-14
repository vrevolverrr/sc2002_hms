package controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import model.users.Doctor;


public class DoctorManager extends Manager<DoctorManager> {

      // Define time slots
      String[] timeSlots = {
        "9:00am", "9:30am", "10:00am", "10:30am", "11:00am", "11:30am",
        "12:00pm", "12:30pm", "1:00pm", "1:30pm", "2:00pm", "2:30pm", 
        "3:00pm", "3:30pm", "4:00pm", "4:30pm", "5:00pm", "5:30pm"
        };
    
    // Add availability for a specific date
    public void addAvailability() {
        // Get day of availability
        Scanner scanner = new Scanner(System.in);
                
        String day = "";
        while (true) {
            System.out.println("Enter date of availability (in format dd/mm/yy): ");
            day = scanner.nextLine().trim();

            // 1. Input validate in dd/mm/yy format
            // 2. Input validate that dd/mm/yy is in the future
            /* Validate date
            if () { 
                break;
            } else {
                System.out.println("Invalid date. Please enter in the format dd/mm/yy.");
            }  */

            System.out.println("Ennter time of availability ");
            time = scanner.nextLine().trim();

            // 1. Input validate that in HH:MM format
            // 2. Input validate that it is within the available timeslots given 

            // Create new row to input availability 
            System.out.println();
        }

        /* Get time of availability
        System.out.print("Enter timeslot of availability (in 24-hour format eg. 14:00): ");
        String time = scanner.nextLine();  */

        // Print the availability in the Doctor Repository

        /* availability.putIfAbsent(date, new ArrayList<>());
        if (!availability.get(date).contains(timeSlot)) {
            availability.get(date).add(timeSlot);
            System.out.println("Added availability on " + date + " at " + timeSlot);
        } else {
            System.out.println("Time slot already exists!");
        } */
       scanner.close();
    }

    // View availability
    public void viewAvailability() {
        System.out.println("Current Availability:");
        availability.forEach((date, timeSlots) -> {
            System.out.println("Date: " + date);
            timeSlots.forEach(time -> System.out.println("  Time: " + time));
        });
    }

    // Remove availability
    public void removeAvailability(LocalDate date, LocalTime timeSlot) {
        if (availability.containsKey(date) && availability.get(date).remove(timeSlot)) {
            System.out.println("Removed availability on " + date + " at " + timeSlot);
            if (availability.get(date).isEmpty()) {
                availability.remove(date);
            }
        } else {
            System.out.println("No such availability exists.");
        }
    }
}
