package view.Admin;

import controller.interfaces.IUserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.User;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Admin.appointments.AdminAppointmentView;
import view.Admin.database.AdminManageDatabaseView;
import view.Admin.inventory.AdminInventoryView;
import view.Admin.inventory.AdminReplenishmentRequestView;
import view.Admin.staff.AdminManageStaffView;
import view.Login.LoginView;
import view.widgets.Title;


/**
 * This is the main view for the Admin role.
 * It displays the admin's details and provides options to manage hospital staff, view appointments, manage medication inventory, and approve replenishment requests.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminView extends View {

    /**
     * Constructs a new {@link AdminView}.
     */
    public AdminView() {}

    /**
     * An instance of the {@link IUserManager} interface. Used to retrieve the active user.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * 
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Overview";
    }

    /**
     * Renders the view, displaying the admin's details and providing options to
     * manage hospital staff, view appointments, manage medication inventory, and
     * approve replenishment requests.
     */
    @Override
    public void render() {
        final User activeUser = userManager.getActiveUser();

        new Breadcrumbs().paint(context);
        new Title("Welcome " + (activeUser.getGender() == Gender.MALE ? "Mr. " : "Mrs.") + activeUser.getName())
                .paint(context);

        new Table(new TableRow("Admin ID", "Name", "Date of Birth", "Gender", "Age"),
                new TableRow(activeUser.getId(), activeUser.getName(), activeUser.getDobString(),
                        activeUser.getGender().toString(), String.valueOf(activeUser.getAge())))
                .paint(context);

        new VSpacer(1).paint(context);

        new Menu(new MenuOption("View and Manage Hospital Staff", () -> Navigator.navigateTo(new AdminManageStaffView())),

                new MenuOption("View Appoinments Details", () -> Navigator.navigateTo(new AdminAppointmentView())),

                new MenuOption("View and Manage Medication Inventory", () -> Navigator.navigateTo(new AdminInventoryView())),

                new MenuOption("Approve Replenishment Request", () -> Navigator.navigateTo(new AdminReplenishmentRequestView())),

                new MenuOption("Manage Database", () -> Navigator.navigateTo(new AdminManageDatabaseView())),

                new MenuOption("Log Out", () -> Navigator.navigateTo(new LoginView()))

        ).readOption(context);
    }

}
