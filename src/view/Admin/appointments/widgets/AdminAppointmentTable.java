package view.Admin.appointments.widgets;

import java.util.List;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.appointments.Appointment;
import services.ServiceLocator;

public class AdminAppointmentTable extends Widget {
    private final List<Appointment> appointments;
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    public AdminAppointmentTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

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
