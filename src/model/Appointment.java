package model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import model.enums.AppointmentStatus;

public class Appointment {
    private Doctor doc;
    private LocalDate date;
    private LocalTime slot;
    private AppointmentStatus status;

    public Appointment(Doctor doc, LocalDate date, LocalTime slot) {
        if (doc == null) {
            throw new IllegalArgumentException("Doctor cannot be null.");
        }
        if (date == null || date.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Appointment date cannot be null or in the past.");
        }
        if (slot == null) {
            throw new IllegalArgumentException("Time slot cannot be null.");
        }
        this.doc = doc;
        this.date = date;
        this.slot = slot;
        this.status = AppointmentStatus.SCHEDULED;
    }

    public Doctor getDoc() {
        return doc;
    }

    public void setDoc(Doctor doc){
        this.doc = doc;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public LocalTime getSlot() {
        return slot;
    }

    public void setSlot(LocalTime slot){
        this.slot = slot;
    }
    
    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        this.status = status;
    }

    @Override
    public String toString() {
        return "Appointment with Dr. " + /*doc.getName() +*/
               " on " + date +
               " at " + slot +
               " (Status: " + status + ")";
    }

    @Override
    //consider whetherb two appoinments are equal;
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Appointment that = (Appointment) obj;
        return doc.equals(that.doc) && date.equals(that.date) && slot.equals(that.slot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(doc, date, slot);
    }
}
