package view.Patient.appointments;

import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

public class PatientAppointmentView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
    private final Patient patient;

    public PatientAppointmentView(Patient patient) {
        this.patient = patient;
    }

    @Override
    public String getViewName() {
        return "Manage Appointments";
    }

    private void handleSchedule() {
        Navigator.navigateTo(new PatientScheduleAppointmentView(patient));
    }

    private void handleReschedule() {
        Navigator.navigateTo(new PatientRescheduleAppointment(patient));
    }

    private void handleCancel() {
        Navigator.navigateTo(new PatientCancelAppointmentView(patient));
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Title("Manage Appointments").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Upcoming Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getAppointments(patient);
        
        new AppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Schedule appointment", () -> this.handleSchedule()),
            new MenuOption("Reschedule appointment", () -> this.handleReschedule()),
            new MenuOption("Cancel appointment", () -> this.handleCancel()),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
}
