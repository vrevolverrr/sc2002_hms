package view.Patient.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Column;
import model.appointments.Appointment;
import model.appointments.AppointmentSlot;
import model.enums.AppointmentStatus;

public class AppointmentScheduledStatus extends Widget {
    private final AppointmentSlot appointmentSlot;
    private final String message;

    private AppointmentScheduledStatus(String message, AppointmentSlot appointmentSlot) {
        this.appointmentSlot = appointmentSlot;
        this.message = "";
    }

    public static AppointmentScheduledStatus scheduled(AppointmentSlot appointmentSlot) {
        return new AppointmentScheduledStatus("Appointment scheduled successfully.", appointmentSlot);
    }

    public static AppointmentScheduledStatus rescheduled(AppointmentSlot appointmentSlot) {
        return new AppointmentScheduledStatus("Appointment rescheduled successfully.", appointmentSlot);
    }

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
