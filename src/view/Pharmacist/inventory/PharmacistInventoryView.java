package view.Pharmacist.inventory;

import java.util.List;

import controller.InventoryManager;
import controller.PharmacistManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import view.View;
import view.Pharmacist.inventory.widget.MedicationTable;
import view.widgets.Title;

public class PharmacistInventoryView extends View {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    final List<InventoryItem> medications = inventoryManager.getAllItems();

    private String keyword = "";

    @Override
    public String getViewName() {
        return "Medication Inventory";
    }

    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Title("Medication Inventory").paint(context);
        new VSpacer(1).paint(context);

        new Title("All Medications").paint(context);

        List<InventoryItem> filteredMedications = filterMedications(keyword);
        new MedicationTable(filteredMedications).paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search Medications");
        new TextInput(searchField).read(context, "Enter a keyword to search for a medication.",
            (input) -> true);

        keyword = searchField.getValue();
        repaint();
    }
    
    private List<InventoryItem> filterMedications(String keyword) {
        if (keyword.isBlank()) {
            return medications;
        }

        return medications.stream()
            .filter(medication -> 
                String.format("%s %s",  medication.getItemName(), medication.getId())
               .toLowerCase().contains(keyword.toLowerCase()))
            .toList();
    }
}
