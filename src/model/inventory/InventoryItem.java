package model.inventory;

import model.BaseModel;
import model.enums.ReplenishmentStatus;
import model.users.Pharmacist;

/**
 * Represents an inventory item.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */
public class InventoryItem extends BaseModel {
    /**
     * The serializable class version number to verify whether the serialized object have loaded classes 
     * for that object that are compatible with respect to serialization. 
     * @see https://docs.oracle.com/javase/7/docs/api/java/io/Serializable.html.
     */
    private static final long serialVersionUID = 42L;
    
    /**
     * The unique ID of the item.
     */
    private String itemId;

    /**
     * The name of the item.
     */
    private String itemName;

    /**
     * The stock of the item.
     */
    private int stock;

    /**
     * The stock level alert of the item.
     */
    private int stockLevelAlert;

    /**
     * The replenishment status of the item.
     */
    ReplenishmentStatus replenishmentStatus;

    /**
     * The replenishment request of the item.
     */
    ReplenishmentRequest replenishmentRequest;
    

    /**
     * Constructor for an {@link InventoryItem}.
     * @param itemId the unique ID of the item.
     * @param itemName the name of the item.
     * @param stock the stock of the item.
     * @param stockLevelAlert the stock level alert of the item.
     */
    public InventoryItem(String itemId, String itemName, int stock, int stockLevelAlert){
        super(itemId);

        this.itemId = itemId;
        this.itemName = itemName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
        
        this.replenishmentStatus = ReplenishmentStatus.NULL;
        this.replenishmentRequest = null;
    }

    /**
     * Gets the unique ID of the item.
     * @return the unique ID of the item.
     */
    public String getItemId() {
        return this.itemId;
    }

    /**
     * Gets the name of the item.
     * @return the name of the item.
     */
    public String getItemName() {
        return this.itemName;
    }

    /**
     * Get the stock of the item.
     * @return the stock of the item.
     */
    public int getStock() {
        return this.stock;
    }

    /**
     * Sets the stock of the item.
     * @param stock the stock of the item.
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Deducts the stock of the item.
     * @param quantity the quantity to deduct.
     */
    public void deductStock(int quantity) {
        if (this.stock - quantity < 0) {
            return;
        }

        this.stock -= quantity;
    }

    /**
     * Adds stock to the item.
     * @param quantity the quantity to add.
     */
    public void addStock(int quantity) {
        this.stock += quantity;
    }

    /**
     * Gets the stock level alert of the item.
     * @return the stock level alert of the item.
     */
    public int getStockLevelAlert() {
        return this.stockLevelAlert;
    }

    /**
     * Sets the stock level alert of the item.
     * @param stockLevelAlert the stock level alert of the item.
     */
    public void setStockLevelAlert(int stockLevelAlert) {
        this.stockLevelAlert = stockLevelAlert;
    }

    /**
     * Gets the replenishment status of the item.
     * @return the replenishment status of the item.
     */
    public ReplenishmentStatus getReplenishmentStatus() {
        return this.replenishmentStatus;
    }

    /**
     * Gets the replenishment request of the item.
     * @return the replenishment request of the item.
     */
    public ReplenishmentRequest getReplenishmentRequest() {
        if (this.replenishmentRequest == null) {
            return null;
        }
        
        return this.replenishmentRequest.copy();
    }

    /**
     * Creates a replenishment request for the item.
     * @param pharmacistId the ID of the pharmacist.
     * @param quantity the quantity to replenish.
     */
    public void createReplenishmentRequest(String pharmacistId, int quantity) {
        this.replenishmentRequest = new ReplenishmentRequest(pharmacistId, quantity);
        this.replenishmentStatus = ReplenishmentStatus.PENDING;
    }

    /**
     * Approves the replenishment request for the item.
     */
    public void approveReplenishmentRequest() {
        if (this.replenishmentRequest == null || this.replenishmentStatus != ReplenishmentStatus.PENDING) {
            return;
        }

        this.replenishmentStatus = ReplenishmentStatus.APPROVED;
        this.stock += this.replenishmentRequest.getQuantity();

        this.replenishmentRequest = null;
    }

    /**
     * Rejects the replenishment request for the item.
     */
    public void rejectReplenishmentRequest() {
        if (this.replenishmentRequest == null || this.replenishmentStatus != ReplenishmentStatus.PENDING) {
            return;
        }

        this.replenishmentStatus = ReplenishmentStatus.REJECTED;
        this.replenishmentRequest = null;
    }

    /**
     * Performs a deep copy of the {@link InventoryItem} object.
     * @return a deep copy of the object.
     */
    @Override
    public InventoryItem copy() {
        InventoryItem item = new InventoryItem(getId(), getItemName(), getStock(), getStockLevelAlert());
        item.replenishmentStatus = getReplenishmentStatus();

        if (getReplenishmentRequest() != null) {
            item.replenishmentRequest = getReplenishmentRequest();
        }

        return item;
    }
}
