package view.Admin;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.Admin;
import services.Navigator;
import view.LoginView;
import view.View;


public class AdminView extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private void viewAndManageStaff(){
        Navigator.navigateTo(new AdminHospitalStaffView());
    }

    private void viewAppointmentsDetails(){
        Navigator.navigateTo(new AdminAppointmentView());
    }

    private void viewandManageInventory(){
        Navigator.navigateTo(new AdminInventoryView());
    }

    private void approveReplenishRequest(){
        Navigator.navigateTo(new AdminReplenishmentRequestView());
    }

    private void logOut(){
        Navigator.navigateTo(new LoginView());
    }

    @Override
    public String getViewName() {
        return("AdminView");
    }

    @Override
    public void render() {

        BuildContext context = new BuildContext(100, 10);

        final int[] validChoice = {-1};
        
        while(validChoice[0] != 1){
            new Menu(
                new MenuOption("View and Manage Hospital Staff", () -> {
                    this.viewAndManageStaff();; validChoice[0] = 1;}),

                new MenuOption("View Appoinments Details", () -> {
                    this.viewAppointmentsDetails();; validChoice[0] = 1;}),

                new MenuOption("View and Manage Medication Inventory", () -> {
                    this.viewandManageInventory();; validChoice[0] = 1;}),

                new MenuOption("Approve Replenishment Request", () -> {
                    this.approveReplenishRequest();; validChoice[0] = 1;}),

                new MenuOption("Log Out", () -> {
                    this.logOut();; validChoice[0] = 1;})

            ).readOption(context);

            if(validChoice[0] != 1){
                new Text("Invalid choice. Please select a valid option.", TextStyle.BOLD).paint(context);
                View.gotoPrevNthLine(2);
                View.clearLines(1);
            }
        }
    }
    
}
