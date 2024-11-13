package view.Pharmacist.inventory;

import java.util.List;

import controller.InventoryManager;
import controller.UserManager;
import lib.uilib.framework.TextInputField;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.TextInput;
import lib.uilib.widgets.base.VSpacer;
import model.enums.ReplenishmentStatus;
import model.inventory.InventoryItem;
import model.users.Pharmacist;
import services.InputValidators;
import services.Navigator;
import view.View;
import view.Pharmacist.inventory.widget.InventoryTable;
import view.widgets.Title;

public class PharmacistReplenishmentRequestView extends View {
    private final UserManager userManager = UserManager.getInstance(UserManager.class);
    private final InventoryManager inventoryManager = InventoryManager.getInstance(InventoryManager.class);

    @Override
    public String getViewName() {
       return "Replenishment Request";
    }

    @Override
    public void render() {
        new Title("Low Stock Alerts").paint(context);
        
        final List<InventoryItem> medications = 
            inventoryManager.getLowStockInventoryItems().stream().filter((item) -> 
            item.getReplenishmentStatus() == ReplenishmentStatus.NULL || 
            item.getReplenishmentStatus() == ReplenishmentStatus.REJECTED
            ).toList();
        
        new InventoryTable(medications).paint(context);
        new VSpacer(1).paint(context);

        TextInputField medicationField = new TextInputField("Choose a medication to request replenishment");
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
