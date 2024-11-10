package view.Patient.appointments;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;

public class PatientAppointmentView extends View {
    private AppointmentManager appointmentManager = AppointmentManager.getInstance(AppointmentManager.class);

    @Override
    public String getViewName() {
        return "Manage Appointments";
    }

    private void handleSchedule() {
        Navigator.navigateTo(new PatientScheduleAppointmentView());
    }

    private void handleReschedule() {
        // System.out.println("Rescheduling your appointment...");
        // appointmentManager.reschedule();  
    }

    private void handleCancel() {
        // System.out.println("Canceling your appointment...");
        // appointmentManager.cancel(); 
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Manage Appointments ] ", TextStyle.BOLD)).paint(context);

        new Menu(
            new MenuOption("Schedule appointment", () -> this.handleSchedule()),
            new MenuOption("Reschedule appointment", () -> this.handleReschedule()),
            new MenuOption("Cancel appointment", () -> this.handleCancel())
        ).readOption(context);
    }
}
