package view.Doctor.appointments.widgets;

import java.util.List;
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

    private final String emptyMessage;

    public DoctorAppointmentsTable(List<Appointment> appointments) {
        this.appointments = appointments;
        this.emptyMessage = "No upcoming appointments";
    }

    public DoctorAppointmentsTable(String emptyMessage, List<Appointment> appointments) {
        this.appointments = appointments;
        this.emptyMessage = emptyMessage;
    }

    @Override
    public String build(BuildContext context) {
        if (appointments.isEmpty()) {
            return new Table(new TableRow(emptyMessage)).build(context);
        }

        TableRow header = new TableRow("Date", "Time", "Patient", "Status");

        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
            new TableRow(
                appointment.getTimeSlot().getFormattedDate(),
                appointment.getTimeSlot().getFormattedTime(), 
                getPatientNameById(appointment.getPatientId()),
                appointment.getStatus().toString()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, appointmentRows).build(context);
    }

    private String getPatientNameById(String patientId) {
        return patientManager.getPatient(patientId).getName();
    }
}   

