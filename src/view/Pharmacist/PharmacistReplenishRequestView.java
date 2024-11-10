// package view.Pharmacist;

// import java.util.List;

// import controller.InventoryManager;
// import controller.InventoryRequest;
// import lib.uilib.framework.BuildContext;
// import lib.uilib.framework.MenuOption;
// import lib.uilib.framework.enums.Alignment;
// import lib.uilib.framework.enums.TextStyle;
// import lib.uilib.widgets.base.Menu;
// import lib.uilib.widgets.base.Text;
// import lib.uilib.widgets.layout.Align;
// import services.Navigator;
// import view.View;

// public class PharmacistReplenishRequestView extends View {

//     @Override
//     public String getViewName() {
//         return("Inventory Replenishment Request View");
//     }

//     @Override
//     public void render() {
//         BuildContext context = new BuildContext(100, 10);
//         new Align(Alignment.CENTER, new Text(" [ Inventory Replenishment Request ] ", TextStyle.BOLD)).paint(context);
//         new Menu(
//             new MenuOption("View All Request", () -> {
//                 this.viewReplenishmentRequest(context);
//             }),
//             new MenuOption("Exit this view", ()-> {
//                 this.exitView();
//             })
//         ).readOption(context);
//     }

//     private void exitView(){
//         Navigator.navigateTo(new PharmacistView());
//     }

//     private void viewReplenishmentRequest(BuildContext context){
//         List<InventoryRequest> requests = InventoryManager.getInventoryRequests();
//         if (requests.isEmpty()) {
//             new Align(Alignment.CENTER, new Text("No inventory requests.", TextStyle.NORMAL)).paint(context);
//         } else {
//             new Align(Alignment.CENTER, new Text("Inventory Requests:", TextStyle.BOLD)).paint(context);
//             for (InventoryRequest request : requests) {
//                 new Text(request.toString()).paint(context);
//             }
//         }
//     }
    
// }
