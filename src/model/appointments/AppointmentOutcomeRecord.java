package model.appointments;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.SerializableCopyable;
import model.enums.MedicalService;
import model.prescriptions.Prescription;

/**
 * Represents the immutable outcome of a medical appointment.
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class AppointmentOutcomeRecord implements SerializableCopyable {
    /**
     * Serial version UID for ensuring compatibility during serialization.
     */
    static final long serialVersionUID = 42L;

    /**
     * The date the outcome record was created.
     */
    private final LocalDate recordedDate;

    /**
     * The list of {@link Prescription} objects associated with the appointment.
     */
    private final List<Prescription> prescriptions;

    /**
     * The list of {@link MedicalService} provided during the appointment.
     */
    private final List<MedicalService> services;

    /**
     * Notes from the consultation during the appointment.
     */
    private final String consultationNotes;

    /**
     * Constructs an instance of {@link AppointmentOutcomeRecord}.
     * 
     * @param recordedDate the date the record was created.
     * @param prescriptions the list of prescriptions issued during the appointment.
     * @param services the list of medical services provided during the appointment.
     * @param consultationNotes the notes from the consultation during the appointment.
     */
    public AppointmentOutcomeRecord(
        LocalDate recordedDate, List<Prescription> prescriptions, List<MedicalService> services, 
        String consultationNotes) {
        
        this.recordedDate = recordedDate;
        this.prescriptions = prescriptions;
        this.services = services;
        this.consultationNotes = consultationNotes;
    }

    /**
     * Gets the recorded date of the outcome record.
     * 
     * @return a {@link LocalDate} representing the recorded date.
     */
    public LocalDate getRecordedDate() {
        return LocalDate.from(recordedDate);
    }

    /**
     * Gets the recorded date formatted as a string.
     * 
     * @return a {@link String} representing the recorded date in the format "dd/MM/yyyy".
     */
    public String getFormattedRecordedDate() {
        return this.recordedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Gets the consultation notes for the appointment.
     * 
     * @return a {@link String} containing the consultation notes.
     */
    public String getConsultationNotes() {
        return consultationNotes;
    }

    /**
     * Gets the list of prescriptions issued during the appointment.
     * 
     * @return an unmodifiable {@link List} of {@link Prescription}.
     */
    public List<Prescription> getPrescriptions() {
        return List.copyOf(prescriptions);
    }

    /**
     * Gets the list of medical services provided during the appointment.
     * 
     * @return an unmodifiable {@link List} of {@link MedicalService}.
     */
    public List<MedicalService> getServices() {
        return List.copyOf(services);
    }

    /**
     * Creates a deep copy of this instance of {@link AppointmentOutcomeRecord}.
     * @return the deep copy of the record.
     */
    @Override
    public AppointmentOutcomeRecord copy() {
        return new AppointmentOutcomeRecord(getRecordedDate(), getPrescriptions(), getServices(), getConsultationNotes());
    }
}
