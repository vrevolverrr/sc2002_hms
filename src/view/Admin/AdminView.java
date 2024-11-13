package view.Admin;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.LoginView;
import view.View;
import view.Admin.appointments.AdminAppointmentView;
import view.Admin.inventory.AdminInventoryView;
import view.Admin.inventory.AdminReplenishmentRequestView;
import view.Admin.staff.AdminManageStaffView;


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

            new MenuOption("View Appoinments Details", () -> 
                 Navigator.navigateTo(new AdminAppointmentView())),

            new MenuOption("View and Manage Medication Inventory", () ->
                Navigator.navigateTo(new AdminInventoryView())),

            new MenuOption("Approve Replenishment Request", () -> 
                Navigator.navigateTo(new AdminReplenishmentRequestView())),

            new MenuOption("Log Out", () -> Navigator.navigateTo(new LoginView()))

        ).readOption(context);
    }
    
}
