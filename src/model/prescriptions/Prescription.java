package model.prescriptions;

import model.SerializableCopyable;
import model.enums.MedicineFrequency;
import model.enums.PrescriptionStatus;

/**
 * Represents a prescription for a medicine.
 * A prescription consists of the drug ID, quantity, dosage and frequency of the medicine.
 * The status of the prescription can be either PENDING or COMPLETED.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */
public class Prescription implements SerializableCopyable {
    static final long serialVersionUID = 42L;

    /**
     * The unique ID of the drug.
     */
    private final String drugId;

    /**
     * The quantity of the drug.
     */
    private final int quantity;

    /**
     * The dosage of the drug.
     */
    private final MedicineDosage dosage;

    /**
     * The frequency of the drug.
     */
    private final MedicineFrequency frequency;

    /**
     * The status of the prescription.
     */
    private PrescriptionStatus status;

    /**
     * Constructor for a {@link Prescription}.
     * @param drugId the unique ID of the drug.
     * @param quantity the quantity of the drug.
     * @param dosage the dosage of the drug.
     * @param frequency the frequency of the drug.
     */
    public Prescription(String drugId, int quantity, MedicineDosage dosage, MedicineFrequency frequency) {
        this.drugId = drugId;
        this.quantity = quantity;
        this.dosage = dosage;
        this.frequency = frequency;
        this.status = PrescriptionStatus.PENDING;
    }

    /**
     * Gets the unique ID of the drug.
     * @return the unique ID of the drug.
     */
    public String getDrugId() {
        return this.drugId;
    }

    /**
     * Gets the quantity of the drug.
     * @return the quantity of the drug.
     */
    public int getQuantity() {
        return this.quantity;
    }
    
    /**
     * Gets the dosage of the drug.
     * @return the dosage of the drug.
     */
    public MedicineDosage getDosage() {
        return this.dosage.copy();
    }

    /**
     * Gets the frequency of the drug.
     * @return the frequency of the drug.
     */
    public MedicineFrequency getFrequency() {
        return frequency;
    }

    /**
     * Gets the status of the prescription.
     * @return the status of the prescription.
     */
    public PrescriptionStatus getStatus() {
        return this.status;
    }

    /**
     * Checks if the prescription is dispensed.
     * @return true if the prescription is dispensed, false otherwise.
     */
    public boolean isDispensed() {
        return this.status == PrescriptionStatus.DISPENSED;
    }

    /**
     * Checks if the prescription is pending.
     * @return true if the prescription is pending, false otherwise.
     */
    public boolean isPending() {
        return this.status == PrescriptionStatus.PENDING;
    }

    /**
     * Dispenses the prescription by marking the status as {@code DISPENSED}.
     */
    public void dispense() {
        this.status = PrescriptionStatus.DISPENSED;
    }

    /**
     * Checks if the prescription is equal to another object.
     * @return true if the prescription is equal to the other object, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Prescription)) return false;

        Prescription pres = (Prescription) obj;

        return pres.getDrugId().equals(this.drugId) &&
            pres.getQuantity() == this.quantity &&
            pres.getDosage().equals(this.dosage) &&
            pres.getFrequency().equals(this.frequency) &&
            pres.getStatus().equals(this.status);
    }

    /**
     * Creates and returns a copy of the {@link Prescription} instance.
     * @return a copy of the instance
     */
    @Override
    public Prescription copy() {
        return new Prescription(getDrugId(), getQuantity(), getDosage(), getFrequency());
    }
}
