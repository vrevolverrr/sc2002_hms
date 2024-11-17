package view.Doctor.records.widget;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.users.Patient;

/**
 * The {@link DoctorPatientCareTable} class is a {@link Widget} that generates a table of patients
 * under a doctor's care. The table displays the patient's name, age, weight, height, and blood type.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorPatientCareTable extends Widget {
    /**
     * A {@link List} of {@link Patient} representing the patients under the doctor's care.
     */
    private final List<Patient> patientsUnderCare;

    /**
     * Constructs a new {@link DoctorPatientCareTable} with the given list of patients under the doctor's care.
     * 
     * @param patientsUnderCare a {@link List} of {@link Patient} representing the patients under the doctor's care.
     */
    public DoctorPatientCareTable(List<Patient> patientsUnderCare) {
        this.patientsUnderCare = patientsUnderCare;
    }

    /**
     * Builds the UI for displaying the doctor's patients under care in a table format. The table contains:
     * <ul>
     *   <li>Patient name</li>
     *   <li>Age</li>
     *   <li>Weight (kg)</li>
     *   <li>Height (cm)</li>
     *   <li>Blood type</li>
     * </ul>
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
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
