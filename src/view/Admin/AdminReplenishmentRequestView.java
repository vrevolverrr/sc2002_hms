package view.Admin;
import controller.InventoryManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;
import services.Navigator;
import view.View;
import model.Inventory;
import model.enums.ReplenishmentStatus;

public class AdminReplenishmentRequestView extends View{

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
        InventoryManager.printReplenishmentRequest();
        

    }
    
}
