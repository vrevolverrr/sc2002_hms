package repository;

import model.InventoryItem;
import model.enums.ReplenishmentStatus;

public class InventoryRepository extends BaseRepository<InventoryItem> {
    final static String FILENAME = "inventory.dat";

    public InventoryRepository() {
        super(FILENAME);
    }

    public InventoryItem findByItemName(String itemName) {
        return findBy(inventoryItem -> inventoryItem.getItemName().equals(itemName)).get(0);
    }

    public boolean itemExists(String itemName) {
        return findByItemName(itemName) != null;
    }

    public int getStockByItemName(String itemName) {
        return findByItemName(itemName).getStock();
    }

    public void updateStockByItemName(String itemName, int stock) {
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setStock(stock);
        save(inventoryItem);
    }

    public void approveRequestByItemName(String itemName){
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.APPROVED);
        inventoryItem.setStock(5*inventoryItem.getStock());
        save(inventoryItem);
    }

    public void rejectRequestByItemName(String itemName){
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.REJECT);
        save(inventoryItem);
    }

    public void setReplenishmentRequest(String itemName){
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setReplenishmentStatus(ReplenishmentStatus.PENDING);
        save(inventoryItem);
    }

}