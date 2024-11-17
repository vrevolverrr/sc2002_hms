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

public class DoctorManageAppointmentsView extends View {
    private final IAppointmentManager appointmentManager = ServiceLocator.getService(IAppointmentManager.class);
    private final Doctor doctor;

    public DoctorManageAppointmentsView(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String getViewName() {
       return "Manage Appointments";
    }

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
