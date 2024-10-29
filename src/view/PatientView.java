package view;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRecord;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;

public class PatientView extends View {

    private void handleUpdateDetails() {

    }
    
    private void handleAppointments() {

    }

    private void handleViewPastDiagnosis() {

    }

    @Override
    public String getViewName() {
        return "Patient Overview";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Table(
            new TableRecord("Patient ID", "Name", "Date of Birth", "Gender", "Blood Type"),
            new TableRecord("P1001", "Bryan Soong", "05/02/2003", "Male", "O+"),
            new TableRecord("P1001", "Bryan Soong", "05/02/2003", "Male", "O+")
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update patient details", () -> this.handleUpdateDetails()),
            new MenuOption("Manage Appointments", () -> this.handleAppointments()),
            new MenuOption("View Past Diagnosis", () -> this.handleViewPastDiagnosis())
        ).readOption(context);
    }
    
}
