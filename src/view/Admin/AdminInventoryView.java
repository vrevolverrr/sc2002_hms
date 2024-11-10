// package view.Admin;

// import controller.InventoryManager;
// import lib.uilib.framework.BuildContext;
// import lib.uilib.framework.MenuOption;
// import lib.uilib.framework.TextInputField;
// import lib.uilib.framework.enums.Alignment;
// import lib.uilib.framework.enums.TextStyle;
// import lib.uilib.widgets.base.Menu;
// import lib.uilib.widgets.base.Text;
// import lib.uilib.widgets.layout.Align;
// import services.Navigator;
// import view.View;
    

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
