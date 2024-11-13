package model.inventory;

import java.io.Serializable;

/**
 * An immutable class representing a replenishment request.
 */
public class ReplenishmentRequest implements Serializable {
    static final long serialVersionUID = 42L;
    
    private String pharmacistId;
    private int quantity;

    public ReplenishmentRequest(String pharmacistId, int quantity) {
        this.pharmacistId = pharmacistId;
        this.quantity = quantity;
    }

    public String getPharmacistId() {
        return this.pharmacistId;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public ReplenishmentRequest copy() {
        return new ReplenishmentRequest(getPharmacistId(), getQuantity());
    }
}
