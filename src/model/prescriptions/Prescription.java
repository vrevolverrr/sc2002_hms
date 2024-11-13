package model.prescriptions;

import java.io.Serializable;

import model.enums.MedicineFrequency;
import model.enums.PrescriptionStatus;

public class Prescription implements Serializable {
    private final String drugId;
    private final int quantity;
    private final MedicineDosage dosage;
    private final MedicineFrequency frequency;
    private PrescriptionStatus status;

    public Prescription(String drugId, int quantity, MedicineDosage dosage, MedicineFrequency frequency) {
        this.drugId = drugId;
        this.quantity = quantity;
        this.dosage = dosage;
        this.frequency = frequency;
        this.status = PrescriptionStatus.PENDING;
    }

    public String getDrugId() {
        return this.drugId;
    }

    public int getQuantity() {
        return this.quantity;
    }
    
    public MedicineDosage getDosage() {
        return this.dosage;
    }

    public MedicineFrequency getFrequency() {
        return frequency;
    }

    public PrescriptionStatus getStatus() {
        return this.status;
    }

    public void setStatus(PrescriptionStatus status) {
        this.status = status;
    }

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
}
