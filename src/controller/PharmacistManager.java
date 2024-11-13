package controller;

import java.util.List;

import model.appointments.Appointment;
import model.enums.PrescriptionStatus;
import model.prescriptions.Prescription;

public class PharmacistManager extends Manager<PharmacistManager> {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    public void dispensePrescriptions(Appointment appointment) {
        List<Prescription> prescriptions = appointment.getOutcomeRecord().getPrescriptions();

        for (Prescription prescription : prescriptions) {
            dispense(prescription);
            prescription.setStatus(PrescriptionStatus.DISPENSED);
        }

        appointmentManager.updateAppointment(appointment);
    }

    public void dispense(Prescription prescription) {
        inventoryManager.deductStock(prescription.getDrugId(),  prescription.getDosage().getQuantity());
    }
    
}
