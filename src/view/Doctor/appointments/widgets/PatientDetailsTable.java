package view.Doctor.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Patient;

public class PatientDetailsTable extends Widget {
    private final Patient patient;

    public PatientDetailsTable(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Name", "Date of Birth", "Gender", "Age", "Blood Type"),
            new TableRow(patient.getName(), patient.getDobString(), patient.getGender().toString(), 
                String.valueOf(patient.getAge()), patient.getBloodType().toString())
        ).build(context);
    }
}
