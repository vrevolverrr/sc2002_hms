package view.Pharmacist;

import java.util.jar.Attributes.Name;

import controller.UserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Align;
import model.users.Pharmacist;
import services.Navigator;
import view.LoginView;
import view.View;
import view.Pharmacist.inventory.PharmacistManageInventory;

public class PharmacistView extends View {

    @Override
    public String getViewName() {
        return("Pharmacist");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Menu(
            // new MenuOption("View Appointment Outcome Record", () -> 
            //     this.viewAppOutcomeRec()),

            // new MenuOption("Update Prescription Status", () -> 
            //     this.updatePrescriptionStatus()),

            new MenuOption("View Medication Inventory", () ->
                Navigator.navigateTo(new PharmacistManageInventory())),

            // new MenuOption("Submit Replenishment Request", () -> 
            //     this.submitReplenishRequest()),

            new MenuOption("Log Out", () -> Navigator.pop())

        ).readOption(context);
    }
}

