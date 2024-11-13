package view.Pharmacist.inventory;

import lib.uilib.framework.MenuOption;
import lib.uilib.widgets.base.Menu;
import services.Navigator;
import view.View;
import view.widgets.Title;

public class PharmacistManageInventory extends View {

//     private void viewInventory(){
    
//     new Title("Medication Inventory Stock").paint(context);
    
//     // Fetch the inventory from the InventoryManager
//     List<Inventory> items = InventoryManager.getInventory();

//     // Check if the inventory is empty and display a message if it is
//     if (items.isEmpty()) {
//         new Align(Alignment.CENTER, new Text("No items in inventory.", TextStyle.NORMAL)).paint(context);
//     } else {
//         // Iterate through the inventory items and display each one
//         for (Inventory item : items) {
//             new Text(item.toString()).paint(context);
//         }
//     }
// }


//     private void promptRequestInventory(){

//         BuildContext context = new BuildContext(100, 10);
//         new Align(Alignment.CENTER, new Text(" [ Update Medication Inventory ] ", TextStyle.BOLD)).paint(context);
//         TextInputField medicName = new TextInputField("Please enter the name of the medication that you wish to request: ");
//         //TextInputField requestedStock = new TextInputField("Please enter the quantity you like to request: ");
//         if(!InventoryManager.requestInventory(medicName.getValue(), 0)){
//             new Text("Failed to request stock.");
//         }
//         else{
//             new Text("Inventory stock successfully requested.");
//         }
//     }

//     private void exitView(){
//         Navigator.navigateTo(new PharmacistView());
//     }

    @Override
    public String getViewName() {
        return("Pharmacist Inventory");
    }

    @Override
    public void render() {
        new Title("Manage Medication Inventory").paint(context);

        new Menu(
            new MenuOption("View Medication Inventory", () -> 
                Navigator.navigateTo(new PharmacistInventoryView())
            ),
            new MenuOption("Submit Replenishment Request", () -> 
                Navigator.navigateTo(new PharmacistReplenishmentRequestView())
            ),
            new MenuOption("Back", () -> Navigator.pop()
            )
        ).readOption(context);
    }
}
    
