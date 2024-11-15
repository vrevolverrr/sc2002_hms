package view.Patient.records.widgets;


import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Patient;

public class PatientMedicalDetailsTable extends Widget {
    private final Patient patient;

    public PatientMedicalDetailsTable(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Name", patient.getName()),
            new TableRow("Age", String.valueOf(patient.getAge())),
            new TableRow("Gender", patient.getGender().toString()),
            new TableRow("Date of Birth", patient.getDobString()),
            new TableRow("Weight (kg)", String.valueOf(patient.getWeight())),
            new TableRow("Height (cm)", String.valueOf(patient.getHeight())),
            new TableRow("Blood Type", patient.getBloodType().toString())
        ).build(context);
    }
    
}
