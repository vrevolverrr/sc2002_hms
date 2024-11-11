package view.Patient.appointments.widgets;

import java.util.List;

import controller.DoctorManager;
import model.appointments.Appointment;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;

public class AppointmentsTable extends Widget {
    private final DoctorManager doctorManager = DoctorManager.getInstance(DoctorManager.class);
    private final List<Appointment> appointments;
    
    public AppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String build(BuildContext context) {
        if (appointments.isEmpty()) {
            return new Table(new TableRow("No appointments found")).build(context);
        }

        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
        new TableRow(
            appointment.getDateTime().getFormattedDate(),
            appointment.getDateTime().getFormattedTime(), 
            getDoctorNameById(appointment.getDoctorId())))
        .toArray(TableRow[]::new);

        return new EnumeratedTable(appointmentRows).build(context);
    }

    private String getDoctorNameById(String doctorId) {
        return doctorManager.getDoctorById(doctorId).getName();
    }
}
