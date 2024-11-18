package view.Admin.staff;

import controller.interfaces.IUserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.widgets.Title;

/**
 * The class provides an interface for managing hospital staff. 
 * Administrators can view, add, update, or navigate back from the staff management options.
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class AdminManageStaffView extends View{

    /**
     * Constructs a new {@link AdminManageStaffView}.
     */
    public AdminManageStaffView() {}
    
    /**
     * An instance of the {@link IUserManager} interface. Used to manage user operations.
     */
    IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * Returns the name of this view.
     * 
     * @return the view name as "Manage Hospital Staff".
     */
    @Override
    public String getViewName() {
        return "Manage Hospital Staff";
    }

    /**
     * Renders the user interface for managing hospital staff.
     * <p>
     * This method displays a menu with options to view all staff, add new staff, update existing staff,
     * or go back to the previous menu. Each option navigates to its respective view using {@link Navigator}.
     * </p>
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Hospital Staff").paint(context);

        new Menu(
            new MenuOption("View All Staff", () -> Navigator.navigateTo(new AdminAllStaffView())),
            new MenuOption("Add New Staff", () -> Navigator.navigateTo(new AdminAddStaffView())),
            new MenuOption("Update Existing Staff", () -> Navigator.navigateTo(new AdminUpdateStaffView())),
            // new MenuOption("Remove Staff", () -> this.promptRemoveStaff()),
            new MenuOption("Back", () -> Navigator.pop())
        ).readOption(context);
    }
}
