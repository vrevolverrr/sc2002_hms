package view.Patient.records.widgets;

import java.util.List;
import java.util.stream.Collectors;

import controller.interfaces.IInventoryManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import model.enums.MedicalService;
import model.medrecord.MedicalRecordEntry;
import model.prescriptions.Prescription;
import services.ServiceLocator;

/**
 * Widget for displaying a detailed table of a patient's medical record entry.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class PatientDetailedMedicalRecordEntryTable extends Widget {
    /**
     * {@link InventoryManager} instance for retrieving drug names.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);
    
    /**
     * The {@link MedicalRecordEntry} to display.
     */
    private final MedicalRecordEntry entry;

    /**
     * Constructs a new {@link PatientDetailedMedicalRecordEntryTable} widget.
     * 
     * @param entry The {@link MedicalRecordEntry} to display.
     */
    public PatientDetailedMedicalRecordEntryTable(MedicalRecordEntry entry) {
        this.entry = entry;
    }

    /**
     * Builds the widget to display the detailed table of a medical record entry.
     * 
     * @param context The {@link BuildContext} to build the widget in.
     * @return The built widget.
     */
    @Override
    public String build(BuildContext context) {
        return EnumeratedTable.headerless(
            new TableRow("Date Recorded", entry.getDateRecorded().toString()),
            new TableRow("Diagnosis", entry.getDiagnosis()),
            new TableRow("Treatment Plan", entry.getTreatmentPlan()),
            new TableRow("Prescription", buildPrescription(entry.getPrescription())),
            new TableRow("Medical Services", buildMedicalServices(entry.getMedicalServices()))
        ).build(context);
    }

    /**
     * Retrieves the name of a drug given its ID.
     * 
     * @param id The ID of the drug.
     * @return The name of the drug.
     */
    private String getDrugNameById(String id) {
        return inventoryManager.getInventoryItem(id).getItemName();
    }

    /**
     * Builds a string representation of a list of prescriptions.
     * 
     * @param prescriptions The {@link List} of {@link Prescription}.
     * @return The string representation of the {@link Prescription}.
     */
    private String buildPrescription(List<Prescription> prescriptions) {
        return prescriptions.stream()
            .map(prescription -> String.format("%s %s %s %s", 
                getDrugNameById(prescription.getDrugId()),
                prescription.getDosage().getQuantity(),
                prescription.getDosage().getUnit().toString(),
                prescription.getFrequency().toString()))
            .collect(Collectors.joining(", "));
    }

    /**
     * Builds a string representation of a list of medical services.
     * 
     * @param medicalServices The {@link List} of {@link MedicalService}.
     * @return The string representation of the {@link MedicalService}.
     */
    private String buildMedicalServices(List<MedicalService> medicalServices) {
        return medicalServices.stream().map(service -> service.toString()).collect(Collectors.joining(", "));
    }
    
}
