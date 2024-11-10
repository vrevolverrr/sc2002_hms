package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import services.Navigator;
import view.LoginView;
import view.View;

public class DoctorAvailabilityMenu extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private void viewSchedule(){
        Navigator.navigateTo(new DoctorScheduleView());
    }

    private void viewDoctorPersonalSchedule(){
        Navigator.navigateTo(new DoctorScheduleView());
    }

    private void setDoctorPersonalAvailability(){
        Navigator.navigateTo(new DoctorAvailabilityView());
    }

    private void viewandUpdateDoctorUpcomingAppointments(){
        Navigator.navigateTo(new DoctorUpcomingAppointmentsView);
    }

    private void logOut(){
        Navigator.navigateTo(new LoginView());
    }

    @Override
    public String getViewName() {
        return("DoctorAvailabilityView");
    }

    @Override
    public void render() {

        BuildContext context = new BuildContext(100, 10);

        final int[] validChoice = {-1};

        // Print out the list of options
        while(validChoice[0] != 1){
            new Menu(
                new MenuOption("View Schedule", () -> {
                    this.viewSchedule();; validChoice[0] = 1;}),

                new MenuOption("Add Availability", () -> {
                    this.addAvailability();; validChoice[0] = 1;}),

                new MenuOption("Remove Availability", () -> {
                    this.removeAvailability();; validChoice[0] = 1;}),

                new MenuOption("Exit Availability Menu", () -> {
                    this.exitAvailability();; validChoice[0] = 1;}),

            ).readOption(context);

            if(validChoice[0] != 1){
                new Text("Invalid choice. Please select a valid option.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(2);
                View.clearLines(1);
            }
        }
    }

}
