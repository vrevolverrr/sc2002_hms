package controller;

import repository.DoctorRepository;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import controller.interfaces.IDoctorManager;
import model.availability.Availability;
import model.availability.TimePeriod;
import model.users.Doctor;

public class DoctorManager implements IDoctorManager {
    private final DoctorRepository doctorRepository;

    public DoctorManager(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Doctor getDoctor(String doctorId) {
        return doctorRepository.findById(doctorId);
    }
    
    public List<Doctor> getAllDoctors() {
        return doctorRepository.getItems().values().stream().distinct().toList();
    }

    public void setDoctorAvailability(Doctor doctor, DayOfWeek day, TimePeriod availablePeriod) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(day, availablePeriod);

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    public void setDoctorAvailability(Doctor doctor, LocalDate date, TimePeriod availablePeriod) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(date, availablePeriod);

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    public void clearDoctorAvailability(Doctor doctor, DayOfWeek day) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(day, TimePeriod.defaultPeriod());

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }

    public void clearDoctorAvailability(Doctor doctor, LocalDate date) {
        Availability availability = doctor.getAvailability();
        availability.setAvailability(date, TimePeriod.defaultPeriod());

        doctor.setAvailability(availability);
        doctorRepository.save(doctor);
    }
}
