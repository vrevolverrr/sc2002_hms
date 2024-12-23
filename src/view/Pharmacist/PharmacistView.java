package view.Pharmacist;

import controller.UserManager;
import controller.interfaces.IUserManager;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TableRow;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Menu;
import lib.uilib.widgets.base.Table;
import lib.uilib.widgets.base.VSpacer;
import model.enums.Gender;
import model.users.User;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Pharmacist.inventory.PharmacistInventoryView;
import view.Pharmacist.inventory.PharmacistReplenishmentRequestView;
import view.Pharmacist.prescription.PharmacistPrescriptionView;
import view.widgets.Title;

/**
 * This is the main view for the Pharmacist role.
 * It displays the pharmacist's details and provides options to update prescription status, view inventory, and submit replenishment requests.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public final class PharmacistView extends View {

    /**
     * Constructs a new {@link PharmacistView}.
     */
    public PharmacistView() {}
    
    /**
     * An instance of the {@link UserManager} class. Used to retrieve the active user.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
        return "Pharmacist";
    }

    /**
     * Renders the view.
     */
    @Override
    public void render() {
        final User activeUser = userManager.getActiveUser();

        new Breadcrumbs().paint(context);
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

