package view.Patient.appointments;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.print.Doc;

import controller.AppointmentManager;
import controller.DoctorManager;
import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

public class PatientCancelAppointmentView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final DoctorManager doctorManager = DoctorManager.getInstance(DoctorManager.class);

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
        BuildContext context = new BuildContext(100, 1000);
        
        new Title("Cancel Appointment").paint(context);
        new VSpacer(1).paint(context);

        new Title("Upcoming Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getAppointments(patient);
        
        new AppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);
        
        if (appointments.isEmpty()) {
            new Pause("Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        /// Choose the appointment to cancel by index on the table, the input is automatically
        /// validated.
        TextInputField timeslotField = new TextInputField("Choose an appointment to cancel");
        new TextInput(timeslotField).read(context, "Choose an appointment to cancel.", (input) -> validateInput(input, appointments.size()));

        final Appointment selectedAppointment = appointments.get(timeslotField.getInt());

        /// Cancel the appointment
        appointmentManager.cancelAppointment(selectedAppointment);

        /// Rebuild the screen with updated table
        clear();
        new Title("Cancel Appointment").paint(context);
        new VSpacer(1).paint(context);

        new AppointmentsTable(appointmentManager.getAppointments(patient)).paint(context);

        new Text("Appointment cancelled successfully", TextStyle.BOLD).paint(context);

        new Pause().pause(context);
        Navigator.pop();

    }
}
