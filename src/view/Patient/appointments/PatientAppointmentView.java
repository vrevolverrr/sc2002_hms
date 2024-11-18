package view.Patient.appointments;

import controller.interfaces.IAppointmentManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
import model.users.Patient;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Patient.appointments.widgets.AppointmentsTable;
import view.widgets.Title;

/**
 * View for managing a patient's appointments.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PatientAppointmentView extends View {
    /**
     * Manager for handling appointment-related operations.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The patient managing their appointments.
     */
    private final Patient patient;

    /**
     * Constructs a new view for managing a patient's appointments.
     * 
     * @param patient The patient managing their appointments.
     */
    public PatientAppointmentView(Patient patient) {
        this.patient = patient;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * 
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Manage Appointments";
    }

    /**
     * Renders the view to manage a patient's appointments.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Scheduled Appointments").paint(context);
        new AppointmentsTable(appointmentManager.getScheduledAppointments(patient)).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("View appointment requests", () -> Navigator.navigateTo(new PatientPendingAppointmentsView(patient))),
            new MenuOption("Schedule an appointment", () -> Navigator.navigateTo(new PatientScheduleAppointmentView(patient))),
            new MenuOption("Reschedule an appointment", () -> Navigator.navigateTo(new PatientRescheduleAppointmentView(patient))),
            new MenuOption("Cancel an appointment", () -> Navigator.navigateTo(new PatientCancelAppointmentView(patient))),
            new MenuOption("View Past Appointments", () -> Navigator.navigateTo(new PatientPastAppointmentsView(patient))),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
}
