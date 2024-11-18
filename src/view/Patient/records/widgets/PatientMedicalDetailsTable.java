package view.Patient.records.widgets;


import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Patient;

/**
 * Widget for displaying a table of a patient's medical details.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */

public class PatientMedicalDetailsTable extends Widget {
    /**
     * The {@link Patient} whose medical details are being displayed.
     */
    private final Patient patient;

    /**
     * Constructs a new {@link PatientMedicalDetailsTable} widget.
     * 
     * @param patient The {@link Patient} whose medical details are being displayed.
     */
    public PatientMedicalDetailsTable(Patient patient) {
        this.patient = patient;
    }

    /**
     * Builds the widget to display the table of medical details.
     * 
     * @param context The {@link BuildContext} to build the widget in.
     * @return The built widget.
     */
    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Name", patient.getName()),
            new TableRow("Patient ID", patient.getId()),
            new TableRow("Age", String.valueOf(patient.getAge())),
            new TableRow("Gender", patient.getGender().toString()),
            new TableRow("Date of Birth", patient.getDobString()),
            new TableRow("Contact", patient.getPhoneNumber()),
            new TableRow("Weight (kg)", String.valueOf(patient.getWeight())),
            new TableRow("Height (cm)", String.valueOf(patient.getHeight())),
            new TableRow("Blood Type", patient.getBloodType().toString())
        ).build(context);
    }
    
}
