package view.Patient.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Doctor;

/**
 * Widget to display a table of selected doctor for appointment.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class SelectedDoctorTable extends Widget {
    /**
     * The {@link Doctor} to display.
     */
    private final Doctor doctor;

    /**
     * Constructs a new {@link SelectedDoctorTable} widget.
     * 
     * @param doctor The {@link Doctor} to display.
     */
    public SelectedDoctorTable(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Builds the UI representation of the selected doctor table.
     * 
     * @param context The build context in which the UI elements are rendered.
     * @return The UI representation of the selected doctor table.
     */
    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Doctor Name", "Specialisation"),
            new TableRow("Dr. " + doctor.getName(), doctor.getSpecialisation().toString())
        ).build(context);
    }
    
}
