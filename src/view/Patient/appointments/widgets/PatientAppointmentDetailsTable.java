package view.Patient.appointments.widgets;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;

/**
 * Displays the details of a patient's appointment in a table format.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class PatientAppointmentDetailsTable extends Widget {
    /**
     * The {@link Appointment} to display.
     */
    private final Appointment appointment;

    /**
     * The {@link UserManager} to retrieve user information.
     */
    private final UserManager userManager = UserManager.getInstance(UserManager.class);

    /**
     * Constructs a new {@link PatientAppointmentDetailsTable} widget.
     * 
     * @param appointment The {@link Appointment} to display.
     */
    public PatientAppointmentDetailsTable(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Builds the UI representation of the appointment details table.
     * 
     * @param context The build context in which the UI elements are rendered.
     * @return The UI representation of the appointment details table.
     */
    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Date", "Time", "Doctor", "Status"),
            new TableRow(
                appointment.getTimeSlot().getFormattedDate(),
                appointment.getTimeSlot().getFormattedTime(),
                getDoctorNameById(appointment.getDoctorId()),
                appointment.getStatus().toString()
            )
        ).build(context);
    }

    /**
     * Retrieves the name of the doctor with the specified ID.
     * 
     * @param id The ID of the doctor.
     * @return The name of the doctor.
     */
    private String getDoctorNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
