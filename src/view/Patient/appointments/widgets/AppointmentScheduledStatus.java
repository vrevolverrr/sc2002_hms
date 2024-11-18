package view.Patient.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Column;
import model.appointments.Appointment;
import model.appointments.AppointmentSlot;
import model.enums.AppointmentStatus;

/**
 * The {@link AppointmentScheduledStatus} is a {@link Widget} to display the status of an appointment that has been scheduled.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class AppointmentScheduledStatus extends Widget {
    /**
     * The {@link AppointmentSlot} that was scheduled.
     */
    private final AppointmentSlot appointmentSlot;

    /**
     * The message to display to the user.
     */
    private final String message;

    /**
     * Constructs a new {@link AppointmentScheduledStatus} widget.
     * 
     * @param message The message to display to the user.
     * @param appointmentSlot The {@link AppointmentSlot} that was scheduled.
     */
    private AppointmentScheduledStatus(String message, AppointmentSlot appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
        this.message = message;
    }

    /**
     * Factory method to create a new {@link AppointmentScheduledStatus} widget.
     * 
     * @param appointmentSlot The {@link AppointmentSlot} that was scheduled.
     * @return The new {@link AppointmentScheduledStatus} widget.
     */
    public static AppointmentScheduledStatus scheduled(AppointmentSlot appointmentSlot) {
        return new AppointmentScheduledStatus("Appointment scheduled successfully.", appointmentSlot);
    }

    /**
     * Factory method to create a new {@link AppointmentScheduledStatus} widget.
     * 
     * @param appointmentSlot The {@link AppointmentSlot} that was rescheduled.
     * @return The new {@link AppointmentScheduledStatus} widget.
     */
    public static AppointmentScheduledStatus rescheduled(AppointmentSlot appointmentSlot) {
        return new AppointmentScheduledStatus("Appointment rescheduled successfully.", appointmentSlot);
    }

    /**
     * Factory method to create a new {@link AppointmentScheduledStatus} widget.
     * 
     * @param context The build context.
     * @return The new {@link AppointmentScheduledStatus} widget.
     */
    @Override
    public String build(BuildContext context) {
        // Temp appointment object to display the table
        final Appointment tempAppt = new Appointment(
            "0", AppointmentStatus.REQUESTED, appointmentSlot.getTimeSlot(), 
            appointmentSlot.getDoctor().getDoctorId(), "");

        return
        new Column(
            new Text(message, TextStyle.BOLD),
            new PatientAppointmentDetailsTable(tempAppt)
        ).build(context);
    }
    
}
