package view.Admin;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.User;
import services.Navigator;
import view.View;
import view.Admin.appointments.AdminAppointmentView;
import view.Admin.inventory.AdminInventoryView;
import view.Admin.inventory.AdminReplenishmentRequestView;
import view.Admin.staff.AdminManageStaffView;
import view.Login.LoginView;
import view.widgets.Title;


public class AdminView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final User activeUser = userManager.getActiveUser();

    @Override
    public String getViewName() {
        return("Overview");
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Welcome " + (activeUser.getGender() == Gender.MALE ? "Mr. " : "Mrs.") + activeUser.getName()).paint(context);
        
        new Table(
            new TableRow("Admin ID", "Name", "Date of Birth", "Gender", "Age"),
            new TableRow(activeUser.getId(), activeUser.getName(), activeUser.getDobString(), 
            activeUser.getGender().toString(), String.valueOf(activeUser.getAge()))
        ).paint(context);

        new VSpacer(1).paint(context);

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
