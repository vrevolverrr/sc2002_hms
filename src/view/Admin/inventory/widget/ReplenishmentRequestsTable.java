package view.Admin.inventory.widget;

import java.util.List;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.inventory.InventoryItem;

public class ReplenishmentRequestsTable extends Widget {
    final private UserManager userManager = UserManager.getInstance(UserManager.class);
    private final List<InventoryItem> requestedItems;

    public ReplenishmentRequestsTable(List<InventoryItem> requestedItems) {
        this.requestedItems = requestedItems;
    }

    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Name", "Quantity", "Reorder Level", "Requested Qty", "Requested By", "Status");

        if (requestedItems.isEmpty()) {
            return new Table(new TableRow("No medications found")).build(context);
        }


        TableRow[] rows = requestedItems.stream()
            .map(item -> new TableRow(
                item.getItemName(),
                String.valueOf(item.getStock()),
                String.valueOf(item.getStockLevelAlert()),
                String.valueOf(item.getReplenishmentRequest().getQuantity()),
                getNameById(item.getReplenishmentRequest().getPharmacistId()),
                item.getReplenishmentStatus().toString()
            ))
            .toArray(TableRow[]::new);

        return EnumeratedTable.withHeader(header, rows).build(context);
    }

    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
    
}
