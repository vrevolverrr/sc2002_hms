package model.medrecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;
import model.enums.MedicalService;
import model.prescriptions.Prescription;

/**
 * Represents a medical record entry.
 * A medical record entry consists of the date recorded, patient ID, doctor ID, diagnosis, treatment plan, 
 * prescriptions and medical services provided to the patient.
 * The medical record entry is uniquely identified by the entry ID.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */
public class MedicalRecordEntry extends BaseModel {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    private static final long serialVersionUID = 42L;

    /**
     * The ID of the medical record entry.
     */
    private final String entryId;
    
    /**
     * The date the record was recorded.
     */
    private LocalDate dateRecorded;
    
    /**
     * The ID of the patient to whom the record belongs.
     */
    private String patientId;

    /**
     * The name of the doctor who recorded the entry.
     */
    private String doctorId;
    
    /**
     * The diagnosis given to the patient.
     */
    private String diagnosis;

    /**
     * The treatment plan for the patient.
     */
    private String treatmentPlan;

    /**
     * The prescription given to the patient.
     */
    private List<Prescription> prescriptions;

    /**
     * The medical services provided to the patient.
     */
    private List<MedicalService> medicalServices;


    /**
     * Constructor for a {@link MedicalRecordEntry}.
     * @param entryId the unique ID of the medical record entry.
     * @param dateRecorded the date the record was recorded.
     * @param patientId the ID of the patient to whom the record belongs.
     * @param doctorId the name of the doctor who recorded the entry.
     * @param diagnosis the diagnosis given to the patient.
     * @param treatmentPlan the treatment plan for the patient.
     * @param prescriptions the prescription given to the patient.
     * @param medicalServices the medical services provided to the patient.
     */
    public MedicalRecordEntry(
        String entryId, LocalDate dateRecorded, String patientId, String doctorId, 
        String diagnosis, String treatmentPlan,
        List<Prescription> prescriptions, List<MedicalService> medicalServices) {
        
        super(entryId);

        this.entryId = entryId;
        this.dateRecorded = dateRecorded;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.diagnosis = diagnosis;
        this.treatmentPlan = treatmentPlan;
        this.prescriptions = prescriptions;
        this.medicalServices = medicalServices;
    }

    /**
     * Gets the unique ID of the medical record entry.
     * @return the unique ID of the medical record entry.
     */
    public String getEntryId() {
        return entryId;
    }

    /**
     * Gets the date the record was recorded.
     * @return the date the record was recorded.
     */
    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    /**
     * Sets the date the record was recorded.
     * @param dateRecorded the date the record was recorded.
     */
    public void setDateRecorded(LocalDate dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    /**
     * Gets the ID of the patient to whom the record belongs.
     * @return the ID of the patient to whom the record belongs.
     */
    public String getPatientId() {
        return patientId;
    }
    
    /**
     * Sets the ID of the patient to whom the record belongs.
     * @param patientId the ID of the patient to whom the record belongs.
     */
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    /**
     * Gets the name of the doctor who recorded the entry.
     * @return the name of the doctor who recorded the entry.
     */
    public String getDoctorId() {
        return doctorId;
    }

    /**
     * Sets the name of the doctor who recorded the entry.
     * @param doctorId the name of the doctor who recorded the entry.
     */
    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    /**
     * Gets the diagnosis given to the patient.
     * @return the diagnosis given to the patient.
     */
    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Sets the diagnosis given to the patient.
     * @param diagnosis the diagnosis given to the patient.
     */
    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    /**
     * Gets the treatment plan for the patient.
     * @return the treatment plan for the patient.
     */
    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    /**
     * Sets the treatment plan for the patient.
     * @param treatmentPlan the treatment plan for the patient.
     */
    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    /**
     * Gets the prescription given to the patient.
     * @return the {@link List} pf {@link Prescription} given to the patient.
     */
    public List<Prescription> getPrescription() {
        return new ArrayList<Prescription>(prescriptions);
    }
    
    /**
     * Sets the prescription given to the patient.
     * @param prescriptions the prescription given to the patient.
     */
    public void setPrescription(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    /**
     * Gets the medical services provided to the patient.
     * @return the {@link List} of {@link MedicalService} provided to the patient.
     */
    public List<MedicalService> getMedicalServices() {
        return new ArrayList<MedicalService>(medicalServices);
    }

    /**
     * Sets the medical services provided to the patient.
     * @param medicalServices the {@link MedicalService} provided to the patient.
     */
    public void setMedicalServices(List<MedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }

    /**
     * Creates an exact copy of the current {@link MedicalRecordEntry} instance.
     * @return a shallow copy of the {@link MedicalRecordEntry}.
     */
    @Override
    public MedicalRecordEntry copy() {
        List<Prescription> prescriptionCopy = new ArrayList<Prescription>();
        List<MedicalService> medicalServicesCopy = new ArrayList<MedicalService>();

        for (Prescription prescription : prescriptions) {
            prescriptionCopy.add(prescription.copy());
        }

        for (MedicalService service : medicalServices) {
            medicalServicesCopy.add(service);
        }

        return new MedicalRecordEntry(
            getEntryId(), getDateRecorded(), getPatientId(), getDoctorId(), 
            getDiagnosis(), getTreatmentPlan(), prescriptionCopy, medicalServicesCopy);
    }
    
}
