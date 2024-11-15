package view.Admin.inventory;

import java.util.List;

import controller.InventoryManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.inventory.InventoryItem;
import services.Navigator;
import utils.InputValidators;
import view.View;
import view.Admin.inventory.widget.ReplenishmentRequestsTable;
import view.widgets.Title;

public class AdminReplenishmentRequestView extends View {
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);

    @Override
    public String getViewName() {
       return "Manage Replenishment Request";
    }

    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Inventory Replemenishment Request").paint(context);

        List<InventoryItem> requests = inventoryManager.getPendingReplenishmentRequestItems();
        new ReplenishmentRequestsTable(requests).paint(context);

        new VSpacer(1).paint(context);

        if (requests.isEmpty()) {
            new Pause("No pending requests. Press any key to continue.").pause(context);
            Navigator.pop();
            return;
        }

        TextInputField requestField = new TextInputField("Choose a request to approve or reject");
        new TextInput(requestField).read(context, "Choose a request from the list above.",
            (input) -> InputValidators.validateRange(input, requests.size()));

        final InventoryItem selectedRequest = requests.get(requestField.getOption());

        new VSpacer(1).paint(context);

        TextInputField confirmationField = new TextInputField(
            String.format("Approve or Reject request of %dx %s (Y/N)", 
                selectedRequest.getReplenishmentRequest().getQuantity(), selectedRequest.getItemName()));
        new TextInput(confirmationField).read(context, "Y to Approve. N to Reject.",
            (input) -> InputValidators.validateYesNo(input));

        if (confirmationField.getYesNo()) {
            inventoryManager.approveReplenishmentRequest(selectedRequest);
        } else {
            inventoryManager.rejectReplenishmentRequest(selectedRequest);
        }

        new Pause("Request has been processed. Press any key to continue").pause(context);
        repaint();
    }

    
}

// public class AdminReplenishmentRequestView extends View{

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
//                 this.viewReplenishmentRequest();;
//             }),
//             new MenuOption("Exit this view", ()-> {
//                 this.exitView();;
//             })
//         ).readOption(context);
//     }

//     private void exitView(){
//         Navigator.navigateTo(new AdminView());
//     }

//     private void viewReplenishmentRequest(){
        
//     }
    
// }
