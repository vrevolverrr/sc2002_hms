package controller;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IPharmacistManager;
import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;

public class PharmacistManager implements IPharmacistManager {
    private final IInventoryManager inventoryManager;
    private final IAppointmentManager appointmentManager;

    public PharmacistManager(IInventoryManager inventoryManager, IAppointmentManager appointmentManager) {
        this.inventoryManager = inventoryManager;
        this.appointmentManager = appointmentManager;
    }

    /**
     * Dispense all prescriptions for an appointment.
     * @param appointment The appointment to dispense prescriptions for.
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
     * Dispense a single prescription for an appointment.
     * @param appointment The appointment to dispense the prescription for.
     * @param prescription The prescription to dispense.
     */
    public void dispensePrescription(Appointment appointment, Prescription prescription) {
        dispense(prescription);

        appointment.getOutcomeRecord().getPrescriptions().stream()
            .filter(p -> p.equals(prescription))
            .findFirst()
            .ifPresent(p -> p.setStatus(PrescriptionStatus.DISPENSED));

        appointmentManager.updateAppointment(appointment);
    }

    public void dispense(Prescription prescription) {
        inventoryManager.deductStock(prescription.getDrugId(),  prescription.getQuantity());
    }
    
}
