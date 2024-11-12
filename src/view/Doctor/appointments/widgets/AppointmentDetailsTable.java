package view.Doctor.appointments.widgets;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;

public class AppointmentDetailsTable extends Widget {
    private final Appointment appointment;
    private final UserManager userManager = UserManager.getInstance(UserManager.class);

    public AppointmentDetailsTable(Appointment appointment) {
        this.appointment = appointment;
    }

    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Date", "Time", "Patient", "Status"),
            new TableRow(
                appointment.getDateTime().getFormattedDate(),
                appointment.getDateTime().getFormattedTime(),
                getPatientNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )
        ).build(context);
    }

    private String getPatientNameById(String id) {
        return userManager.getUser(id).getName();
    }
}