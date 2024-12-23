package controller.interfaces;

import model.appointments.Appointment;
import model.prescriptions.Prescription;

/**
 * This interface provides methods to manage prescriptions in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IPharmacistManager {
    /**
     * Dispense all prescriptions for an appointment.
     * @param appointment The appointment to dispense prescriptions for.
     */
    void dispensePrescriptions(Appointment appointment);

    /**
     * Dispense a single prescription for an appointment.
     * @param appointment The appointment to dispense the prescription for.
     * @param prescription The prescription to dispense.
     */
    void dispensePrescription(Appointment appointment, Prescription prescription);

    /**
     * Dispense a single prescription.
     * @param prescription The prescription to dispense.
     */
    void dispense(Prescription prescription);
}