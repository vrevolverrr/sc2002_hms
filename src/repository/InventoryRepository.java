package repository;

import java.util.List;

import model.inventory.InventoryItem;
import repository.interfaces.IInventoryRepository;
import repository.interfaces.IRepository;

/**
 * An implementation of {@link IRepository} that on {@link InventoryItem} data models.
 * @see https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class InventoryRepository extends BaseRepository<InventoryItem> implements IInventoryRepository {
    /**
     * The filename of the file to store the inventory items.
     */
    private final static String FILENAME = "inventory.dat";
    
    /**
     * The prefix for the ID of an {@link InventoryItem}.
     */
    public final static String ID_PREFIX = "I";

    /**
     * Constructor for the {@link InventoryRepository} class.
     */
    public InventoryRepository() {
        super(FILENAME);
    }

    /**
     * Generates an ID for an {@link InventoryItem}.
     * @return the generated ID for an {@link InventoryItem}.
     */
    @SuppressWarnings("unused")
    @Override
    public String generateId() {
        return ID_PREFIX + getItems().keySet().stream().sorted().reduce((first, second) -> second).map(
            last -> String.format("%04d", Integer.parseInt(last.substring(1)) + 1)).orElse("1001");
    }

    public List<InventoryItem> getAllItems() {
        return findAll().stream()
        .sorted((a, b) -> a.getId().compareTo(b.getId()))
        .toList();
    }

    /**
     * Finds the {@link InventoryItem} matching the item name.
     * @param itemName the name of the item.
     * @return the {@link InventoryItem} matching the name, or null if no such item exists.
     */
    public InventoryItem findByItemName(String itemName) {
        return findBy(inventoryItem -> inventoryItem.getItemName().equals(itemName)).get(0);
    }

    /**
     * Checks if an item exists in the inventory.
     * @param itemName the name of the item.
     * @return true if the item exists, false otherwise.
     */
    public boolean itemExists(String itemName) {
        return findByItemName(itemName) != null;
    }

    /**
     * Gets the stock of an item by its name.
     * @param itemName the name of the item.
     * @return the stock of the item.
     */
    public int getStockByItemName(String itemName) {
        return findByItemName(itemName).getStock();
    }

    /**
     * Updates the stock of an item by its name.
     * @param itemName the name of the item.
     * @param stock the new stock of the item.
     */
    public void updateStockByItemName(String itemName, int stock) {
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setStock(stock);
        save(inventoryItem);
    }

    /**
     * Saves an {@link InventoryItem} object to the repository.
     * @param item the {@link InventoryItem} object to save.
     * @return the saved {@link InventoryItem} object.
     */
    @Override
    public InventoryItem save(InventoryItem item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        return super.save(item);
    }
}
