package view.Doctor.appointments.widgets;

import java.util.List;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.appointments.Appointment;

public class PastAppointmentsTable extends Widget {
    private final List<Appointment> appointments;
    private final UserManager userManager = UserManager.getInstance(UserManager.class);

    public PastAppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Date", "Time", "Patient", "Status");

        List<TableRow> rows = appointments.stream()
            .map(appointment -> new TableRow(
                appointment.getDateTime().getFormattedDate(),
                appointment.getDateTime().getFormattedTime(), 
                getNameById(appointment.getPatientId()), 
                appointment.getStatus().toString()))
            .toList();

        return EnumeratedTable.withHeader(header, rows.toArray(TableRow[]::new)).build(context);
    }

    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
