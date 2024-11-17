package view.Admin.inventory.widget;

import java.util.List;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.Widget;
import lib.uilib.widgets.base.EnumeratedTable;
import lib.uilib.widgets.base.Table;
import model.inventory.InventoryItem;
import services.ServiceLocator;

/**
 * A widget that displays a table of replenishment requests for inventory items.
 * This widget is used to provide administrators with a clear view of all pending
 * replenishment requests, including details about the item, requested quantity, 
 * and the pharmacist who initiated the request.
 * <p>
 * The table includes the following columns:
 * <ul>
 *     <li>Item Name</li>
 *     <li>Stock Quantity</li>
 *     <li>Reorder Level</li>
 *     <li>Requested Quantity</li>
 *     <li>Requested By</li>
 *     <li>Status</li>
 * </ul>
 * </p>
 * 
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class ReplenishmentRequestsTable extends Widget {
    /**
     * The user manager responsible for retrieving user information.
     */
    final private IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    /**
     * The list of inventory items with pending replenishment requests.
     */
    private final List<InventoryItem> requestedItems;

    /**
     * Constructs a new {@code ReplenishmentRequestsTable} for the given list of requested items.
     * 
     * @param requestedItems the list of inventory items with pending replenishment requests
     */
    public ReplenishmentRequestsTable(List<InventoryItem> requestedItems) {
        this.requestedItems = requestedItems;
    }

    /**
     * Builds the UI representation of the replenishment requests table.
     * <p>
     * If no requests are present, the table displays a single row stating "No replenishment requests found."
     * Otherwise, the table includes a header row and one row for each replenishment request,
     * displaying the details of the request.
     * </p>
     * 
     * @param context the build context in which the UI elements are rendered
     * @return the built UI structure as a string
     */
    @Override
    public String build(BuildContext context) {
        TableRow header = new TableRow("Name", "Quantity", "Reorder Level", "Requested Qty", "Requested By", "Status");

        if (requestedItems.isEmpty()) {
            return new Table(new TableRow("No replenishment requests found")).build(context);
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

    /**
     * Retrieves the name of the user (pharmacist) who initiated the replenishment request
     * based on their ID.
     * 
     * @param id the ID of the pharmacist
     * @return the name of the pharmacist
     */
    private String getNameById(String id) {
        return userManager.getUser(id).getName();
    }
    
}
