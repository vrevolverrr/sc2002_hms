package controller;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IPharmacistManager;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;

/**
 * Manages operations related to pharmacists.
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */

public class PharmacistManager implements IPharmacistManager {
    
    private final IInventoryManager inventoryManager;
    private final IAppointmentManager appointmentManager;

    public PharmacistManager(IInventoryManager inventoryManager, IAppointmentManager appointmentManager) {
        this.inventoryManager = inventoryManager;
        this.appointmentManager = appointmentManager;
    }

    /**
     * Dispense all {@link Prescription} for an {@link Appointment}.
     * @param appointment The {@link Appointment} to dispense {@link Prescription} for.
     */
    public void dispensePrescriptions(Appointment appointment) {
        List<Prescription> prescriptions = appointment.getOutcomeRecord().getPrescriptions();

        for (Prescription prescription : prescriptions) {
            dispense(prescription);
            prescription.setStatus(PrescriptionStatus.DISPENSED);
        }

        appointmentManager.updateAppointment(appointment);
    }

    /**
     * Dispense a single {@link Prescription} for an {@link Appointment}.
     * @param appointment The {@link Appointment} to dispense the {@link Prescription} for.
     * @param prescription The {@link Prescription} to dispense.
     */
    public void dispensePrescription(Appointment appointment, Prescription prescription) {
        dispense(prescription);

        appointment.getOutcomeRecord().getPrescriptions().stream()
            .filter(p -> p.equals(prescription))
            .findFirst()
            .ifPresent(p -> p.setStatus(PrescriptionStatus.DISPENSED));

        appointmentManager.updateAppointment(appointment);
    }

    /**
     * Dispenses a {@link Prescription}.
     * @param prescription The {@link Prescription} to dispense.
     */
    public void dispense(Prescription prescription) {
        inventoryManager.deductStock(prescription.getDrugId(),  prescription.getQuantity());
    }
    
}
