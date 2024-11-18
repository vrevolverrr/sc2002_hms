package view.Admin.staff;

import java.util.List;

import controller.interfaces.IStaffManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.users.User;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Admin.staff.widget.StaffTable;
import view.widgets.Title;

/**
 * This view allows the admin to search for and update staff details.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminUpdateStaffView extends View {

    /**
     * Default constructor for the {@link AdminUpdateStaffView} class.
     */
    public AdminUpdateStaffView() {};
    
    /**
     * An instance of the {@link IStaffManager} interface. Used to manage staff operations.
     */
    private IStaffManager staffManager = ServiceLocator.getService(IStaffManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Update Staff";
    }

    /**
     * Renders the view, allowing the admin to search for staff and update their details.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Find Staff to Update").paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search for staff");
        new TextInput(searchField).read(context, "Enter a query to search.", input -> !input.trim().isEmpty());

        List<User> staffs = staffManager.findStaffByKeywords(searchField.getValue());
        new StaffTable(staffs).paint(context);

        new VSpacer(1).paint(context);

        if (staffs.isEmpty()) {
            new Pause("Press any key to search again.").pause(context);
            clear();
            render();
            return;
        }

        TextInputField selectField = new TextInputField(String.format("Select a staff to modify (1-%d)", staffs.size()));
        new TextInput(selectField).read(context, "Select from the list above.", 
            input -> InputValidators.validateRange(input, staffs.size()));

        User staff = staffs.get(selectField.getOption());

        new Menu(
            new MenuOption("Update Staff Details", () -> Navigator.navigateTo(new AdminUpdateStaffDetailsView(staff))),    
            new MenuOption("Delete Staff", () -> deleteStaff(staff))
        ).readOption(context);
    }

    /**
     * Deletes the specified staff member.
     * @param staff the staff member to delete.
     */
    private void deleteStaff(User staff) {
        staffManager.deleteStaff(staff);

        new Pause("Staff has been deleted. Press any key to continue.").pause(context);
        Navigator.pop();
    }
}
