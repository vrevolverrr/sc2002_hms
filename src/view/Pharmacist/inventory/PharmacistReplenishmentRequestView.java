package view.Pharmacist.inventory;

import java.util.List;

import controller.InventoryManager;
import controller.UserManager;
import controller.interfaces.IInventoryManager;
import controller.interfaces.IUserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Breadcrumbs;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.ReplenishmentStatus;
import model.inventory.InventoryItem;
import model.users.Pharmacist;
import services.Navigator;
import services.ServiceLocator;
import utils.InputValidators;
import view.View;
import view.Pharmacist.inventory.widget.InventoryTable;
import view.widgets.Title;

/**
 * This view allows the pharmacist to request replenishment for low stock medications.
 * It displays a list of low stock medications and allows the pharmacist to request replenishment.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class PharmacistReplenishmentRequestView extends View {

    /**
     * Default constructor for the {@link PharmacistReplenishmentRequestView} class.
     */
    public PharmacistReplenishmentRequestView() {}
    
    /**
     * An instance of the {@link UserManager} class. Used to retrieve the active user.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    /**
     * An instance of the {@link InventoryManager} class. Used to manage inventory items.
     */
    private final IInventoryManager inventoryManager = ServiceLocator.getService(IInventoryManager.class);

    /**
     * Gets the name of the view for the breadcrumbs.
     * @return the name of the view.
     */
    @Override
    public String getViewName() {
       return "Replenishment Request";
    }

    /**
     * Renders the view.
     */
    @Override
    public void render() {
        new Breadcrumbs().paint(context);
        new Title("Low Stock Alerts").paint(context);
        
        final List<InventoryItem> medications = 
            inventoryManager.getLowStockInventoryItems().stream().filter((item) -> 
            item.getReplenishmentStatus() == ReplenishmentStatus.NULL || 
            item.getReplenishmentStatus() == ReplenishmentStatus.REJECTED
            ).toList();
        
        new InventoryTable(medications).paint(context);
        new VSpacer(1).paint(context);

        TextInputField medicationField = new TextInputField(String.format("Select a medication to request replenishment (1-%d)", medications.size()));
        new TextInput(medicationField).read(context, "Choose a medication from the list above.",
            (input) -> InputValidators.validateRange(input, medications.size()));

        final InventoryItem selectedMedication = medications.get(medicationField.getOption());

        new VSpacer(1).paint(context);
        TextInputField quantityField = new TextInputField("Enter quantity to request");
        new TextInput(quantityField).read(context, "Enter a valid item quantity.",
            (input) -> InputValidators.validateQuantity(input));

        new VSpacer(1).paint(context);
        TextInputField confirmationField = new TextInputField(
            String.format("Confirm request of %dx %s (Y/N)", quantityField.getInt(), selectedMedication.getItemName()));
        new TextInput(confirmationField).read(context, "Y to Confirm. N to Cancel.",
            (input) -> InputValidators.validateYesNo(input));

        if (!confirmationField.getYesNo()) {
            new Pause("Request cancelled. Press any key to go back.").pause(context);
            Navigator.pop();
            return;
        }

        inventoryManager.requestReplenishment((Pharmacist) userManager.getActiveUser(), selectedMedication, quantityField.getInt());
        
        new Pause("Replenishment request submitted. Press any key to continue.").pause(context);
        repaint();
    }
    
}
