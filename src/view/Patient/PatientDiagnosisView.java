package view.Patient;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import model.Patient;
import view.View;

public class PatientDiagnosisView extends View  {
    UserManager userManager = UserManager.getInstance(UserManager.class);

    @Override
    public String getViewName() {
        return "View Past Diagnosis";
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        // Type cast is valid since user is guaranteed to be a Patient.
        Patient patient = (Patient) userManager.getActiveUser();

        new Align(Alignment.CENTER, new Text(" [ Patient Overview ] ", TextStyle.BOLD)).paint(context);

        new Table(
            new TableRow("Patient ID", "Name", "Date of Birth", "Gender", "Blood Type"),
            new TableRow(patient.getPatientId(), patient.getName(), patient.getDobString(), patient.getGender().getValue(), patient.getBloodType().getValue())
        ).paint(context);
    }
    
}
