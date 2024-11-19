package model.prescriptions;

import model.SerializableCopyable;
import model.enums.DosageUnit;

/**
 * An immutable representation of the dosage of a medicine.
 * 
 * A dosage consists of a quantity and a unit.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */

public class MedicineDosage implements SerializableCopyable {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     */
    private static final long serialVersionUID = 42L;
    
    /**
     * The quantity of the dosage.
     */
    private final int quantity;

    /**
     * The unit of the dosage.
     */
    private final DosageUnit unit;

    /**
     * Constructor for a {@link MedicineDosage}.
     * @param quantity the quantity of the dosage.
     * @param unit the unit of the dosage.
     */
    public MedicineDosage(int quantity, DosageUnit unit) {
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * Gets the quantity of the dosage.
     * @return the quantity of the dosage.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Gets the unit of the dosage.
     * @return the unit of the dosage.
     */
    public DosageUnit getUnit() {
        return unit;
    }

    /**
     * Returns a string representation of the dosage.
     * @return a string representation of the dosage.
     */
    @Override
    public String toString() {
        return quantity + " " + unit;
    }

    /**
     * Creates and returns a copy of the {@link MedicineDosage} instance.
     * @return a copy of the instance
     */ 
    @Override
    public MedicineDosage copy() {
        return new MedicineDosage(getQuantity(), getUnit());
    }
}
