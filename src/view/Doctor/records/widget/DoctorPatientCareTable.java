package view.Doctor.records.widget;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.users.Patient;

public class DoctorPatientCareTable extends Widget {
    private final List<Patient> patientsUnderCare;

    public DoctorPatientCareTable(List<Patient> patientsUnderCare) {
        this.patientsUnderCare = patientsUnderCare;
    }

    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Patient Name", "Age", "Weight (kg)", "Height (cm)", "Blood Type");

        if (patientsUnderCare.isEmpty()) {
            return new Table(new TableRow("No patients under your care.")).build(context);
        }

        TableRow[] patientRows = patientsUnderCare.stream().map((patient) -> 
            new TableRow(
                patient.getName(),
                String.valueOf(patient.getAge()),
                String.valueOf(patient.getWeight()),
                String.valueOf(patient.getHeight()),
                patient.getBloodType().toString()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, patientRows).build(context);
    }
    
}
