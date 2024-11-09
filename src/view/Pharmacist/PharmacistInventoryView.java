package view.Pharmacist;

import java.util.List;

import controller.InventoryManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;

public class PharmacistInventoryView extends View {

    private void viewInventory(){
    BuildContext context = new BuildContext(100, 10);
    new Align(Alignment.CENTER, new Text(" [ Medication Inventory Stock ] ", TextStyle.BOLD)).paint(context);
    
    // Fetch the inventory from the InventoryManager
    List<InventoryManager.InventoryItem> items = InventoryManager.getInventory();

    // Check if the inventory is empty and display a message if it is
    if (items.isEmpty()) {
        new Align(Alignment.CENTER, new Text("No items in inventory.", TextStyle.NORMAL)).paint(context);
    } else {
        // Iterate through the inventory items and display each one
        for (InventoryManager.InventoryItem item : items) {
            new Text(item.toString()).paint(context);
        }
    }
}


    private void promptRequestInventory(){

        BuildContext context = new BuildContext(100, 10);
        new Align(Alignment.CENTER, new Text(" [ Update Medication Inventory ] ", TextStyle.BOLD)).paint(context);
        TextInputField medicName = new TextInputField("Please enter the name of the medication that you wish to request: ");
        TextInputField requestedStock = new TextInputField("Please enter the quantity you like to request: ");
        if(!InventoryManager.requestInventory(medicName.getValue(), Integer.parseInt(requestedStock.getValue()))){
            new Text("Failed to request stock.");
        }
        else{
            new Text("Inventory stock successfully requested.");
        }
    }

    

    private void exitView(){
        Navigator.navigateTo(new PharmacistView());
    }

    @Override
    public String getViewName() {
        return("View Management Medication Inventory");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Align(Alignment.CENTER, new Text(" [ View and Manage Medication Inventory ] ", TextStyle.BOLD)).paint(context);

        new Menu(
            new MenuOption("View Medication Inventory", () -> {
                this.viewInventory();
            }),
            new MenuOption("Replenish Request of Medication", () -> {
                this.promptRequestInventory();
            }),
            new MenuOption("Exit this view", () -> {
                this.exitView();;;
            })
        ).readOption(context);
    }
}
    
