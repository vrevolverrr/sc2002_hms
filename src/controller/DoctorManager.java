package controller;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import model.users.Doctor;


public class DoctorManager extends Manager<DoctorManager> {
    
    // Add availability for a specific date
    public void addAvailability(LocalDate date, LocalTime timeSlot) {
        availability.putIfAbsent(date, new ArrayList<>());
        if (!availability.get(date).contains(timeSlot)) {
            availability.get(date).add(timeSlot);
            System.out.println("Added availability on " + date + " at " + timeSlot);
        } else {
            System.out.println("Time slot already exists!");
        }
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
