package view.Doctor.widgets;

import java.util.List;
import java.util.stream.Stream;

import controller.PatientManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.appointments.Appointment;

public class DoctorAppointmentsTable extends Widget {
    private final List<Appointment> appointments;
    private final PatientManager patientManager = PatientManager.getInstance(PatientManager.class);

    public DoctorAppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public String build(BuildContext context) {
        if (appointments.isEmpty()) {
            return new Table(new TableRow("No upcoming appointments")).build(context);
        }

        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
            new TableRow(
                appointment.getDateTime().getFormattedDate(),
                appointment.getDateTime().getFormattedTime(), 
                getPatientNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.headerless(appointmentRows).build(context);
    }

    private String getPatientNameById(String patientId) {
        return patientManager.getPatientById(patientId).getName();
    }
}   

