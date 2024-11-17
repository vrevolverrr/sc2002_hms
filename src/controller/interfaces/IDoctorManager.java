package controller.interfaces;

import model.users.Doctor;
import model.availability.TimePeriod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

/**
 * This interface provides methods to manage doctors in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IDoctorManager {
    /**
     * Retrieves all doctors.
     *
     * @return a list {@link Doctor} of all doctors.
     */
    List<Doctor> getAllDoctors();

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorId the ID of the doctor.
     * @return the doctor with the specified ID.
     */
    Doctor getDoctor(String doctorId);

    /**
     * Sets the availability of a doctor for a specific day of the week.
     *
     * @param doctor the doctor whose availability is being set.
     * @param day the day of the week.
     * @param availablePeriod the time period during which the doctor is available.
     */
    void setDoctorAvailability(Doctor doctor, DayOfWeek day, TimePeriod availablePeriod);

    /**
     * Sets the availability of a doctor for a specific date.
     *
     * @param doctor the doctor whose availability is being set.
     * @param date the date.
     * @param availablePeriod the time period during which the doctor is available.
     */
    void setDoctorAvailability(Doctor doctor, LocalDate date, TimePeriod availablePeriod);

    /**
     * Clears the availability of a doctor for a specific day of the week.
     *
     * @param doctor the doctor whose availability is being cleared.
     * @param day the day of the week.
     */
    void clearDoctorAvailability(Doctor doctor, DayOfWeek day);
    
    /**
     * Clears the availability of a doctor for a specific date.
     *
     * @param doctor the doctor whose availability is being cleared.
     * @param date the date.
     */
    void clearDoctorAvailability(Doctor doctor, LocalDate date);
}