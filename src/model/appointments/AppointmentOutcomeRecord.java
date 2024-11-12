package model.appointments;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.enums.MedicalService;
import model.prescriptions.Prescription;

/**
 * An imutable class representing the outcome of a medical appointment.
 */
public class AppointmentOutcomeRecord implements Serializable {
    static final long serialVersionUID = 42L;

    private final LocalDate recordedDate;
    private final List<Prescription> prescriptions;
    private final List<MedicalService> services;
    private final String consultationNotes;

    public AppointmentOutcomeRecord(
        LocalDate recordedDate, List<Prescription> prescriptions, List<MedicalService> services, 
        String consultationNotes) {
        
        this.recordedDate = recordedDate;
        this.prescriptions = prescriptions;
        this.services = services;
        this.consultationNotes = consultationNotes;
    }

    public LocalDate getRecordedDate() {
        return LocalDate.from(recordedDate);
    }

    public String getFormattedRecordedDate() {
        return this.recordedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public String getConsultationNotes() {
        return consultationNotes;
    }

    public List<Prescription> getPrescriptions() {
        return List.copyOf(prescriptions);
    }

    public List<MedicalService> getServices() {
        return List.copyOf(services);
    }

    /**
     * Creates a deep copy of this instance of {@link AppointmentOutcomeRecord}.
     * @return the deep copy of the record.
     */
    public AppointmentOutcomeRecord copy() {
        return new AppointmentOutcomeRecord(getRecordedDate(), getPrescriptions(), getServices(), getConsultationNotes());
    }
}
