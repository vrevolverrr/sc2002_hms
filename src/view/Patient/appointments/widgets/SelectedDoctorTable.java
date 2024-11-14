package view.Patient.appointments.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import model.users.Doctor;

public class SelectedDoctorTable extends Widget {
    private final Doctor doctor;

    public SelectedDoctorTable(Doctor doctor) {
        this.doctor = doctor;
    }

    @Override
    public String build(BuildContext context) {
        return new Table(
            new TableRow("Doctor Name", "Specialisation"),
            new TableRow("Dr. " + doctor.getName(), doctor.getSpecialisation().toString())
        ).build(context);
    }
    
}
