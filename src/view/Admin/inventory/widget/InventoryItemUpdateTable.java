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

public class InventoryItemUpdateTable extends Widget {
    private final InventoryItem item;

    public InventoryItemUpdateTable(InventoryItem item) {
        this.item = item;
    }

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
