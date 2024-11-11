package view.Admin.staff;

import java.util.List;

import model.users.User;
import controller.StaffManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import view.View;
import view.Admin.staff.widget.StaffTable;
import view.widgets.Title;

public class AdminAllStaffView extends View {
    private final StaffManager staffManager = StaffManager.getInstance(StaffManager.class);
    
    private String searchKeywords = "";
    private final BuildContext context = BuildContext.unboundedVertical(130);

    @Override
    public String getViewName() {
        return "Search Staff";
    }

    @Override
    public void render() {
        List<User> staffs;

        if (searchKeywords.isEmpty()) {
            staffs = staffManager.getAllStaff();
        } else {
            staffs = staffManager.findStaffByKeywords(searchKeywords);
        }

        new Title("Search Staff").paint(context);
        new StaffTable(staffs).paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search for staff");
        new TextInput(searchField).read(context, (input) -> true);
        
        searchKeywords = searchField.getValue();
        clear();
        render();
    }
}
