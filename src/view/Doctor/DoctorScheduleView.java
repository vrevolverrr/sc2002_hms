package view.Doctor;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import view.View;

public class DoctorScheduleView extends View {
    
    UserManager userManager = UserManager.getInstance(UserManager.class);

    public String getViewName() {
        return("DoctorScheduleView");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ My Schedule ] ", TextStyle.BOLD)).paint(context);

        new Table(
            new TableRow("Time","Monday", "Tuesday", "Wednesday", "Thursday", "Friday"),
            new TableRow(doctor.getDoctorId(), doctor.getName(), doctor.getDobString(), doctor.getGender().getValue(), String.valueOf(doctor.getAge()), doctor.getBloodType().getValue())
        ).paint(context);
    }
}
