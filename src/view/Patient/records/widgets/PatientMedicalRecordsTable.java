package view.Patient.records.widgets;

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

public class PatientMedicalRecordsTable extends Widget {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    
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

    // private String getDrugNameById(String id) {
    //     return inventoryManager.getInventoryItem(id).getItemName();
    // }

    // private String buildPrescription(List<Prescription> prescriptions) {
    //     return prescriptions.stream()
    //         .map(prescription -> String.format("%s %s %s %s", 
    //             getDrugNameById(prescription.getDrugId()),
    //             prescription.getDosage().getQuantity(),
    //             prescription.getDosage().getUnit().toString(),
    //             prescription.getFrequency().toString()))
    //         .collect(Collectors.joining(", "));
    // }

    // private String buildMedicalServices(List<MedicalService> medicalServices) {
    //     return medicalServices.stream().map(service -> service.toString()).collect(Collectors.joining(", "));
    // }
}
