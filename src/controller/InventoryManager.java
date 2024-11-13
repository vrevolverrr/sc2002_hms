package controller;

import java.util.List;

import model.enums.ReplenishmentStatus;
import model.inventory.InventoryItem;
import model.inventory.ReplenishmentRequest;
import model.users.Pharmacist;
import repository.InventoryRepository;

public class InventoryManager extends Manager<InventoryManager> {
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    public InventoryItem getInventoryItem(String itemId) {
        return inventoryRepository.findById(itemId);
    }

    public InventoryItem getItem(String id) {
        return inventoryRepository.findById(id);
    }

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll().stream()
        .sorted((a, b) -> a.getId().compareTo(b.getId()))
        .toList();
    }

    public void updateStockLevelAlert(InventoryItem item, int level) {
        item.setStockLevelAlert(level);
        inventoryRepository.save(item);
    }

    public void updateStock(InventoryItem item, int quantity) {
        item.setStock(quantity);
        inventoryRepository.save(item);
    }

    public void addStock(InventoryItem item, int quantity) {
        item.setStock(item.getStock() + quantity);
        inventoryRepository.save(item);
    }

    public void addStock(String itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId);
        addStock(item, quantity);
    }

    public void deductStock(InventoryItem item, int quantity) {
        item.setStock(item.getStock() - quantity);
        inventoryRepository.save(item);
    }

    public void deductStock(String itemId, int quantity) {
        InventoryItem item = inventoryRepository.findById(itemId);
        deductStock(item, quantity);
    }

    public List<InventoryItem> getLowStockInventoryItems() {
        return inventoryRepository.findBy(
            item -> item.getStock() <= item.getStockLevelAlert());
    }

    public List<InventoryItem> getPendingReplenishmentRequestItems() {
        return inventoryRepository.findBy(
            item -> item.getReplenishmentStatus() == ReplenishmentStatus.PENDING)
            .stream()
            .sorted((a, b) -> a.getId().compareTo(b.getId()))
            .toList();
    }

    public void requestReplenishment(Pharmacist pharmacist, InventoryItem inventoryItem, int quantity) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.PENDING);

        ReplenishmentRequest request = new ReplenishmentRequest(pharmacist.getPharmacistId(), quantity);
        inventoryItem.setReplenishmentRequest(request);

        inventoryRepository.save(inventoryItem);
    }

    public void approveReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.APPROVED);

        inventoryItem.setStock(inventoryItem.getStock() + inventoryItem.getReplenishmentRequest().getQuantity());
        
        // inventoryItem.setReplenishmentRequest(null);

        inventoryRepository.save(inventoryItem);
    }

    public void rejectReplenishmentRequest(InventoryItem inventoryItem) {
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.REJECTED);

        // inventoryItem.setReplenishmentRequest(null);
     
        inventoryRepository.save(inventoryItem);
    }
}
