package view.Patient.appointments.widgets;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;
import services.ServiceLocator;

public class PatientAppointmentDetailsTable extends Widget {
    private final Appointment appointment;
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    public PatientAppointmentDetailsTable(Appointment appointment) {
        this.appointment = appointment;
    }

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

    private String getDoctorNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
