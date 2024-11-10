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
import model.Pharmacist;
import services.Navigator;
import view.LoginView;
import view.View;

public class PharmacistView extends View {

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private void viewAppOutcomeRec(){
        //Navigator.navigateTo(new PharmachistAppRecView);
        //would this view be the same as the patient appRecview?
    }

    private void updatePrescriptionStatus(){
        Navigator.navigateTo(new PharmacistPrescriptionView());
    }

    private void viewMedInventoryAndReplishRequest(){
        Navigator.navigateTo(new PharmacistInventoryView());
        //is this the same view as admininventoryview?
    }

    private void submitReplenishRequest(){
        Navigator.navigateTo(new PharmacistReplenishRequestView());
    }

    private void logOut(){
        Navigator.navigateTo(new LoginView());
    }

    @Override
    public String getViewName() {
        return("Pharmacist");
    }

    @Override
    public void render() {
        BuildContext context = new BuildContext(100, 10);

        new Menu(
            new MenuOption("View Appointment Outcome Record", () -> 
                this.viewAppOutcomeRec()),

            new MenuOption("Update Prescription Status", () -> 
                this.updatePrescriptionStatus()),

            new MenuOption("View Medication Inventory", () ->
                this.viewMedInventoryAndReplishRequest()),

            new MenuOption("Submit Replenishment Request", () -> 
                this.submitReplenishRequest()),

            new MenuOption("Log Out", () -> 
                this.logOut())

        ).readOption(context);
    }
}

