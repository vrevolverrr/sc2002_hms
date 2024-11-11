package view.Patient.appointments.widgets;

import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.users.Doctor;

public class DoctorsSelectionTable extends Widget {
    private List<Doctor> doctors;

    public DoctorsSelectionTable(List<Doctor> doctors) {
        this.doctors = doctors;
    }

    @Override
    public String build(BuildContext context) {
        TableRow[] doctorRows = doctors.stream().map((doctor) -> new TableRow(
           doctor.getName(), doctor.getSpecialisation().toString(), doctor.getDobString())).toArray(TableRow[]::new);

        return new EnumeratedTable(doctorRows).build(context);
    }
    
}
