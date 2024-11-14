package view.Admin.staff;

import java.util.List;

import model.users.User;
import controller.StaffManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import view.View;
import view.Admin.staff.widget.StaffTable;
import view.widgets.Title;

public class AdminAllStaffView extends View {
    private final StaffManager staffManager = StaffManager.getInstance(StaffManager.class);
    
    private String searchKeywords = "";
    private final BuildContext context = BuildContext.unboundedVertical(120);

    @Override
    public String getViewName() {
        return "Search Staff";
    }

    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Search Staff").paint(context);

        List<User> filteredStaffs = filterByKeyword(searchKeywords);
        new StaffTable(filteredStaffs).paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search for staff");
        new TextInput(searchField).read(context, input -> true);
        
        searchKeywords = searchField.getValue();
        clear();
        render();
    }
    
    private List<User> filterByKeyword(String keyword) {
        if (keyword.isBlank()) {
            return staffManager.getAllStaff();
        }

        return staffManager.findStaffByKeywords(keyword);
    }
}
