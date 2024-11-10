package view.Admin;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;
import controller.AppointmentManager;

public class AdminAppointmentView extends View{
    @Override
    public String getViewName() {
        return("Admin Appointment View");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);
        new Align(Alignment.CENTER, new Text(" [ Scheduled Appointments ] ", TextStyle.BOLD)).paint(context);
        new Menu(
            new MenuOption("View All Appointments", () -> {
                System.out.println("All Appointments");
            }),
            new MenuOption("Exit this view", ()-> {
                this.exitView();;
            })
        ).readOption(context);
    }

    private void exitView(){
        Navigator.navigateTo(new AdminView());
    }
}
