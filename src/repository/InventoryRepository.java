package repository;

import model.InventoryItem;
import model.enums.ReplenishmentStatus;

public class InventoryRepository extends BaseRepository<InventoryItem> {
    private final static String FILENAME = "inventory.dat";
    
    public final static String ID_PREFIX = "I";

    public InventoryRepository() {
        super(FILENAME);
    }

    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
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

    @Override
    public InventoryItem save(InventoryItem item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        return super.save(item);
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
