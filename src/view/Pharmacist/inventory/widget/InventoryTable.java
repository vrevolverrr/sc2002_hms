package view.Pharmacist.inventory.widget;

import java.util.List;

import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.inventory.InventoryItem;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;

/**
 * The {@code InventoryTable} widget displays a table of inventory items.
 * It displays the name, quantity, reorder level, stock level, and replenishment status of each item.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class InventoryTable extends Widget {

    /**
     * The {@link List} of {@link InventoryItem} to be displayed in the table.
     */
    private List<InventoryItem> medications;

    /**
     * Constructs a new {@link InventoryTable} with a given {@link List} of {@link InventoryItem}.
     * 
     * @param medications the {@link List} of {@link InventoryItem}.
     */
    public InventoryTable(List<InventoryItem> medications) {
        this.medications = medications;
    }

    /**
     * Builds the table of inventory items.
     * 
     * @param context the {@link BuildContext} used to build the widget.
     * @return a {@link String} representing the table of {@link InventoryItem}.
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Name", "Quantity", "Reorder Level", "Stock Level", "Replenishment Status");

        if (medications.isEmpty()) {
            return new Table(new TableRow("No medications found")).build(context);
        }
        
        TableRow[] rows = medications.stream()
            .map(medication -> new TableRow(
                medication.getItemName(),
                String.valueOf(medication.getStock()),
                String.valueOf(medication.getStockLevelAlert()),
                getStockLevel(medication),
                medication.getReplenishmentStatus().toString()
            ))
            .toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, rows).build(context);
    }
    
    public static String getStockLevel(InventoryItem medication) {
        int reorderLevel = medication.getStockLevelAlert();
        int stock = medication.getStock();

        if (stock <= reorderLevel) {
            return "Crtiical";
        }

        if (stock <= reorderLevel * 2) {
            return "Low";
        }

        if (stock <= reorderLevel * 3) {
            return "Medium";
        }

        return "High";
    }
}
