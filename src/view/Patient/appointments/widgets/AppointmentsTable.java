package view.Patient.appointments.widgets;

import java.util.List;

import controller.interfaces.IDoctorManager;
import model.appointments.Appointment;
import services.ServiceLocator;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;

public class AppointmentsTable extends Widget {
    private final IDoctorManager doctorManager = ServiceLocator.getService(IDoctorManager.class);
    private final List<Appointment> appointments;
    
    public AppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

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

    private String getDoctorNameById(String doctorId) {
        return doctorManager.getDoctor(doctorId).getName();
    }
}
