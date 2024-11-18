package view.Doctor.appointments.widgets;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;
import services.ServiceLocator;

/**
 * A {@link Widget} that displays the details of a doctor's appointment.
 * This widget is designed to provide doctors with a clear view of the details of a specific appointment.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */

public class DoctorAppointmentDetailsTable extends Widget {
    /**
     * The {@link Appointment} object representing the specific appointment details for which the table will be created.
     */
    private final Appointment appointment;

    /**
     * The {@link UserManager} instance used to retrieve user details, such as patient name by their ID.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * Constructs a {@code DoctorAppointmentDetailsTable} to display the details of the given appointment.
     *
     * @param appointment the {@link Appointment} object that contains the appointment details to be displayed.
     */
    public DoctorAppointmentDetailsTable(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Builds the UI for displaying the appointment details in a table format. The table contains:
     * <ul>
     *   <li>Date of the appointment</li>
     *   <li>Time of the appointment</li>
     *   <li>Patient name (retrieved using the patient's ID)</li>
     *   <li>Status of the appointment</li>
     * </ul>
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Date", "Time", "Patient", "Status"),
            new TableRow(
                appointment.getTimeSlot().getFormattedDate(),
                appointment.getTimeSlot().getFormattedTime(),
                getPatientNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )
        ).build(context);
    }

    /**
     * Retrieves the name of the patient based on the given patient ID.
     *
     * @param id the ID of the patient whose name is to be retrieved.
     * @return the name of the patient as a {@link String}.
     */
    private String getPatientNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
