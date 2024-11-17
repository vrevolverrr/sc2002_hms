package controller;

import java.util.List;

import controller.interfaces.IPharmacistManager;
import model.appointments.Appointment;
import model.inventory.InventoryItem;
import model.prescriptions.Prescription;
import repository.interfaces.IAppointmentRepository;
import repository.interfaces.IInventoryRepository;

/**
 * Manages operations related to pharmacists.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */

public class PharmacistManager implements IPharmacistManager {
    /**
     * Repository for accessing inventory data.
     */
    private final IInventoryRepository inventoryRepository;

    /**
     * Repository for accessing appointment data.
     */
    private final IAppointmentRepository appointmentRepository;

    /**
     * Creates a new {@link PharmacistManager}.
     * @param inventoryRepository the repository for accessing inventory data.
     * @param appointmentRepository the repository for accessing appointment data.
     */
    public PharmacistManager(
        IInventoryRepository inventoryRepository, IAppointmentRepository appointmentRepository) {
        
        this.inventoryRepository = inventoryRepository;
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * Dispense all {@link Prescription} for an {@link Appointment}.
     * @param appointment The {@link Appointment} to dispense {@link Prescription} for.
     */
    public void dispensePrescriptions(Appointment appointment) {
        List<Prescription> prescriptions = appointment.getOutcomeRecord().getPrescriptions();

        for (Prescription prescription : prescriptions) {
            dispense(prescription);
            prescription.dispense();
        }

        appointmentRepository.save(appointment);
    }

    /**
     * Dispense a single {@link Prescription} for an {@link Appointment}.
     * @param appointment The {@link Appointment} to dispense the {@link Prescription} for.
     * @param prescription The {@link Prescription} to dispense.
     */
    public void dispensePrescription(Appointment appointment, Prescription prescription) {
        dispense(prescription);

        appointment.getOutcomeRecord().getPrescriptions().stream()
            .filter(p -> p.equals(prescription) && p.isPending())
            .findFirst()
            .ifPresent(p -> p.dispense());

        appointmentRepository.save(appointment);
    }

    /**
     * Dispenses a {@link Prescription}.
     * @param prescription The {@link Prescription} to dispense.
     */
    public void dispense(Prescription prescription) {
        InventoryItem item = inventoryRepository.findById(prescription.getDrugId());
        item.deductStock(prescription.getQuantity());
        
        inventoryRepository.save(item);
    }
    
}
