package repository.interfaces;

import java.util.List;

import model.inventory.InventoryItem;

public interface IInventoryRepository extends IRepository<InventoryItem> {
    /**
     * Checks if an item exists in the inventory.
     * @param itemName the name of the item.
     * @return true if the item exists, false otherwise.
     */
    public boolean itemExists(String itemName);
    
    public List<InventoryItem> getPendingReplenishmentRequestItems();
    public List<InventoryItem> getLowStockInventoryItems();
}