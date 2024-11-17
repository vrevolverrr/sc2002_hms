package controller.interfaces;

import java.util.List;
import model.inventory.InventoryItem;
import model.users.Pharmacist;

/**
 * This interface provides methods to manage inventory in the system.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public interface IInventoryManager {
    /**
     * Retrieves an inventory item by its ID.
     *
     * @param id the ID of the inventory item.
     * @return the inventory item with the specified ID.
     */
    public InventoryItem getItem(String id);

    /**
     * Retrieves all inventory items.
     *
     * @return a {@link List} of all {@link InventoryItem}.
     */
    public List<InventoryItem> getAllItems();

    /**
     * Updates the stock level alert for an inventory item.
     *
     * @param item the {@link InventoryItem}.
     * @param level the new stock level alert.
     */
    public void updateStockLevelAlert(InventoryItem item, int level);

    /**
     * Updates the stock level for an inventory item.
     * 
     * @param item the {@link InventoryItem}.
     * @param quantity the new stock level.
     */
    public void updateStock(InventoryItem item, int quantity);

    /**
     * Adds stock to an inventory item.
     * 
     * @param item the {@link InventoryItem}.
     * @param quantity the quantity to add.
     */
    public void addStock(InventoryItem item, int quantity);

    /**
     * Deducts stock from an inventory item.
     * 
     * @param item the {@link InventoryItem}.
     * @param quantity the quantity to deduct.
     */
    public void addStock(String itemId, int quantity);

    /**
     * Deducts stock from an inventory item.
     * 
     * @param item the {@link InventoryItem}.
     * @param quantity the quantity to deduct.
     */
    public void deductStock(String itemId, int quantity);

    /**
     * Retrieves all inventory items with low stock.
     *
     * @return a {@link List} of all {@link InventoryItem} with low stock.
     */
    public List<InventoryItem> getLowStockInventoryItems();

    /**
     * Retrieves all inventory items with pending replenishment requests.
     *
     * @return a {@link List} of all {@link InventoryItem} with pending replenishment requests.
     */
    public List<InventoryItem> getPendingReplenishmentRequestItems();

    /**
     * Requests replenishment of an inventory item.
     *
     * @param pharmacist the {@link Pharmacist} requesting replenishment.
     * @param inventoryItem the {@link InventoryItem} to replenish.
     * @param quantity the quantity to replenish.
     */
    public void requestReplenishment(Pharmacist pharmacist, InventoryItem inventoryItem, int quantity);

    /**
     * Approves a replenishment request.
     *
     * @param inventoryItem the {@link InventoryItem} to approve.
     */
    public void approveReplenishmentRequest(InventoryItem inventoryItem);
    
    /**
     * Rejects a replenishment request.
     *
     * @param inventoryItem the {@link InventoryItem} to reject.
     */
    public void rejectReplenishmentRequest(InventoryItem inventoryItem);
}