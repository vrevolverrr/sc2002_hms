package view.Patient.appointments;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
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

    @Override
    public void render() {
        new Title("Manage Appointments").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Scheduled Appointments").paint(context);
        new AppointmentsTable(appointmentManager.getScheduledAppointments(patient)).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("View appointment requests", () -> Navigator.navigateTo(new PatientPendingAppointmentsView(patient))),
            new MenuOption("Schedule an appointment", () -> Navigator.navigateTo(new PatientScheduleAppointmentView(patient))),
            new MenuOption("Reschedule an appointment", () -> Navigator.navigateTo(new PatientRescheduleAppointment(patient))),
            new MenuOption("Cancel an appointment", () -> Navigator.navigateTo(new PatientCancelAppointmentView(patient))),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
}
