package model.inventory;

import java.io.Serializable;

/**
 * An immutable representation of a replenishment request.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */
public class ReplenishmentRequest implements Serializable {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    private static final long serialVersionUID = 42L;
    
    /**
     * The unique ID of the pharmacist.
     */
    private String pharmacistId;
    
    /**
     * The quantity of the replenishment request.
     */
    private int quantity;

    /**
     * Constructor for a {@link ReplenishmentRequest}.
     * @param pharmacistId the unique ID of the pharmacist.
     * @param quantity the quantity of the replenishment request.
     */
    public ReplenishmentRequest(String pharmacistId, int quantity) {
        this.pharmacistId = pharmacistId;
        this.quantity = quantity;
    }

    /**
     * Gets the unique ID of the pharmacist.
     * @return the unique ID of the pharmacist.
     */
    public String getPharmacistId() {
        return this.pharmacistId;
    }

    /**
     * Gets the quantity of the replenishment request.
     * @return the quantity of the replenishment request.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Creates and returns a copy of the {@link ReplenishmentRequest} instance.
     * @return a copy of the instance
     */
    public ReplenishmentRequest copy() {
        return new ReplenishmentRequest(getPharmacistId(), getQuantity());
    }
}
