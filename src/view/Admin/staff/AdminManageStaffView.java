package view.Admin.staff;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.View;
import view.widgets.Title;

/**
 * This view allows the admin to manage hospital staff.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminManageStaffView extends View {
    /**
     * An instance of the {@link UserManager} class. Used to manage user operations.
     */
    UserManager userManager = UserManager.getInstance(UserManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Manage Hospital Staff";
    }

    /**
     * Renders the view, providing options to view, add, and update staff.
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
