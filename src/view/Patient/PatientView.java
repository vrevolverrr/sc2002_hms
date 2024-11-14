package view.Patient;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.Patient;
import services.Navigator;
import view.View;
import view.Patient.appointments.PatientAppointmentView;
import view.Patient.records.PatientMedicalRecordView;
import view.Patient.update.PatientUpdateDetailsView;
import view.widgets.Title;

public class PatientView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final Patient patient = (Patient) userManager.getActiveUser();

    @Override
    public String getViewName() {
        return "Patient Overview";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Welcome " + (patient.getGender() == Gender.MALE ? "Mr. " : "Mrs. ") + patient.getName()).paint(context);

        new Table(
            new TableRow("Patient ID", "Name", "Date of Birth", "Gender", "Age", "Blood Type"),
            new TableRow(patient.getPatientId(), patient.getName(), patient.getDobString(), patient.getGender().toString(), String.valueOf(patient.getAge()), patient.getBloodType().toString())
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("View Medical Records", () -> Navigator.navigateTo(new PatientMedicalRecordView(patient))),
            new MenuOption("Update patient details", () -> Navigator.navigateTo(new PatientUpdateDetailsView(patient))),
            new MenuOption("Manage Appointments", () -> Navigator.navigateTo(new PatientAppointmentView(patient))),
            new MenuOption("Log Out", () -> Navigator.pop())
        ).readOption(context);
    }
    
}
