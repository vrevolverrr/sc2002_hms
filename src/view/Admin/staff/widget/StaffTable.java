package view.Admin.staff.widget;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.users.User;

/**
 * A {@link Widget} that displays a table of staff information.
 */
public class StaffTable extends Widget {
    /**
     * The list of staff members to be displayed.
     */
    private final List<User> staffs;

    /**
     * Constructs a StaffTable with the specified list of staff members.
     *
     * @param staffs the list of staff members
     */
    public StaffTable(List<User> staffs) {
        this.staffs = staffs;
    }

    /**
     * Builds the table of staff information.
     * @param context the build context
     * @return the built {@code String} representation of the table
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("ID", "Name", "Age", "Gender", "Role", "DOB", "Email", "Contact");

        if (staffs.isEmpty()) {
            return new Table(new TableRow("No staff found")).build(context);
        }

        TableRow[] staffRows = staffs.stream().map((staff) -> 
            new TableRow(
                staff.getId(),
                staff.getName(),
                String.valueOf(staff.getAge()),
                staff.getGender().toString(),
                staff.getRole().toString(),
                staff.getDob().toString(),
                staff.getEmailAddress(),
                staff.getPhoneNumber()
            )).toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, staffRows).build(context);
    }
    
}
