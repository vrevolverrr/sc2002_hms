package view.Patient.appointments.widgets;

import java.util.List;

import controller.DoctorManager;
import model.appointments.Appointment;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;

/**
 * Widget to display a table of appointments for a patient.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class AppointmentsTable extends Widget {
    /**
     * The {@link DoctorManager} to retrieve doctor information.
     */
    private final DoctorManager doctorManager = DoctorManager.getInstance(DoctorManager.class);

    /**
     * The {@link List} of {@link Appointment} to display.
     */
    private final List<Appointment> appointments;
    
    /**
     * Constructs a new {@link AppointmentsTable} widget.
     * @param appointments The {@link List} of {@link Appointment} to display.
     */
    public AppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Builds the UI representation of the appointments table.
     * <p>
     * If no appointments are found, the table displays a single row stating "No appointments found."
     * Otherwise, the table includes one row for each appointment, displaying the details of the appointment.
     * </p>
     * 
     * @param context The build context in which the UI elements are rendered.
     * @return The UI representation of the {@link AppointmentTable}.
     */
    @Override
    public String build(BuildContext context) {
        if (appointments.isEmpty()) {
            return new Table(new TableRow("No appointments found")).build(context);
        }

        TableRow header = new TableRow("Date", "Time", "Doctor", "Status");

        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
        new TableRow(
            appointment.getTimeSlot().getFormattedDate(),
            appointment.getTimeSlot().getFormattedTime(), 
            getDoctorNameById(appointment.getDoctorId()),
            appointment.getStatus().toString()))
        .toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, appointmentRows).build(context);
    }

    /**
     * Retrieves the name of the doctor with the given ID.
     * @param doctorId The ID of the doctor.
     * @return The name of the doctor.
     */
    private String getDoctorNameById(String doctorId) {
        return doctorManager.getDoctorById(doctorId).getName();
    }
}
