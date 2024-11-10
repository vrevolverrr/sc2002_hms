package view.Patient.appointments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import view.View;

public class PatientCancelAppointmentView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private Patient patient;
    
    public PatientCancelAppointmentView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
       return "Cancel Appointment";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 300);
        
        new Align(Alignment.CENTER, new Text("[ Cancel Appointment ]", TextStyle.BOLD)).paint(context);
        new VSpacer(1).paint(context);

        List<Appointment> appointments = appointmentManager.getAppointmentsByPatientId(patient.getPatientId());
        TableRow[] appointmentRows = appointments.stream().map((Appointment appointment) -> 
            new TableRow(
                appointment.getDateTime().format(DateTimeFormatter.ofPattern("dd/MM/yy")),
                appointment.getDateTime().format(DateTimeFormatter.ofPattern("h:mma")), 
                getDoctorNameById(appointment.getDoctorId())))
            .toArray(TableRow[]::new);

        new EnumeratedTable(appointmentRows).paint(context);

        new VSpacer(1).paint(context);
        
        TextInputField timeslotField = new TextInputField("Choose an appointment to cancel");
        new TextInput(timeslotField).read(context, (input) -> validateInput(input, appointments.size()));

        // TODO cancel appointment
        new Text("Appointment cancelled successfully", TextStyle.BOLD).paint(context);

        new Pause().pause(context);
        Navigator.pop();

    }

    private String getDoctorNameById(String doctorId) {
        // TODO get doctor name
        return "John Doe";
    }
}
