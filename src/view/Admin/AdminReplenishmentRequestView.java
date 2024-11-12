package view.Admin;
import controller.InventoryManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;
import model.InventoryItem;
import model.enums.ReplenishmentStatus;

public class AdminReplenishmentRequestView extends View{

    InventoryManager inventoryManager = new InventoryManager();

    @Override
    public String getViewName() {
        return("Inventory Replenishment Request View");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);
        new Align(Alignment.CENTER, new Text(" [ Inventory Replenishment Request ] ", TextStyle.BOLD)).paint(context);
        new Menu(
            new MenuOption("View All Request", () -> {
                this.viewReplenishmentRequest();;
            }),
            new MenuOption("Exit this view", ()-> {
                this.exitView();;
            })
        ).readOption(context);
    }

    private void exitView(){
        Navigator.navigateTo(new AdminView());
    }

    private void viewReplenishmentRequest(){
        inventoryManager.printReplenishmentRequest();
        BuildContext context = new BuildContext(100, 10);
        new Menu(
            new MenuOption("Manage request", () -> {
                this.promptManageRequest();
            }),
            new MenuOption("Exit", ()-> {
                Navigator.navigateTo(new AdminReplenishmentRequestView());
            })
        ).readOption(context);
        

    }

    private void promptManageRequest(){
        BuildContext context = new BuildContext(100, 10);
        TextInputField medicName = new TextInputField("Please enter the inventory item ID that you wish to manage");
        new TextInput(medicName).read(context, (input) -> true);
        new Text("Please select your option (1-2)");
        new Menu(
            new MenuOption("Approve", () -> {
                inventoryManager.approveRequestByItemName(medicName.getValue());
                new Text("Replenishment request approved.");
            }),
            new MenuOption("Reject", () -> {
                inventoryManager.rejectRequestByItemName(medicName.getValue());
                new Text("Replenishment request rejected.");
            }
            )
        ).readOption(context);
        inventoryManager.printInventory(medicName.getValue());
    }
    
}
