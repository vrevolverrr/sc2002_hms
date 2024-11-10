package model.prescriptions;

import model.InventoryItem;

public class Prescription {
    private final InventoryItem drug;
    private final int quantity;
    private PrescriptionStatus status;

    public Prescription(InventoryItem drug, int quantity) {
        this.drug = drug;
        this.quantity = quantity;
    }

    public InventoryItem getDrug() {
        return this.drug;
    }
    
    public int getQuantity() {
        return this.quantity;
    }

    public PrescriptionStatus getStatus() {
        return this.status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }
}
