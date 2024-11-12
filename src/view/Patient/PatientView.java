package view.Patient;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.appointments.PatientAppointmentView;
import view.widgets.Title;

public class PatientView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final Patient patient = (Patient) userManager.getActiveUser();

    private void handleUpdateDetails() {
        Navigator.navigateTo(new PatientUpdateView());
    }
    
    private void handleAppointments() {
        Navigator.navigateTo(new PatientAppointmentView(patient));
    }

    private void handleViewPastDiagnosis() {
        // Navigator.navigateTo(new PatientDiagnosisView() );
    }

    @Override
    public String getViewName() {
        return "Overview";
    }

    @Override
    public void render() {
        new Title("Patient Overview").paint(context);

        new Table(
            new TableRow("Patient ID", "Name", "Date of Birth", "Gender", "Age", "Blood Type"),
            new TableRow(patient.getPatientId(), patient.getName(), patient.getDobString(), patient.getGender().toString(), String.valueOf(patient.getAge()), patient.getBloodType().toString())
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update patient details", () -> this.handleUpdateDetails()),
            new MenuOption("Manage Appointments", () -> this.handleAppointments()),
            new MenuOption("View Past Diagnosis", () -> this.handleViewPastDiagnosis()),
            new MenuOption("Log Out", () -> Navigator.pop())
        ).readOption(context);
    }
    
}
