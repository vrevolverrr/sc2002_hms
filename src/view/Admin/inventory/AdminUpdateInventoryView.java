package view.Admin.inventory;

import controller.interfaces.IInventoryManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Admin.inventory.widget.InventoryItemUpdateTable;
import view.widgets.Title;

/**
 * This view allows the admin to update inventory items.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminUpdateInventoryView extends View {
    /**
     * An instance of the {@link InventoryManager} class. Used to manage inventory operations.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);

    /**
     * The inventory item to update.
     */
    private final InventoryItem item;

    /**
     * Constructs the view with the specified inventory item.
     * @param item the inventory item to update.
     */
    public AdminUpdateInventoryView(InventoryItem item) {
        this.item = item;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Update Inventory";
    }

    /**
     * Renders the view, allowing the admin to update the specified inventory item.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Update Inventory Item").paint(context);
        new InventoryItemUpdateTable(item).paint(context);

        new VSpacer(1).paint(context);

        TextInputField selectField = new TextInputField("Select field to update (1-2)");
        new TextInput(selectField).read(context, "Choose either stock or stock level alert to update",
            (input) -> InputValidators.validateRange(input, 2));

        new VSpacer(1).paint(context);

        if (selectField.getOption() == 0) {
            promptUpdateStock();
        } else {
            promptUpdateStockLevelAlert();
        }

        clear();
        
        // Repaint the view with the changes
        new Breadcrumbs().paint(context);
        new Title("Update Inventory Item");
        new InventoryItemUpdateTable(item).paint(context);

        new Pause("Inventory item has been updated. Press any key to continue.").pause(context);
        Navigator.pop();
    }

    /**
     * Prompts the admin to update the stock quantity of the inventory item.
     */
    private void promptUpdateStock() {
        TextInputField stockField = new TextInputField("Enter new stock quantity");
        new TextInput(stockField).read(context, "Enter a valid stock quantity.", 
            (input) -> InputValidators.validateQuantity(input));
        
        inventoryManager.updateStock(item, stockField.getInt());
    }

    /**
     * Prompts the admin to update the stock level alert of the inventory item.
     */
    private void promptUpdateStockLevelAlert() {
        TextInputField stockLevelAlertField = new TextInputField("Enter new stock level alert");
        new TextInput(stockLevelAlertField).read(context, "Enter a valid stock level alert.", 
            (input) -> InputValidators.validateQuantity(input));
        
        inventoryManager.updateStockLevelAlert(item, stockLevelAlertField.getInt());
    }
    
}
