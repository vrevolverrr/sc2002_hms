package view.Admin.appointments.widgets;

import java.util.List;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.appointments.Appointment;

/**
 * A {@link Widget} that displays a table of appointments.
 * This widget is designed to provide administrators with a clear view of all appointments in the system.
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminAppointmentTable extends Widget {
    /**
     * List of {@link Appointment} objects to be displayed in the table.
     */
    private final List<Appointment> appointments;

    /**
     * An instance of {@link UserManager} used to fetch user details by ID.
     */
    private final UserManager userManager = UserManager.getInstance(UserManager.class);

    /**
     * Constructs an {@code AdminAppointmentTable} with the given list of appointments.
     *
     * @param appointments a {@link List} of {@link Appointment} objects to be displayed.
     */
    public AdminAppointmentTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Builds the table representation of appointments.
     * <p>
     * The table consists of the following columns:
     *  <ul>
     * <li>Patient ID</li>
     * <li>Doctor ID</li>
     * <li>Date</li>
     * <li>Time</li>
     * <li>Doctor</li>
     * <li>Patient</li>
     * <li>Status</li>
     * </ul>
     * </p>
     * If no appointments are available, a single row indicating "No appointments found" is displayed.
     * Otherwise, the table is constructed with a header and rows representing each appointment.
     *
     * @param context the {@link BuildContext} for rendering the table.
     * @return the table as a {@link String}.
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Patient ID", "Doctor ID", "Date", "Time", "Doctor", "Patient", "Status");

        if (appointments.isEmpty()) {
            return EnumeratedTable.headerless(new TableRow("No appointments found")).build(context);
        }

        TableRow[] appointmentRows = appointments.stream().map((appointment) -> 
            new TableRow(
                appointment.getPatientId(),
                appointment.getDoctorId(),
                appointment.getTimeSlot().getFormattedDate(),
                appointment.getTimeSlot().getFormattedTime(),
                getNameById(appointment.getDoctorId()),
                getNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, appointmentRows).build(context);
    }
    
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
