package model.users;

import java.time.LocalDate;
import model.availability.Availability;
import model.enums.Gender;
import model.enums.Specialisation;
import model.enums.UserRole;

/**
 * The concrete implementation of a {@link User} corresponding to a doctor.
 */
public class Doctor extends User {
    /**
     * The unique ID of the doctor.
     */
    private String doctorId;

    /**
     * The specialisation of the doctor.
     */
    private Specialisation specialisation;

    /**
     * The availability of the doctor.
     */
    Availability availability;

    /**
     * Constructor for a {@link Doctor}. Calls the constructor of {@link User}.
     * @param doctorId
     * @param name
     * @param age
     * @param password
     * @param gender
     * @param dob
     * @param phoneNumber
     * @param emailAddress
     * @param specialisation
     */
    public Doctor(String doctorId, String name, int age, String password, Gender gender, LocalDate dob, 
    String phoneNumber, String emailAddress, Specialisation specialisation) { 
        super(doctorId, UserRole.DOCTOR, password, name, age, gender, dob, phoneNumber, emailAddress);
                
        this.doctorId = doctorId;
        this.specialisation = specialisation;

        this.availability = new Availability();
    }

    /**
     * Gets the ID of the doctor.
     * @return the ID of the doctor.
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the ID of the doctor.
     * @param id the ID of the doctor.
     */
    public void setDoctorId(String id) {
        this.doctorId = id;
        super.setId(id);
    }

    /**
     * Get the specialisation of the doctor.
     * @return the specialisation of the doctor.
     */
    public Specialisation getSpecialisation() {
        return this.specialisation;
    }

    /**
     * Set the specialisation of the doctor. 
     * @param specialisation
     */
    public void setSpecialisation(Specialisation specialisation) {
        this.specialisation = specialisation;
    }

    public Availability getAvailability() {
        return availability.copy();
    }

    public void setAvailability(Availability availability) {
        this.availability = availability;
    }

    // // Set default availability from 9 AM to 6 PM on weekdays
    // public void setDefaultAvailability(LocalDate startDate, LocalDate endDate) {
    //     for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
    //         if (isWeekday(date)) {
    //             List<LocalTime> timeSlots = generateTimeSlots(LocalTime.of(9, 0), LocalTime.of(18, 0), 1);
    //             availability.put(date, timeSlots);
    //         }
    //     }
    //     System.out.println("Default availability set from " + startDate + " to " + endDate);
    // }

    // // Generate time slots with a specified interval (in hours)
    // private List<LocalTime> generateTimeSlots(LocalTime startTime, LocalTime endTime, int intervalHours) {
    //     List<LocalTime> timeSlots = new ArrayList<>();
    //     while (!startTime.isAfter(endTime)) {
    //         timeSlots.add(startTime);
    //         startTime = startTime.plusHours(intervalHours);
    //     }
    //     return timeSlots;
    // }

    // // Check if a date is a weekday (Monday to Friday)
    // private boolean isWeekday(LocalDate date) {
    //     DayOfWeek day = date.getDayOfWeek();
    //     return day != DayOfWeek.SATURDAY && day != DayOfWeek.SUNDAY;
    // }

    // // Check availability for testing purposes
    // public void checkAvailability() {
    //     System.out.println("Current Availability:");
    //     availability.forEach((date, timeSlots) -> {
    //         System.out.println("Date: " + date);
    //         timeSlots.forEach(time -> System.out.println("  Time: " + time));
    //     });
    // }

    @Override
    public Doctor copy() {
        Doctor newDoctor = new Doctor(getDoctorId(), getName(), getAge(), getPassword(), getGender(), getDob(), getPhoneNumber(), getEmailAddress(), getSpecialisation());
        newDoctor.setAvailability(getAvailability());

        return newDoctor;
    }
}
