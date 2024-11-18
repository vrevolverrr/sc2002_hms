package view.Admin.inventory.widget;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Column;
import model.inventory.InventoryItem;
import view.widgets.Title;
import lib.uilib.widgets.base.EnumeratedTable;

/**
 * A widget that displays detailed information about a specific inventory item
 * in a tabular format. This widget is used in the context of updating an inventory item,
 * showing its basic details, replenishment status, and stock details.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class InventoryItemUpdateTable extends Widget {
    /**
     * The inventory item whose details are displayed in the table.
     */
    private final InventoryItem item;

    /**
     * Constructs a new {@code InventoryItemUpdateTable} for the given inventory item.
     * 
     * @param item the inventory item to be displayed in the table
     */
    public InventoryItemUpdateTable(InventoryItem item) {
        this.item = item;
    }

    /**
     * Builds the UI representation of the inventory item's details.
     * 
     * @param context the build context in which the UI elements are rendered
     * @return the built UI structure as a string
     */ 
    @Override
    public String build(BuildContext context) {
        return new Column(
            new Table(
                new TableRow("Item Name", item.getItemName()),
                new TableRow("Replemishment Status", item.getReplenishmentStatus().toString())
            ),

            new VSpacer(1),
            new Title("Stock Details"),

            EnumeratedTable.headerless(
                new TableRow("Stock", String.valueOf(item.getStock())),
                new TableRow("Stock Level Alert", String.valueOf(item.getStockLevelAlert()))
            )
        ).build(context);
    }
    
}
