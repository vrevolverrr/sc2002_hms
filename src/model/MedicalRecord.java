package model;

public class MedicalRecord extends BaseModel {
    private String recordId;

    /**
     * The ID of the appointment corresponding to this medical record.
     */
    private String appointmentId;

    /**
     * The ID of the doctor who created this medical record.
     */
    private String doctorId;

    private String patientId;

    
    public MedicalRecord(String recordId, String appointmentId, String doctorId, String patientId) {
        super(recordId);
        
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }


    @Override
    public BaseModel copy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
    
}
