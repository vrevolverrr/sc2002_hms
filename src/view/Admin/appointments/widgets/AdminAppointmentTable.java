package view.Admin.appointments.widgets;

import java.util.List;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.appointments.Appointment;
import services.ServiceLocator;

/**
 * A {@link Widget} that displays a table of appointments.
 * This widget is designed to provide administrators with a clear view of all appointments in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminAppointmentTable extends Widget {
    /**
     * List of {@link Appointment} objects to be displayed in the table.
     */
    private final List<Appointment> appointments;

    /**
     * An instance of user manager used to fetch user details by ID.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

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
    
    /**
     * Retrieves the name of a user by their ID.
     *
     * @param id the ID of the user.
     * @return the name of the user with the specified ID.
     */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
