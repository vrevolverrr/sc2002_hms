package view.Doctor.appointments.widgets;

import java.util.List;
import controller.interfaces.IPatientManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;
import services.ServiceLocator;

/**
 * A {@link Widget} that displays a table of appointments.
 * This widget is designed to provide doctors with a clear view of all appointments in the system.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class DoctorAppointmentsTable extends Widget {
    /**
     * A list of {@link Appointment} objects representing the appointments to be displayed.
     */
    private final List<Appointment> appointments;

    /**
     * The {@link PatientManager} instance used to retrieve patient details, such as patient names by their IDs.
     */
    private final IPatientManager patientManager = ServiceLocator.getService(IPatientManager.class);

    /**
     * A custom message to be displayed when there are no appointments.
     */
    private final String emptyMessage;

    /**
     * Constructs a {@code DoctorAppointmentsTable} with the given list of appointments and a default empty message.
     *
     * @param appointments a list of {@link Appointment} objects that represent the appointments to be displayed.
     */
    public DoctorAppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
        this.emptyMessage = "No upcoming appointments";
    }

    /**
     * Constructs a {@code DoctorAppointmentsTable} with the given list of appointments and a custom empty message.
     *
     * @param emptyMessage a {@link String} containing the custom message to be displayed when there are no appointments.
     * @param appointments a list of {@link Appointment} objects that represent the appointments to be displayed.
     */
    public DoctorAppointmentsTable(String emptyMessage, List<Appointment> appointments) {
        this.appointments = appointments;
        this.emptyMessage = emptyMessage;
    }

    /**
     * Builds the UI for displaying the doctor's appointments in a table format. The table contains:
     * <ul>
     *   <li>Date of the appointment</li>
     *   <li>Time of the appointment</li>
     *   <li>Patient name (retrieved using the patient's ID)</li>
     *   <li>Status of the appointment</li>
     * </ul>
     * If there are no appointments, a message is displayed instead of the table.
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
     */
    @Override
    public String build(BuildContext context) {
        if (appointments.isEmpty()) {
            return new Table(new TableRow(emptyMessage)).build(context);
        }

        TableRow header = new TableRow("Date", "Time", "Patient", "Status");

        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
            new TableRow(
                appointment.getTimeSlot().getFormattedDate(),
                appointment.getTimeSlot().getFormattedTime(), 
                getPatientNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, appointmentRows).build(context);
    }
     
    /**
     * Retrieves the name of the patient based on the given patient ID.
     *
     * @param patientId the ID of the patient whose name is to be retrieved.
     * @return the name of the patient as a {@link String}.
     */
    private String getPatientNameById(String patientId) {
        return patientManager.getPatient(patientId).getName();
    }
}   

