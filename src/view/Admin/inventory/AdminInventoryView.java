package view.Admin.inventory;

import java.util.List;

import controller.interfaces.IInventoryManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Pharmacist.inventory.widget.InventoryTable;
import view.widgets.Title;

/**
 * This view allows the admin to manage the inventory.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminInventoryView extends View {
    /**
     * An instance of the {@link InventoryManager} class. Used to manage inventory operations.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);

    /**
     * The list of all inventory items.
     */
    private final List<InventoryItem> items = inventoryManager.getAllItems();

    /**
     * The search keyword entered by the admin.
     */
    private String keyword = "";

    /**
     * Indicates whether the view is showing search results.
     */
    private boolean showingResults = false;

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Manage Inventory";
    }

    @SuppressWarnings("unused")
    /**
     * Renders the view, allowing the admin to manage the inventory.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Manage Inventory").paint(context);

        List<InventoryItem> filteredItems = filterItems(keyword);
        new InventoryTable(filteredItems).paint(context);

        new VSpacer(1).paint(context);

        if (showingResults) {
            TextInputField selectField = new TextInputField(String.format("Select an item to update (1-%d)", filteredItems.size()));
            new TextInput(selectField).read(context, "Select an item from the list above.",
                (input) -> InputValidators.validateRange(input, filteredItems.size()));

            final InventoryItem selectedItem = filteredItems.get(selectField.getOption());
            Navigator.navigateTo(new AdminUpdateInventoryView(selectedItem));
            return;
        }

        TextInputField searchField = new TextInputField("Search for items");
        new TextInput(searchField).read(context, input -> true);

        keyword = searchField.getValue().toLowerCase();
        showingResults = !keyword.isBlank();

        repaint();
    }

    /**
     * Filters the inventory items by the specified keyword.
     * @param keyword the keyword to filter by.
     * @return the list of filtered inventory items.
     */
    private List<InventoryItem> filterItems(String keyword) {
        if (keyword.isBlank()) {
            return items;
        }

        return items.stream()
            .filter(item -> 
                String.format("%s %s %s", 
                item.getItemName(), item.getId(), item.getReplenishmentStatus())
                .toLowerCase().contains(keyword.toLowerCase()))
            .toList();
    }
}

    

// public class AdminInventoryView extends View{

//     @Override
//     public String getViewName() {
//         return("View and Manage Medication Inventory");
//     }

//     @Override
//     public void render() {

//         BuildContext context = new BuildContext(100, 10);

//         new Align(Alignment.CENTER, new Text(" [ View and Manage Medication Inventory ] ", TextStyle.BOLD)).paint(context);

//         new Menu(
//             new MenuOption("View Medication Inventory", () -> {
//                 this.viewInventory();
//             }),
//             new MenuOption("Update Current Medication Inventory", () -> {
//                 this.promptUpdateInventory();
//             }),
//             new MenuOption("Add New Medication Inventory", () -> {
//                 this.promptAddNewInventory();
//             }),
//             new MenuOption("Exit this view", () -> {
//                 this.exitView();
//             })
//         ).readOption(context);
//     }

//     private void viewInventory(){

//         BuildContext context = new BuildContext(100, 10);
//         new Align(Alignment.CENTER, new Text(" [ Medication Inventory Stock ] ", TextStyle.BOLD)).paint(context);
//         InventoryManager.printInventory();

//     }

//     private void promptAddNewInventory(){
//         BuildContext context = new BuildContext(100, 10);
//         new Align(Alignment.CENTER, new Text(" [ Medication Inventory Stock ] ", TextStyle.BOLD)).paint(context);
//         TextInputField medicName = new TextInputField("Please enter the name of the medication that you wish to update: ");
//         TextInputField stock = new TextInputField("Please enter the inventory stock: ");
//         TextInputField stockAlertLevel = new TextInputField("Please enter the stock alert level: ");
//         InventoryManager.addNewInventory(medicName.getValue(), Integer.parseInt(stock.getValue()), Integer.parseInt(stockAlertLevel.getValue()));

//     }

//     private void promptUpdateInventory(){

//         BuildContext context = new BuildContext(100, 10);
//         new Align(Alignment.CENTER, new Text(" [ Update Medication Inventory ] ", TextStyle.BOLD)).paint(context);
//         TextInputField medicName = new TextInputField("Please enter the name of the medication that you wish to update: ");
//         TextInputField newStock = new TextInputField("Please enter the new inventory stock: ");
//         if(!InventoryManager.updateInventory(medicName.getValue(), Integer.parseInt(newStock.getValue()))){
//             new Text("Failed to update stock.");
//         }
//         else{
//             new Text("Inventory stock successfully updated.");
//         }
//     }

    

//     private void exitView(){
//         Navigator.navigateTo(new AdminView());
//     }

    
// }
