package view.Pharmacist.inventory.widget;

import java.util.List;

import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.inventory.InventoryItem;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;

public class InventoryTable extends Widget {
    private List<InventoryItem> medications;

    public InventoryTable(List<InventoryItem> medications) {
        this.medications = medications;
    }

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
