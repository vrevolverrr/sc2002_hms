package view.Admin.staff;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.View;
import view.widgets.Title;
    
public class AdminManageStaffView extends View{
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return "Manage Hospital Staff";
    }

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
