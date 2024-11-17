package controller.interfaces;

import model.users.Doctor;
import model.availability.TimePeriod;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public interface IDoctorManager {
    List<Doctor> getAllDoctors();

    Doctor getDoctor(String doctorId);

    void setDoctorAvailability(Doctor doctor, DayOfWeek day, TimePeriod availablePeriod);

    void setDoctorAvailability(Doctor doctor, LocalDate date, TimePeriod availablePeriod);

    void clearDoctorAvailability(Doctor doctor, DayOfWeek day);
    
    void clearDoctorAvailability(Doctor doctor, LocalDate date);
}