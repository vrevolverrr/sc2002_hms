package controller;

import repository.interfaces.IDoctorRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import controller.interfaces.IDoctorManager;
import model.availability.Availability;
import model.availability.TimePeriod;
import model.users.Doctor;

/**
 * Manages oerations related to doctors.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
*/
public class DoctorManager implements IDoctorManager {
    /**
     * Repository for accessing doctor data.
     */
    private final IDoctorRepository doctorRepository;

    /**
     * Creates a new DoctorManager.
     * @param doctorRepository the repository for accessing doctor data.
     */
    public DoctorManager(IDoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorId the ID of the doctor.
     * @return the {@link Doctor} with the specified ID.
     */
    public Doctor getDoctor(String doctorId) {
        return doctorRepository.findById(doctorId);
    }

    /**
     * Retrieves all doctors.
     *
     * @return a list {@link Doctor} of all doctors.
     */
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getItems().values().stream().distinct().toList();
    }

    /**
     * Retrieves a doctor by their ID.
     *
     * @param doctorId the ID of the doctor.
     * @return the doctor with the specified ID.
     */
    public Doctor getDoctorById(String doctorId) {
        return doctorRepository.findById(doctorId);
    }

    /**
     * Sets the availability of a doctor for a specific day of the week.
     *
     * @param doctor the doctor whose availability is being set.
     * @param day the day of the week.
     * @param availablePeriod the time period during which the doctor is available.
     */
    public void setDoctorAvailability(Doctor doctor, DayOfWeek day, TimePeriod availablePeriod) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(day, availablePeriod);

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    /**
     * Sets the availability of a doctor for a specific date.
     *
     * @param doctor the doctor whose availability is being set.
     * @param date the date.
     * @param availablePeriod the time period during which the doctor is available.
     */
    public void setDoctorAvailability(Doctor doctor, LocalDate date, TimePeriod availablePeriod) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(date, availablePeriod);

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    /**
     * Clears the availability of a doctor for a specific day of the week.
     *
     * @param doctor the doctor whose availability is being cleared.
     * @param day the day of the week.
     */
    public void clearDoctorAvailability(Doctor doctor, DayOfWeek day) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(day, TimePeriod.defaultPeriod());

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    /**
     * Clears the availability of a doctor for a specific date.
     *
     * @param doctor the doctor whose availability is being cleared.
     * @param date the date.
     */
    public void clearDoctorAvailability(Doctor doctor, LocalDate date) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(date, TimePeriod.defaultPeriod());

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }
}
