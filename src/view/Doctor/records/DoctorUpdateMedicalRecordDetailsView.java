package view.Doctor.records;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import controller.InventoryManager;
import controller.MedicalRecordManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.DosageUnit;
import model.enums.MedicalService;
import model.enums.MedicineFrequency;
import model.enums.PrescriptionStatus;
import model.inventory.InventoryItem;
import model.medrecord.MedicalRecordEntry;
import model.prescriptions.MedicineDosage;
import model.prescriptions.Prescription;
import utils.InputValidators;
import utils.UpdatableField;
import view.View;
import view.Patient.records.widgets.PatientDetailedMedicalRecordEntryTable;
import view.widgets.Title;

public class DoctorUpdateMedicalRecordDetailsView extends View {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final MedicalRecordManager recordManager = MedicalRecordManager.getInstance(MedicalRecordManager.class);

    private final MedicalRecordEntry entry;

    private final BuildContext context = BuildContext.unboundedVertical(125);

    public DoctorUpdateMedicalRecordDetailsView(MedicalRecordEntry medicalRecordEntry) {
        this.entry = medicalRecordEntry;
    }   

    @Override
    public String getViewName() {
        return "Update Medical Record Details";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Medical Record Details").paint(context);
        new VSpacer(1).paint(context);

        List<UpdatableField> updatableFields = new ArrayList<UpdatableField>();

        updatableFields.add(new UpdatableField(new TableRow("Date Recorded", entry.getDateRecorded().toString()), this::updateDate));
        updatableFields.add(new UpdatableField(new TableRow("Diagnosis", entry.getDiagnosis()), this::updateDiagnosis));
        updatableFields.add(new UpdatableField(new TableRow("Treatment Plan", entry.getTreatmentPlan()), this::updateTreatmentPlan));
        updatableFields.add(new UpdatableField(new TableRow("Prescription", buildPrescription(entry.getPrescription())), this::updatePrescription));
        updatableFields.add(new UpdatableField(new TableRow("Medical Services", buildMedicalServices(entry.getMedicalServices())), this::updateMedicalServices));

        new Title("Patient Medical Record").paint(context);
        new PatientDetailedMedicalRecordEntryTable(entry).paint(context);
        new VSpacer(1).paint(context);

        TextInputField updateField = new TextInputField("Choose field to update (2-5)");
        new TextInput(updateField).read(context, "Choose a valid field to update.", 
            input -> InputValidators.validateRange(input, 2, 5));

        new VSpacer(1).paint(context);

        updatableFields.get(updateField.getOption()).update();
        repaint();
    }

    private void updateDate() {}

    private void updateDiagnosis() {
        TextInputField diagnosisField = new TextInputField("Enter new diagnosis");
        new TextInput(diagnosisField).read(context, "Enter a non-empty diagnosis.", input -> !input.trim().isEmpty());

        entry.setDiagnosis(diagnosisField.getValue());
        recordManager.updateRecord(entry);
    }

    private void updateTreatmentPlan() {
        TextInputField treatmentPlanField = new TextInputField("Enter new treatment plan");
        new TextInput(treatmentPlanField).read(context, "Enter a non-empty treatment plan.", input -> !input.trim().isEmpty());

        entry.setTreatmentPlan(treatmentPlanField.getValue());
        recordManager.updateRecord(entry);
    }

    private void updatePrescription() {
        final List<InventoryItem> drugList = inventoryManager.getAllItems();

        final Prescription[] prescription = {null};

        new Text("Select medicine to add:", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        final List<MenuOption> drugOptions = 
                drugList.stream().map(drug ->
                        new MenuOption(drug.getItemName(),
                        () -> {
                            clearLines(drugList.size() + 5);
                            new Text("Selected Drug: " + drug.getItemName(), TextStyle.BOLD).paint(context);
                            prescription[0] = promptDosageDetails(drug);
                        }
                        ))
            .collect(Collectors.toCollection(ArrayList::new));
        
        new Menu(
            drugOptions.toArray(MenuOption[]::new)
        ).readOption(context);

        if (prescription[0] == null) {
            return;
        }

        // Automatically set the prescription status to DISPENSED
        prescription[0].setStatus(PrescriptionStatus.DISPENSED);

        // Add the new prescription to the existing list of prescriptions
        List<Prescription> prescriptions = entry.getPrescription();
        prescriptions.add(prescription[0]);
        entry.setPrescription(prescriptions);
        
        // Update the record
        recordManager.updateRecord(entry);
    }

    private Prescription promptDosageDetails(InventoryItem drug) {
        TextInputField[] dosageField = {null};
        final DosageUnit[] selectedUnit = {null};
        final MedicineFrequency[] selectedFreq = {null};

        final List<MenuOption> dosageOptions = 
            Stream.of(DosageUnit.values()).map(dosageOption ->
                new MenuOption(dosageOption.toString(), () -> {
                    selectedUnit[0] = dosageOption;

                    new VSpacer(1).paint(context);
                    dosageField[0] = new TextInputField(String.format("Enter dosage (%s)", dosageOption.toString()));
                    new TextInput(dosageField[0]).read(context, "Enter a valid dosage.", input -> InputValidators.validateRange(input, 99999));

                    clearLines(DosageUnit.values().length + 7);
                    new Text("Selected Dosage: " + dosageField[0].getInt() + " " + dosageOption.toString(), TextStyle.BOLD).paint(context);

                    new VSpacer(1).paint(context);
                    new Text("Enter frequency:", TextStyle.BOLD).paint(context);
                    new VSpacer(1).paint(context);

                    new Menu(
                        Stream.of(MedicineFrequency.values()).map(freq -> 
                            new MenuOption(freq.toString(), () -> {
                                selectedFreq[0] = freq;
                                clearLines(MedicineFrequency.values().length + 5);
                                new Text("Selected Frequency: "+ freq.toString(), TextStyle.BOLD).paint(context);
                            })

                        ).toArray(MenuOption[]::new)
                    ).readOption(context); 
                })
            ).toList();

        new VSpacer(1).paint(context);
        new Text("Select dosage unit:", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);
        new Menu(dosageOptions.toArray(MenuOption[]::new)).readOption(context);
        
        new VSpacer(1).paint(context);
        TextInputField prescribedQtyField = new TextInputField("Enter quantity to prescribe");
        new TextInput(prescribedQtyField).read(context, "Enter a valid item quantity.",
            (input) -> InputValidators.validateQuantity(input));

        MedicineDosage dosage = new MedicineDosage(dosageField[0].getInt(), selectedUnit[0]);
        return new Prescription(drug.getId(), prescribedQtyField.getInt(), dosage, selectedFreq[0]);
    }

    private void updateMedicalServices() {
        final MedicalService[] serviceToAdd = {null};

        new Text("Select the services provided:", TextStyle.BOLD).paint(context);
        new VSpacer(1).paint(context);

        final List<MenuOption> serviceOptions = 
                Stream.of(MedicalService.values()).map(service ->
                        new MenuOption(service.toString(), () -> 
                        serviceToAdd[0] = service
                )).collect(Collectors.toCollection(ArrayList::new));
                
        new Menu(
            serviceOptions.toArray(MenuOption[]::new)
        ).readOption(context);

        if (serviceToAdd[0] == null || entry.getMedicalServices().contains(serviceToAdd[0])) {
            return;
        }

        // Add the new service to the existing list of services
        List<MedicalService> medicalServices = entry.getMedicalServices();
        medicalServices.add(serviceToAdd[0]);
        entry.setMedicalServices(medicalServices);

        // Update the record
        recordManager.updateRecord(entry);
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