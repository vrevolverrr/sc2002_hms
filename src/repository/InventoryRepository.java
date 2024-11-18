package repository;

import java.util.List;

import model.enums.ReplenishmentStatus;
import model.inventory.InventoryItem;
import repository.interfaces.IInventoryRepository;
import repository.interfaces.IRepository;

/**
 * An implementation of {@link IRepository} that operates on {@link InventoryItem} data models.
 * This repository provides methods to manage inventory items, including CRUD operations,
 * checking stock levels, and handling replenishment requests.
 * 
 * @see <a href="https://www.geeksforgeeks.org/dependency-injection-di-design-pattern/">Dependency Injection</a>
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
     * Constructs a new {@link InventoryRepository} instance.
     * It initializes the repository with the specified file.
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

    /**
     * Retrieves all {@link InventoryItem}s stored in the repository, sorted by their IDs.
     * 
     * @return a sorted {@link List} of all {@link InventoryItem}s.
     */
    @Override
    public List<InventoryItem> findAll() {
        return getItems().values().stream()
        .sorted((a, b) -> a.getId().compareTo(b.getId()))
        .toList();
    }

    /**
     * Finds an {@link InventoryItem} by its name.
     * 
     * @param itemName the name of the inventory item.
     * @return the {@link InventoryItem} with the specified name, or {@code null} if not found.
     */
    public InventoryItem findByItemName(String itemName) {
        return findBy(inventoryItem -> inventoryItem.getItemName().equals(itemName)).get(0);
    }

    /**
     * Retrieves all {@link InventoryItem}s with pending replenishment requests.
     * 
     * @return a {@link List} of {@link InventoryItem}s with a replenishment status of {@code PENDING}.
     */
    @Override
    public List<InventoryItem> getPendingReplenishmentRequestItems() {
        return findBy(
            item -> item.getReplenishmentStatus() == ReplenishmentStatus.PENDING)
            .stream()
            .sorted((a, b) -> a.getId().compareTo(b.getId()))
            .toList();
    }

    /**
     * Retrieves all {@link InventoryItem}s with stock levels below their alert threshold.
     * 
     * @return a {@link List} of {@link InventoryItem}s with low stock.
     */
    @Override
    public List<InventoryItem> getLowStockInventoryItems() {
        return findBy(
            item -> item.getStock() <= item.getStockLevelAlert());
    }

    /**
     * Checks whether an inventory item with the specified name exists.
     * 
     * @param itemName the name of the inventory item.
     * @return {@code true} if an item with the specified name exists, {@code false} otherwise.
     */
    @Override
    public boolean itemExists(String itemName) {
        return findByItemName(itemName) != null;
    }

    /**
     * Retrieves the stock quantity of an inventory item by its name.
     * 
     * @param itemName the name of the inventory item.
     * @return the stock quantity of the item.
     */
    public int getStockByItemName(String itemName) {
        return findByItemName(itemName).getStock();
    }

    /**
     * Updates the stock quantity of an inventory item by its name.
     * 
     * @param itemName the name of the inventory item.
     * @param stock the new stock quantity to set.
     */
    public void updateStockByItemName(String itemName, int stock) {
        InventoryItem inventoryItem = findByItemName(itemName);
        inventoryItem.setStock(stock);
        save(inventoryItem);
    }

    /**
     * Saves an {@link InventoryItem} to the repository.
     * If the item does not already have an ID, a new ID is generated for it.
     * 
     * @param item the {@link InventoryItem} to save.
     * @return the saved {@link InventoryItem}.
     */
    @Override
    public InventoryItem save(InventoryItem item) {
        if (item.getId() == null || item.getId().isBlank()) {
            item.setId(generateId());
        }

        return super.save(item);
    }
}
