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

public class PatientDetailedMedicalRecordEntryTable extends Widget {
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);
    
    private final MedicalRecordEntry entry;

    public PatientDetailedMedicalRecordEntryTable(MedicalRecordEntry entry) {
        this.entry = entry;
    }

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

    private String getDrugNameById(String id) {
        return inventoryManager.getInventoryItem(id).getItemName();
    }

    private String buildPrescription(List<Prescription> prescriptions) {
        return prescriptions.stream()
            .map(prescription -> String.format("%s %s %s %s", 
                getDrugNameById(prescription.getDrugId()),
                prescription.getDosage().getQuantity(),
                prescription.getDosage().getUnit().toString(),
                prescription.getFrequency().toString()))
            .collect(Collectors.joining(", "));
    }

    private String buildMedicalServices(List<MedicalService> medicalServices) {
        return medicalServices.stream().map(service -> service.toString()).collect(Collectors.joining(", "));
    }
    
}
