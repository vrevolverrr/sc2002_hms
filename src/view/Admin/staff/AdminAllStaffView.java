package view.Admin.staff;

import java.util.List;

import model.users.User;
import controller.interfaces.IStaffManager;
import services.ServiceLocator;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import view.View;
import view.Admin.staff.widget.StaffTable;
import view.widgets.Title;

/**
 * This view allows the admin to search and view all staff members.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminAllStaffView extends View {

    /**
     * Default constructor for the {@link AdminAllStaffView} class.
     */
    public AdminAllStaffView() {}
    
    /**
     * An instance of the {@link IStaffManager} interface. Used to manage staff operations.
     */
    private final IStaffManager staffManager = ServiceLocator.getService(IStaffManager.class);
    
    /**
     * The search keywords entered by the admin.
     */
    private String searchKeywords = "";

    /**
     * The build context for rendering the view.
     */
    private final BuildContext context = BuildContext.unboundedVertical(120);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Search Staff";
    }

    @SuppressWarnings("unused")
    /**
     * Renders the view, allowing the admin to search and view all staff members.
     */
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
        repaint();
    }
    
    /**
     * Filters the staff members by the specified keyword.
     * @param keyword the keyword to filter by.
     * @return the list of filtered staff members.
     */
    private List<User> filterByKeyword(String keyword) {
        if (keyword.isBlank()) {
            return staffManager.getAllStaff();
        }

        return staffManager.findStaffByKeywords(keyword);
    }
}
