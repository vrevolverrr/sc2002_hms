package controller;

import model.appointments.Appointment;
import model.appointments.AppointmentOutcomeRecord;
import model.appointments.AppointmentSlot;
import model.appointments.TimeSlot;
import model.availability.Availability;
import model.enums.AppointmentStatus;
import model.enums.MedicalService;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;
import model.users.Doctor;
import model.users.Patient;
import repository.AppointmentRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentManager extends Manager<AppointmentManager> {
    private final AppointmentRepository appointmentRepository = new AppointmentRepository();

    public final static int START_HOUR = 8;
    public final static int END_HOUR = 17;
    public final static int SLOT_DURATION = 30;

    protected AppointmentManager() {
        // Every time the AppointmentManager is instantiated, it will check for past appointments
        // and mark them as fulfilled if it is overdue.
        fulfillPastAppointments();
    }

    /**
     * Marks all past appointments as fulfilled if they are overdue.
     * This method is called every time the AppointmentManager is instantiated.
     */
    private void fulfillPastAppointments() {
        appointmentRepository.findBy(appointment -> appointment.isScheduled())
            .forEach(appointment -> {
                if (appointment.getTimeSlot().getDateTime().isBefore(LocalDateTime.now())) {
                    appointment.setStatus(AppointmentStatus.FULFILLED);
                    appointmentRepository.save(appointment);
                }
            });
    }

    public void updateAppointment(Appointment appointment) {
        appointmentRepository.save(appointment);
    }

    /**
     * Schedules an appointment for a patient. Marks the appointment as {@code REQUESTED} 
     * for doctor approval.
     * @param slot the appointment slot to schedule the appointment.
     * @param patient the patient to schedule the appointment for.
     */
    public void scheduleAppointment(AppointmentSlot slot, Patient patient) {
        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.REQUESTED, slot.getTimeSlot(), slot.getDoctor().getDoctorId(), patient.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    /**
     * Cancels a scheduled appointment.
     * @param appointment the appointment to cancel.
     */
    public void cancelAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    /**
     * Reschedules an appointment to a new appointment slot.
     * @param initialAppointment the initial appointment to reschedule.
     * @param newSlot the new appointment slot.
     */
    public void rescheduleAppointment(Appointment initialAppointment, AppointmentSlot newSlot) {
        initialAppointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(initialAppointment);

        Appointment newAppointment = new Appointment(appointmentRepository.generateId(), AppointmentStatus.REQUESTED, newSlot.getTimeSlot(), newSlot.getDoctor().getDoctorId(), initialAppointment.getPatientId());
        appointmentRepository.save(newAppointment);
    }

    public void acceptAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.SCHEDULED);
        appointmentRepository.save(appointment);
    }

    public void declineAppointment(Appointment appointment) {
        appointment.setStatus(AppointmentStatus.CANCELLED);
        appointmentRepository.save(appointment);
    }

    public AppointmentOutcomeRecord updateAppointmentOutcome(
        Appointment appointment, String consultationNotes,
        List<Prescription> prescriptions, List<MedicalService> services) {
        
        AppointmentOutcomeRecord outcomeRecord = 
            new AppointmentOutcomeRecord(LocalDate.now(), 
                List.copyOf(prescriptions), List.copyOf(services), consultationNotes);

        appointment.setOutcomeRecord(outcomeRecord);
        appointment.setStatus(AppointmentStatus.COMPLETED);
        
        appointmentRepository.save(appointment);

        return outcomeRecord;
    }

    /**
     * Gets the available time slots for an appointment of a doctor on a given date.
     * @param date the date to get the available slots for.
     * @param doctor the doctor.
     * @return the list of avaialble time slots.
     */
    public List<AppointmentSlot> getAvailableSlotsByDoctor(LocalDate date, Doctor doctor) {
        List<AppointmentSlot> availableSlots = new ArrayList<AppointmentSlot>();

        for (TimeSlot slot : generateTimeSlots(date)) {
            // Check if the time slot is in the past.
            if (slot.getDateTime().isBefore(LocalDateTime.now())) {
                continue;
            }

            // Check if there is already an exisitng appointment at this time slot.
            if (!isAppointmentSlotAvailable(slot, doctor)) {
                continue;
            }

            // Check if the doctor is available at this time slot.
            if (!isDoctorAvailable(slot, doctor)) {
                continue;
            }

            availableSlots.add(new AppointmentSlot(doctor, slot));
        }

        return availableSlots;
    }

    /**
     * Gets all the appointments in the system, ordered by most recent first.
     * @return the list of appointments.
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.getItems().values()
            .stream().sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets all the appointments of a doctor, ordered by most recent first.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getAppointments(Doctor doctor) {
        return appointmentRepository
            .findBy((appointment) -> appointment.getDoctorId().equals(doctor.getDoctorId()))
            .stream()
            .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot()))
            .toList();
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isScheduled()
        );
    }

    /**
     * Gets the upcoming scheduled appointments of a doctor scheduled on a given date.
     * @param doctor the doctor to get the appointments for.
     * @param date the date to get the appointments for.
     * @return the list of appointments.
     */
    public List<Appointment> getScheduledAppointments(Doctor doctor, LocalDate date) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.getTimeSlot().getDate().equals(date) &&
            appointment.isScheduled()
        );
    }

    /**
     * Gets the upcoming scheduled appointments of the patient, ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getScheduledAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isScheduled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the upcoming pending appointments of a patient, that is pending doctor approval.
     * @param patient the patient to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets both upcoming {@code SCHEDULED} and {@code REQUESTED} appointments of a patient, 
     * ordered by most recent first.
     * @param patient the patient to get the appointments for.
     * @return the list of appointments, ordered by most recent first.
     */
    public List<Appointment> getUpcomingAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            (appointment.isScheduled() || appointment.isRequested())
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the upcoming pending appointments assigned to a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of pending appointments, ordered by most recent first.
     */
    public List<Appointment> getPendingAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            !appointment.getTimeSlot().getDate().isBefore(LocalDate.now()) &&
            appointment.isRequested()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Gets the fulfilled appointments of a doctor.
     * @param doctor the doctor to get the appointments for.
     * @return the list of fulfilled appointments, ordered by most recent first.
     */
    public List<Appointment> getFulfilledAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    public List<Appointment> getCompletedAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) &&
            appointment.isCompleted()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    public List<Appointment> getPastAppointments(Patient patient) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getPatientId().equals(patient.getPatientId()) && 
            appointment.isCompleted() || appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    public List<Appointment> getPastAppointments(Doctor doctor) {
        return appointmentRepository.findBy((appointment) -> 
            appointment.getDoctorId().equals(doctor.getDoctorId()) && 
            appointment.isCompleted() || appointment.isFulfilled()
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    public List<Appointment> getUndispensedAppointments() {
        return appointmentRepository.findBy((appointment) -> 
            appointment.isCompleted() && 
            appointment.getOutcomeRecord() != null && // this condition is kept for redudnancy checks
            appointment.getOutcomeRecord().getPrescriptions().stream()
            .anyMatch(prescription -> prescription.getStatus() == PrescriptionStatus.PENDING)
        )
        .stream()
        .sorted((a, b) -> a.getTimeSlot().compareTo(b.getTimeSlot())).toList();
    }

    /**
     * Checks whether a time slot is available for an appointment with a doctor.
     * @param slot the time slot to check.
     * @param doctor the doctor to check the availability for.
     * @return whether the slot is available.
     */
    public boolean isAppointmentSlotAvailable(TimeSlot slot, Doctor doctor) {
        List<Appointment> scheduledAppointments = getScheduledAppointments(doctor, slot.getDateTime().toLocalDate());

        for (Appointment appointment : scheduledAppointments) {
            if (appointment.getTimeSlot().equals(slot)) {
                return false;
            }
        }

        return true;
    }

    public boolean isDoctorAvailable(TimeSlot slot, Doctor doctor) {
        Availability availability =  doctor.getAvailability();

        // Only have to check against the date, since it falls back to checking the default
        // availability of the day of week if the date is not found in the availability map.
        if (availability.getAvailability(slot.getDate()).contains(slot.getTime())) {
            return true;
        }
        
        return false;
    }

    /**
     * Generates all possible time slots for appointments on a given date.
     * @param date the date to generate the time slots for.
     * @return the list of time slots.
     */
    public static List<TimeSlot> generateTimeSlots(LocalDate date) {
        // The time slots start at 8:00am, and the last slot start at 5:30pm.
        // for a total of 20 time slots per day.
        List<TimeSlot> timeSlots = new ArrayList<TimeSlot>();
        for (int hour = START_HOUR; hour < END_HOUR; hour++) {
            for (int minute = 0; minute < 60; minute += SLOT_DURATION) {
                timeSlots.add(new TimeSlot(date.atTime(hour, minute)));
            }
        }

        return timeSlots;
    }
}
