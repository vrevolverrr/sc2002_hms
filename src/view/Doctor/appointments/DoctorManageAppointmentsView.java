package view.Doctor.appointments;

import java.util.List;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.VSpacer;
import model.appointments.Appointment;
import model.users.Doctor;
import services.Navigator;
import view.View;
import view.Doctor.widgets.DoctorAppointmentsTable;
import view.widgets.Title;

public class DoctorManageAppointmentsView extends View {
    private final AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);
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
        BuildContext context = new BuildContext(100, 1000);

        new Title("Manage Appointments").paint(context);
        new VSpacer(1).paint(context);
        
        new Title("Scheduled Appointments").paint(context);
        List<Appointment> appointments = appointmentManager.getScheduledAppointments(doctor);
        
        new DoctorAppointmentsTable(appointments).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Manage Appointment Requests", () -> Navigator.navigateTo(new DoctorAppointmentRequestsView(doctor))),
            new MenuOption("Update Appointment Outcome", () -> Navigator.navigateTo(new DoctorUpdateAppointmentOutcomeView(doctor))),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
    
}
