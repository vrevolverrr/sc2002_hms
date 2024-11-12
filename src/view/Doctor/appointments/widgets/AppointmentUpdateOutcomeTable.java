package view.Doctor.appointments.widgets;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import model.enums.MedicalService;
import model.prescriptions.Prescription;

public class AppointmentUpdateOutcomeTable extends Widget {
    private final String consultationNotes;
    private final List<Prescription> prescriptions;
    private final List<MedicalService> services;
    
    public AppointmentUpdateOutcomeTable(String consultationNotes, List<Prescription> prescriptions, List<MedicalService> services) {
        this.consultationNotes = consultationNotes;
        this.prescriptions = prescriptions;
        this.services = services;
    }

    @Override
    public String build(BuildContext context) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'build'");
    }
    
}
