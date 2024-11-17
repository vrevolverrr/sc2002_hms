package view.Doctor.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Patient;
import view.Patient.PatientView;

/**
 * A {@link Widget} that displays the details of a patient.
 * This widget is designed to provide doctors with a clear view of the details of a specific patient.
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class PatientDetailsTable extends Widget {

     /**
     * The {@link Patient} object whose details will be displayed in the table.
     */
    private final Patient patient;

    /**
     * Constructs a {@code PatientDetailsTable} for the specified {@link Patient}.
     *
     * @param patient the {@link Patient} object containing the details to be displayed.
     */
    public PatientDetailsTable(Patient patient) {
        this.patient = patient;
    }

    /**
     * Builds the UI for displaying the patient's details in a table format. 
     * The table contains the following information:
     * <ul>
     *   <li>Patient's Name</li>
     *   <li>Date of Birth</li>
     *   <li>Gender</li>
     *   <li>Age</li>
     *   <li>Blood Type</li>
     * </ul>
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Name", "Date of Birth", "Gender", "Age", "Blood Type"),
            new TableRow(patient.getName(), patient.getDobString(), patient.getGender().toString(), 
                String.valueOf(patient.getAge()), patient.getBloodType().toString())
        ).build(context);
    }
}
