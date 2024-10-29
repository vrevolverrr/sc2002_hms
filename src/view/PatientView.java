package view;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRecord;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.Patient;
import model.User;
import services.Navigator;

public class PatientView extends View {
        UserManager userManager = UserManager.getInstance(UserManager.class);

    private void handleUpdateDetails() {
        Navigator.navigateTo(new LoginView());
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

        // Type cast is valid since user is guaranteed to be a Patient.
        Patient patient = (Patient) userManager.getActiveUser();

        new Align(Alignment.CENTER, new Text(" [ Patient Overview ] ", TextStyle.BOLD)).paint(context);

        new Table(
            new TableRecord("Patient ID", "Name", "Date of Birth", "Gender", "Blood Type"),
            new TableRecord(patient.getPatientId(), patient.getName(), patient.getDobString(), patient.getGender().getValue(), patient.getBloodType().getValue())
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update patient details", () -> this.handleUpdateDetails()),
            new MenuOption("Manage Appointments", () -> this.handleAppointments()),
            new MenuOption("View Past Diagnosis", () -> this.handleViewPastDiagnosis())
        ).readOption(context);
    }
    
}
