package controller;

import java.util.List;

import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;

public class PharmacistManager extends Manager<PharmacistManager> {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

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