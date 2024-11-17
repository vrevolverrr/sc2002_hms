package controller;

import java.util.List;

import model.enums.ReplenishmentStatus;
import model.inventory.InventoryItem;
import model.inventory.ReplenishmentRequest;
import model.users.Pharmacist;
import repository.InventoryRepository;

/**
 * Manages operations related to inventory.
 * @author Bryan Soong & Joyce Lee
 * @version 1.0
 * @since 2024-11-16
 */

public class InventoryManager extends Manager<InventoryManager> {
    /**
     * Repository for accessing inventory data.
     */
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    /**
     * Retrieves an inventory item by its ID.
     *
     * @param itemId the ID of the inventory item.
     * @return the inventory item with the specified ID.
     */
    public InventoryItem getInventoryItem(String itemId) {
        return inventoryRepository.findById(itemId);
    }

    /**
     * Retrieves an inventory item by its ID.
     *
     * @param id the ID of the inventory item.
     * @return the inventory item with the specified ID.
     */
    public InventoryItem getItem(String id) {
        return inventoryRepository.findById(id);
    }

    /**
     * Retrieves all inventory items.
     *
     * @return a list of all inventory items.
     */
    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll().stream()
        .sorted((a, b) -> a.getId().compareTo(b.getId()))
        .toList();
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
        item.setStock(item.getStock() + quantity);
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
        item.setStock(item.getStock() - quantity);
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
        return inventoryRepository.findBy(
            item -> item.getStock() <= item.getStockLevelAlert());
    }

    /**
     * Retrieves inventory items with pending replenishment requests.
     *
     * @return a list of {@link InventoryItem} with pending replenishment requests.
     */
    public List<InventoryItem> getPendingReplenishmentRequestItems() {
        return inventoryRepository.findBy(
            item -> item.getReplenishmentStatus() == ReplenishmentStatus.PENDING)
            .stream()
            .sorted((a, b) -> a.getId().compareTo(b.getId()))
            .toList();
    }

    /**
     * Requests replenishment for an inventory item.
     *
     * @param pharmacist the pharmacist making the request.
     * @param inventoryItem the inventory item.
     * @param quantity the quantity to replenish.
     */
    public void requestReplenishment(Pharmacist pharmacist, InventoryItem inventoryItem, int quantity) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.PENDING);

        ReplenishmentRequest request = new ReplenishmentRequest(pharmacist.getPharmacistId(), quantity);
        inventoryItem.setReplenishmentRequest(request);

        inventoryRepository.save(inventoryItem);
    }

    /**
     * Approves a replenishment request for an inventory item.
     *
     * @param inventoryItem the inventory item.
     */
    public void approveReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.APPROVED);

        inventoryItem.setStock(inventoryItem.getStock() + inventoryItem.getReplenishmentRequest().getQuantity());
        
        // inventoryItem.setReplenishmentRequest(null);

        inventoryRepository.save(inventoryItem);
    }

    /**
     * Rejects a replenishment request for an inventory item.
     *
     * @param inventoryItem the inventory item.
     */
    public void rejectReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.REJECTED);

        // inventoryItem.setReplenishmentRequest(null);
     
        inventoryRepository.save(inventoryItem);
    }
}
