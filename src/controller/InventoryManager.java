package controller;

import java.util.List;

import controller.interfaces.IInventoryManager;
import model.inventory.InventoryItem;
import model.users.Pharmacist;
import repository.interfaces.IInventoryRepository;

/**
 * Manages operations related to inventory.
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */
public class InventoryManager implements IInventoryManager {
    /**
     * Repository for accessing inventory data.
     */
    private final IInventoryRepository inventoryRepository;

    public InventoryManager(IInventoryRepository inventoryRepository) {
        this.inventoryRepository = inventoryRepository;
    }

    /**
     * Retrieves an inventory item by its ID.
     *
     * @param id the ID of the inventory item.
     * @return the inventory item with the specified ID.
     */
    @Override
    public InventoryItem getItem(String id) {
        return inventoryRepository.findById(id);
    }

    /**
     * Retrieves all inventory items.
     *
     * @return a list of all inventory items.
     */
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }

    /**
     * Updates the stock level alert for an inventory item.
     *
     * @param item the inventory item.
     * @param level the new stock level alert.
     */
    public void updateStockLevelAlert(InventoryItem item, int level) {
        item.setStockLevelAlert(level);
        inventoryRepository.save(item);
    }

    /**
     * Updates the stock level for an inventory item.
     * 
     * @param item the inventory item.
     * @param quantity the new stock level.
     */

    public void updateStock(InventoryItem item, int quantity) {
        item.setStock(quantity);
        inventoryRepository.save(item);
    }

    /**
     * Adds stock to an inventory item.
     *
     * @param item the inventory item.
     * @param quantity the quantity to add.
     */
    public void addStock(InventoryItem item, int quantity) {
        item.addStock(quantity);
        inventoryRepository.save(item);
    }

    /**
     * Adds stock to an inventory item by its ID.
     *
     * @param itemId the ID of the inventory item.
     * @param quantity the quantity to add.
     */
    public void addStock(String itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId);
        addStock(item, quantity);
    }

    /**
     * Deducts stock from an inventory item by its ID.
     *
     * @param itemId the ID of the inventory item.
     * @param quantity the quantity to deduct.
     */
    public void deductStock(InventoryItem item, int quantity) {
        item.deductStock(quantity);
        inventoryRepository.save(item);
    }

    /**
     * Deducts stock from an inventory item by its ID.
     *
     * @param itemId the ID of the inventory item.
     * @param quantity the quantity to deduct.
     */
    public void deductStock(String itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId);
        deductStock(item, quantity);
    }

    /**
     * Retrieves inventory items with low stock.
     *
     * @return a list of {@link InventoryItem} with low stock.
     */
    public List<InventoryItem> getLowStockInventoryItems() {
        return inventoryRepository.getLowStockInventoryItems();
    }

    /**
     * Retrieves inventory items with pending replenishment requests.
     *
     * @return a list of {@link InventoryItem} with pending replenishment requests.
     */
    public List<InventoryItem> getPendingReplenishmentRequestItems() {
        return inventoryRepository.getPendingReplenishmentRequestItems();
    }

    /**
     * Requests replenishment for an inventory item.
     *
     * @param pharmacist the pharmacist making the request.
     * @param inventoryItem the inventory item.
     * @param quantity the quantity to replenish.
     */
    public void requestReplenishment(Pharmacist pharmacist, InventoryItem inventoryItem, int quantity) {
        inventoryItem.createReplenishmentRequest(pharmacist.getPharmacistId(), quantity);
        inventoryRepository.save(inventoryItem);
    }

    /**
     * Approves a replenishment request for an inventory item.
     *
     * @param inventoryItem the inventory item.
     */
    public void approveReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.approveReplenishmentRequest();
        inventoryRepository.save(inventoryItem);
    }

    /**
     * Rejects a replenishment request for an inventory item.
     *
     * @param inventoryItem the inventory item.
     */
    public void rejectReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.rejectReplenishmentRequest();
        inventoryRepository.save(inventoryItem);
    }
}
