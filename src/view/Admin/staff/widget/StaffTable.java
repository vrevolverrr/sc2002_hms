package view.Admin.staff.widget;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.users.User;

public class StaffTable extends Widget {
    private final List<User> staffs;

    public StaffTable(List<User> staffs) {
        this.staffs = staffs;
    }

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
