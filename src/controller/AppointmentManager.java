package controller;

import model.appointments.Appointment;
import model.appointments.AppointmentSlot;
import model.appointments.TimeSlot;
import model.enums.AppointmentStatus;
import model.users.Doctor;
import model.users.Patient;
import repository.AppointmentRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager extends Manager<AppointmentManager> {
    private AppointmentRepository appointmentRepository = new AppointmentRepository();

    protected AppointmentManager() {}

    public void scheduleAppointment(AppointmentSlot slot, Patient patient) {
        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.SCHEDULED, slot.getTimeSlot(), slot.getDoctor().getDoctorId(), patient.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public void rescheduleAppointment(Appointment initialAppointment, AppointmentSlot newSlot) {
        initialAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(initialAppointment);

        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.SCHEDULED, newSlot.getTimeSlot(), newSlot.getDoctor().getDoctorId(), initialAppointment.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    public List<AppointmentSlot> getAvailableSlotsByDoctor(LocalDate date, Doctor doctor) {
        List<AppointmentSlot> availableSlots = new ArrayList<AppointmentSlot>();
        
        for (TimeSlot slot : generateTimeSlots(date)) {
            if (appointmentRepository.isAppointmentSlotAvailable(slot, doctor)) {
                availableSlots.add(new AppointmentSlot(doctor, slot));
            }
        }

        return availableSlots;
    }

    public List<Appointment> getAppointments(Doctor doctor, LocalDate date) {
        return appointmentRepository.getAppointmentsByDateAndDoctor(date, doctor);
    }

    public List<Appointment> getAppointments(Patient patient) {
        return appointmentRepository.getAppointmentsByPatient(patient);
    }

    public static List<TimeSlot> generateTimeSlots(LocalDate date) {
        final int startHour = 9;
        final int endHour = 18;
        final int slotDuration = 30;

        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (int hour = startHour; hour <= endHour; hour++) {
            for (int minute = 0; minute < 60; minute += slotDuration) {
                timeSlots.add(new TimeSlot(date.atTime(hour, minute)));
            }
        }

        return timeSlots;
    }
}
