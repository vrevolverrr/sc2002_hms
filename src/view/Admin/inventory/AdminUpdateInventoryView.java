package view.Admin.inventory;

import controller.InventoryManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Admin.inventory.widget.InventoryItemUpdateTable;
import view.widgets.Title;

public class AdminUpdateInventoryView extends View {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);
    private final InventoryItem item;

    public AdminUpdateInventoryView(InventoryItem item) {
        this.item = item;
    }

    @Override
    public String getViewName() {
        return "Update Inventory";
    }

    @Override
    public void render() {
       new Title("Update Inventory Item").paint(context);
       new InventoryItemUpdateTable(item).paint(context);

       new VSpacer(1).paint(context);

       TextInputField selectField = new TextInputField("Select field to update");
         new TextInput(selectField).read(context, "Choose either stock or stock level alert to update",
              (input) -> InputValidators.validateRange(input, 2));

        new VSpacer(1).paint(context);

        if (selectField.getOption() == 0) {
            promptUpdateStock();
        } else {
            promptUpdateStockLevelAlert();
        }

        clear();
        new Title("Update Inventory Item");
        new InventoryItemUpdateTable(item).paint(context);

        new Pause("Inventory item has been updated. Press any key to continue.").pause(context);
        Navigator.pop();
    }

    private void promptUpdateStock() {
        TextInputField stockField = new TextInputField("Enter new stock quantity");
        new TextInput(stockField).read(context, "Enter a valid stock quantity.", 
            (input) -> InputValidators.validateQuantity(input));
        
        inventoryManager.updateStock(item, stockField.getInt());
    }

    private void promptUpdateStockLevelAlert() {
        TextInputField stockLevelAlertField = new TextInputField("Enter new stock level alert");
        new TextInput(stockLevelAlertField).read(context, "Enter a valid stock level alert.", 
            (input) -> InputValidators.validateQuantity(input));
        
        inventoryManager.updateStockLevelAlert(item, stockLevelAlertField.getInt());
    }
    
}
