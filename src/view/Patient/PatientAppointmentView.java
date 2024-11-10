package view.Patient;

import controller.AppointmentManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import view.View;

public class PatientAppointmentView extends View {

    @Override
    public String getViewName() {
        return "Manage Appointments";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ Manage Appointments ] ", TextStyle.BOLD)).paint(context);

        System.out.println("Choose the action you want to take:");

        new Menu(
            new MenuOption("Schedule appointment", () -> this.schedule()),
            new MenuOption("Reschedule appointment", () -> this.reschedule()),
            new MenuOption("Cancel appointment", () -> this.cancel())
        ).readOption(context);
    }

    private void schedule() {
        System.out.println("Scheduling a new appointment...");
        AppointmentManager appointmentManager = AppointmentManager.getInstance(); 
    }

    private void reschedule() {
        System.out.println("Rescheduling your appointment...");
        AppointmentManager appointmentManager = AppointmentManager.getInstance();  
        appointmentManager.reschedule();  
    }

    private void cancel() {
        System.out.println("Canceling your appointment...");
        AppointmentManager appointmentManager = AppointmentManager.getInstance();  
        appointmentManager.cancel(); 
    }
}
