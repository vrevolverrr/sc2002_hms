package view.Admin;

import controller.StaffManager;
import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.Admin;
import model.Staff;
import services.Navigator;
import view.View;
    
public class AdminHospitalStaffView extends View{

    StaffManager staffManager = StaffManager.getInstance(StaffManager.class);
    
    @Override
    public String getViewName() {
        return "View and Manage Hospital Staff";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        Staff staff = (Staff) staffManager.getActiveStaff();

        new Align(Alignment.CENTER, new Text(" [ Hospital Staff Overview ] ", TextStyle.BOLD)).paint(context);

        new Table(
            new TableRow("Staff ID", "Name", "Role", "Gender", "Age"),
            new TableRow(staff.getId(), staff.getName(), staff.getRole(), staff.getGender().getValue(),
        ).paint(context);

        new VSpacer(1).paint(context);

        /*new Menu(
            new MenuOption("Update patient details", () -> this.handleUpdateDetails()),
            new MenuOption("Manage Appointments", () -> this.handleAppointments()),
            new MenuOption("View Past Diagnosis", () -> this.handleViewPastDiagnosis())
        ).readOption(context);
        */

    }

}
