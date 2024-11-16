package view.Pharmacist.inventory;

import java.util.List;

import controller.InventoryManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import view.View;
import view.Pharmacist.inventory.widget.InventoryTable;
import view.widgets.Title;

/**
 * This view allows the pharmacist to view the medication inventory.
 * It displays a list of all medications and allows the pharmacist to search for specific medications.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PharmacistInventoryView extends View {
    /**
     * An instance of the {@link InventoryManager} class. Used to manage inventory items.
     */
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    
    /**
     * A list of all medications in the inventory.
     */
    final List<InventoryItem> medications = inventoryManager.getAllItems();

    /**
     * The keyword used to filter the medications.
     */
    private String keyword = "";

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Medication Inventory";
    }

    /**
     * Renders the view.
     */
    @SuppressWarnings("unused")
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Medication Inventory").paint(context);
        new VSpacer(1).paint(context);

        new Title("All Medications").paint(context);

        List<InventoryItem> filteredMedications = filterMedications(keyword);
        new InventoryTable(filteredMedications).paint(context);

        new VSpacer(1).paint(context);

        TextInputField searchField = new TextInputField("Search Medications");
        new TextInput(searchField).read(context, "Enter a keyword to search for a medication.",
            (input) -> true);

        keyword = searchField.getValue();
        repaint();
    }
    
    /**
     * Filters the medications based on the given keyword.
     * @param keyword the keyword to filter the medications.
     * @return a list of filtered medications.
     */
    private List<InventoryItem> filterMedications(String keyword) {
        if (keyword.isBlank()) {
            return medications;
        }

        return medications.stream()
            .filter(medication -> 
                String.format("%s %s %s",  medication.getItemName(), medication.getId(), 
                InventoryTable.getStockLevel(medication))
               .toLowerCase().contains(keyword.toLowerCase()))
            .toList();
    }
}
