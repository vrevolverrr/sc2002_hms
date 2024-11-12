package controller;

import java.util.List;

import model.InventoryItem;
import repository.InventoryRepository;

public class InventoryManager extends Manager<InventoryManager> {
    private final InventoryRepository inventoryRepository = new InventoryRepository();

    public InventoryItem getInventoryItem(String itemId) {
        return inventoryRepository.findById(itemId);
    }

    public List<InventoryItem> getAllItems() {
        return inventoryRepository.findAll();
    }
}
