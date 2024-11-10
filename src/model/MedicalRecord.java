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

    


    public MedicalRecord(String recordId, String appointmentId, String doctorId) {
        super(recordId);
        
        this.recordId = recordId;
        this.appointmentId = appointmentId;
        this.doctorId = doctorId;
    }


    @Override
    public BaseModel copy() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'copy'");
    }
    
}
