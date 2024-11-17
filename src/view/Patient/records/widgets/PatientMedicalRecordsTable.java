package view.Patient.records.widgets;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.medrecord.MedicalRecordEntry;
import services.ServiceLocator;

public class PatientMedicalRecordsTable extends Widget {
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    private List<MedicalRecordEntry> medicalRecord;

    public PatientMedicalRecordsTable(List<MedicalRecordEntry> medicalRecord) {
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
                getDoctorNameById(record.getDoctorId()),
                record.getDiagnosis()
           
            )).collect(Collectors.toCollection(ArrayList::new));

        return EnumeratedTable.withHeader(header, medicalRecordRows.toArray(TableRow[]::new)).build(context);
    }

    private String getDoctorNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
