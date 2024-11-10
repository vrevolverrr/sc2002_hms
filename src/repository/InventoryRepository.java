package repository;

import model.InventoryItem;

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
}
