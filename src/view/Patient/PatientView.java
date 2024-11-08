package view.Patient;

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
import model.Patient;
import services.Navigator;
import view.View;

public class PatientView extends View {
        UserManager userManager = UserManager.getInstance(UserManager.class);

    private void handleUpdateDetails() {
        Navigator.navigateTo(new PatientUpdateView());
    }
    
    private void handleAppointments() {
        Navigator.navigateTo(new PatientAppointmentView());
    }

    private void handleViewPastDiagnosis() {
        Navigator.navigateTo(new PatientDiagnosisView() );
    }

    @Override
    public String getViewName() {
        return "Overview";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        // Type cast is valid since user is guaranteed to be a Patient.
        Patient patient = (Patient) userManager.getActiveUser();

        new Align(Alignment.CENTER, new Text(" [ Patient Overview ] ", TextStyle.BOLD)).paint(context);

        new Table(
            new TableRow("Patient ID", "Name", "Date of Birth", "Gender", "Age", "Blood Type"),
            new TableRow(patient.getPatientId(), patient.getName(), patient.getDobString(), patient.getGender().getValue(), patient.getAge(), patient.getBloodType().getValue())
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update patient details", () -> this.handleUpdateDetails()),
            new MenuOption("Manage Appointments", () -> this.handleAppointments()),
            new MenuOption("View Past Diagnosis", () -> this.handleViewPastDiagnosis())
        ).readOption(context);
    }
    
}
