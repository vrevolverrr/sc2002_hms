package model.prescriptions;

import java.io.Serializable;

import model.enums.MedicineFrequency;
import model.enums.PrescriptionStatus;

public class Prescription implements Serializable {
    private final String drugId;
    private final MedicineDosage dosage;
    private final MedicineFrequency frequency;
    private PrescriptionStatus status;

    public Prescription(String drugId, MedicineDosage dosage, MedicineFrequency frequency) {
        this.drugId = drugId;
        this.dosage = dosage;
        this.frequency = frequency;
        this.status = PrescriptionStatus.PENDING;
    }

    public String getDrugId() {
        return this.drugId;
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
}
