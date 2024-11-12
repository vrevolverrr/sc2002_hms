package controller;

import model.InventoryItem;
import repository.InventoryRepository;

public class InventoryManager extends Manager<InventoryManager> {
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    public InventoryItem getInventoryItem(String itemId) {
        return inventoryRepository.findById(itemId);
    }
}
