package view.Patient.appointments.widgets;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.users.Doctor;

/**
 * The {@link DoctorsSelectionTable} is a {@link Widget} to display a table of doctors for selection.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorsSelectionTable extends Widget {
    /**
     * The {@link List} of {@link Doctor} to display.
     */
    private List<Doctor> doctors;

    /**
     * Constructs a new {@link DoctorsSelectionTable} widget.
     * 
     * @param doctors The {@link List} of {@link Doctor} to display.
     */
    public DoctorsSelectionTable(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    /**
     * Builds the UI representation of the doctors selection table.
     * <p>
     * If no doctors are found, the table displays a single row stating "No doctors found."
     * Otherwise, the table includes one row for each doctor, displaying the details of the doctor.
     * </p>
     * 
     * @param context The build context in which the UI elements are rendered.
     * @return The UI representation of the doctors selection table.
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Name", "Specialisation", "Gender");

        TableRow[] doctorRows = doctors.stream().map((doctor) -> new TableRow(
           doctor.getName(), doctor.getSpecialisation().toString(), doctor.getGender().toString())).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, doctorRows).build(context);
    }
    
}
