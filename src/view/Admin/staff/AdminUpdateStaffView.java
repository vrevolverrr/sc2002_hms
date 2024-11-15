package view.Admin.staff;

import java.util.List;

import controller.StaffManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.users.User;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Admin.staff.widget.StaffTable;
import view.widgets.Title;

public class AdminUpdateStaffView extends View {
    private StaffManager staffManager = StaffManager.getInstance(StaffManager.class);

    @Override
    public String getViewName() {
        return "Update Staff";
    }

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

    private void deleteStaff(User staff) {
        staffManager.deleteStaff(staff);

        new Pause("Staff has been deleted. Press any key to continue.").pause(context);
        Navigator.pop();
    }
}
