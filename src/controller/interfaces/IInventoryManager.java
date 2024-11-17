package controller.interfaces;

import java.util.List;
import model.inventory.InventoryItem;
import model.users.Pharmacist;

public interface IInventoryManager {
    public InventoryItem getInventoryItem(String itemId);

    public InventoryItem getItem(String id);

    public List<InventoryItem> getAllItems();

    public void updateStockLevelAlert(InventoryItem item, int level);

    public void updateStock(InventoryItem item, int quantity);

    public void addStock(InventoryItem item, int quantity);

    public void addStock(String itemId, int quantity);

    public void deductStock(String itemId, int quantity);

    public List<InventoryItem> getLowStockInventoryItems();

    public List<InventoryItem> getPendingReplenishmentRequestItems();

    public void requestReplenishment(Pharmacist pharmacist, InventoryItem inventoryItem, int quantity);

    public void approveReplenishmentRequest(InventoryItem inventoryItem);
    
    public void rejectReplenishmentRequest(InventoryItem inventoryItem);
}