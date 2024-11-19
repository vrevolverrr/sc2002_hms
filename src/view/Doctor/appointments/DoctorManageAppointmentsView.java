package view.Doctor.appointments;

import java.util.List;

import controller.interfaces.IAppointmentManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Doctor.appointments.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

/**
 * {@link DoctorManageAppointmentsView} is a {@link View} that allows doctors to manage
 * their scheduled appointments. 
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorManageAppointmentsView extends View {
    /**
     * An instance of appointment manager used to manage appointments.
     */
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);

    /**
     * The {@link Doctor} for whom the view is managing appointments.
     */
    private final Doctor doctor;

    /**
     * Constructs a new {@link DoctorManageAppointmentsView} for a given doctor.
     *
     * @param doctor the {@link Doctor} whose appointments are being managed.
     */
    public DoctorManageAppointmentsView(Doctor doctor) {
        this.doctor = doctor;
    }

    /**
     * Returns the name of the view.
     *
     * @return a {@link String} representing the view name, "Manage Appointments".
     */
    @Override
    public String getViewName() {
       return "Manage Appointments";
    }

    /**
     * Renders the "Manage Appointments" view for the doctor.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Appointments").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Scheduled Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getScheduledAppointments(doctor);
        
        new DoctorAppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Manage Appointment Requests", () -> Navigator.navigateTo(new DoctorAppointmentRequestsView(doctor))),
            new MenuOption("Record Appointment Outcome", () -> Navigator.navigateTo(new DoctorRecordAppointmentOutcomeView(doctor))),
            new MenuOption("View Past Appointments", () -> Navigator.navigateTo(new DoctorViewPastAppointmentsView(doctor))),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
    
}
