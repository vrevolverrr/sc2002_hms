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

/**
 * This view allows the admin to manage replenishment requests.
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class AdminReplenishmentRequestView extends View {
    /**
     * An instance of the {@link InventoryManager} class. Used to manage inventory operations.
     */
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
       return "Manage Replenishment Request";
    }

    /**
     * Renders the view, allowing the admin to manage replenishment requests.
     */
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

        TextInputField requestField = new TextInputField(String.format("Choose a request to approve or reject (1-%d)", requests.size()));
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
