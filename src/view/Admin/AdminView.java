package view.Admin;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.LoginView;
import view.View;


public class AdminView extends View {
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return("Overview");
    }

    @Override
    public void render() {
        new Menu(
            new MenuOption("View and Manage Hospital Staff", () -> 
                Navigator.navigateTo(new AdminManageStaffView())),

            // new MenuOption("View Appoinments Details", () -> 
            //     this.viewAppointmentsDetails()),

            // new MenuOption("View and Manage Medication Inventory", () ->
            //     this.viewandManageInventory()),

            // new MenuOption("Approve Replenishment Request", () -> 
            //     this.approveReplenishRequest()),

            new MenuOption("Log Out", () -> Navigator.navigateTo(new LoginView()))

        ).readOption(context);
    }
    
}
