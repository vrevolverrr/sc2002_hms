package repository.interfaces;

import java.util.List;

import model.inventory.InventoryItem;

/**
 * The interface that defines the contract for an inventory repository.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IInventoryRepository extends IRepository<InventoryItem> {
    /**
     * Checks if an item exists in the inventory.
     * @param itemName the name of the item.
     * @return true if the item exists, false otherwise.
     */
    public boolean itemExists(String itemName);
    
    /**
     * Gets the inventory item with pending replenishment request.
     * @return the {@link List} of {@link InventoryItem} with pending replenishment request.
     */
    public List<InventoryItem> getPendingReplenishmentRequestItems();

    /**
     * Retrieves all inventory items that are currently low stock.
     * @return the {@link List} of {@link InventoryItem} that are low stock.
     */
    public List<InventoryItem> getLowStockInventoryItems();
}