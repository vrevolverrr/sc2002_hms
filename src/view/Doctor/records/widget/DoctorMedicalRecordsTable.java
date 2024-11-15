package view.Doctor.records.widget;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.medrecord.MedicalRecordEntry;

public class DoctorMedicalRecordsTable extends Widget {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    
    private List<MedicalRecordEntry> medicalRecord;

    public DoctorMedicalRecordsTable(List<MedicalRecordEntry> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Date", "Doctor", "Diagnosis");

        if (medicalRecord.isEmpty()) {
            return new Table(new TableRow("No medical records found")).build(context);
        }

        List<TableRow> medicalRecordRows = medicalRecord.stream().map((record) -> 
            new TableRow(
                record.getDateRecorded().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                getPatientNameById(record.getPatientId()),
                record.getDiagnosis()
           
            )).collect(Collectors.toCollection(ArrayList::new));

        return EnumeratedTable.withHeader(header, medicalRecordRows.toArray(TableRow[]::new)).build(context);
    }

    private String getPatientNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
