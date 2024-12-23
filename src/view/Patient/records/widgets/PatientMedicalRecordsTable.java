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

/**
 * Widget for displaying a table of a patient's medical records.
 * Each row in the table contains the date of the record, the doctor who recorded it, and the diagnosis.
 * If there are no records, a message is displayed instead.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class PatientMedicalRecordsTable extends Widget {

    /**
     * The user manager instance for retrieving doctor names.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    /**
     * The {@link List} of {@link MedicalRecordEntry} to display.
     */
    private List<MedicalRecordEntry> medicalRecord;

    /**
     * Constructs a new {@link PatientMedicalRecordsTable} widget.
     * 
     * @param medicalRecord The {@link List} of {@link MedicalRecordEntry} to display.
     */
    public PatientMedicalRecordsTable(List<MedicalRecordEntry> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    /**
     * Builds the widget to display the table of medical records.
     * 
     * @param context The {@link BuildContext} to build the widget in.
     * @return The built widget.
     */
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

    /**
     * Retrieves the name of a doctor by their ID.
     * 
     * @param id The ID of the doctor.
     * @return The name of the doctor.
     */
    private String getDoctorNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
