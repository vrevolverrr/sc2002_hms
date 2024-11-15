package model.medrecord;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.BaseModel;
import model.enums.MedicalService;
import model.prescriptions.Prescription;

public class MedicalRecordEntry extends BaseModel {
    private final String entryId;
    
    /**
     * The date the record was recorded.
     */
    private LocalDate dateRecorded;
    
    /*
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

    public String getEntryId() {
        return entryId;
    }

    public LocalDate getDateRecorded() {
        return dateRecorded;
    }

    public void setDateRecorded(LocalDate dateRecorded) {
        this.dateRecorded = dateRecorded;
    }

    public String getPatientId() {
        return patientId;
    }
    
    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getDiagnosis() {
        return diagnosis;
    }


    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }

    public List<Prescription> getPrescription() {
        return new ArrayList<Prescription>(prescriptions);
    }
    
    public void setPrescription(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }

    public List<MedicalService> getMedicalServices() {
        return new ArrayList<MedicalService>(medicalServices);
    }

    public void setMedicalServices(List<MedicalService> medicalServices) {
        this.medicalServices = medicalServices;
    }

    /**
     * Creates an exact copy of the current {@link MedicalRecordEntry} instance.
     * @return a shallow copy of the medical record entry.
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
