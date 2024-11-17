package view.Doctor.records.widget;

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
 * The {@link DoctorMedicalRecordsTable} class is a {@link Widget} that generates a table of medical records
 * for a doctor. The table displays the date, doctor, and diagnosis for each medical record entry.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class DoctorMedicalRecordsTable extends Widget {
    /**
     * The {@link UserManager} instance used to retrieve user details, such as patient name by their ID.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    /**
     * A {@link List} of {@link MedicalRecordEntry} representing the medical records to be displayed.
     */
    private List<MedicalRecordEntry> medicalRecord;

    /**
     * Constructs a new {@link DoctorMedicalRecordsTable} with the given list of medical records.
     * 
     * @param medicalRecord a {@link List} of {@link MedicalRecordEntry} representing the medical records to be displayed.
     */
    public DoctorMedicalRecordsTable(List<MedicalRecordEntry> medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    /**
     * Builds the UI for displaying the doctor's medical records in a table format. The table contains:
     * <ul>
     *   <li>Date of the medical record</li>
     *   <li>Patient name (retrieved using the patient's ID)</li>
     *   <li>Diagnosis of the medical record</li>
     * </ul>
     * 
     * @param context the {@link BuildContext} used for rendering the widget.
     * @return a {@link String} representing the built table UI.
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
                getPatientNameById(record.getPatientId()),
                record.getDiagnosis()
           
            )).collect(Collectors.toCollection(ArrayList::new));

        return EnumeratedTable.withHeader(header, medicalRecordRows.toArray(TableRow[]::new)).build(context);
    }

    /**
     * Retrieves the name of the patient with the given ID.
     * 
     * @param id the ID of the patient
     * @return a {@link String} representing the name of the patient
     */
    private String getPatientNameById(String id) {
        return userManager.getUser(id).getName();
    }
}
