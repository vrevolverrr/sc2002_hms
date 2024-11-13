package view.Pharmacist;

import controller.UserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.User;
import services.Navigator;
import view.View;
import view.Pharmacist.inventory.PharmacistInventoryView;
import view.Pharmacist.inventory.PharmacistReplenishmentRequestView;
import view.Pharmacist.prescription.PharmacistPrescriptionView;
import view.widgets.Title;

public class PharmacistView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final User activeUser = userManager.getActiveUser();

    @Override
    public String getViewName() {
        return("Pharmacist");
    }

    @Override
    public void render() {
        new Title("Welcome " + (activeUser.getGender() == Gender.MALE ? "Mr. " : "Mrs.") + activeUser.getName()).paint(context);
        
        new Table(
            new TableRow("Pharmacist ID", "Name", "Date of Birth", "Gender", "Age"),
            new TableRow(activeUser.getId(), activeUser.getName(), activeUser.getDobString(), 
            activeUser.getGender().toString(), String.valueOf(activeUser.getAge()))
        ).paint(context);

        new VSpacer(1).paint(context);

        new Menu(
            new MenuOption("Update Prescription Status", () -> 
                Navigator.navigateTo(new PharmacistPrescriptionView())),
            new MenuOption("View Medication Inventory", () ->
                Navigator.navigateTo(new PharmacistInventoryView())),
            new MenuOption("Submit Replenishment Request", () -> 
                Navigator.navigateTo(new PharmacistReplenishmentRequestView())),
            new MenuOption("Log Out", () -> Navigator.pop())

        ).readOption(context);
    }
}

