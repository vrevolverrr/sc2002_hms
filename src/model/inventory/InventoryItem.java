package model.inventory;

import model.BaseModel;
import model.enums.ReplenishmentStatus;

/**
 * Represents an inventory item.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-10-27
 */
public class InventoryItem extends BaseModel {

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
     * Sets the name of the item.
     * @param itemName the name of the item.
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
     * Sets the replenishment status of the item.
     * @param replenishmentStatus the replenishment status of the item.
     */
    public void setReplenishmentStatus(ReplenishmentStatus replenishmentStatus) {
        this.replenishmentStatus = replenishmentStatus;
    }

    /**
     * Gets the replenishment request of the item.
     * @return the replenishment request of the item.
     */
    public ReplenishmentRequest getReplenishmentRequest() {
        return this.replenishmentRequest;
    }

    /**
     * Sets the replenishment request of the item.
     * @param replenishmentRequest the replenishment request of the item.
     */
    public void setReplenishmentRequest(ReplenishmentRequest replenishmentRequest) {
        this.replenishmentRequest = replenishmentRequest;
    }

    /**
     * Performs a deep copy of the {@link InventoryItem} object.
     * @return a deep copy of the object.
     */
    @Override
    public InventoryItem copy() {
        InventoryItem item = new InventoryItem(getId(), getItemName(), getStock(), getStockLevelAlert());
        item.setReplenishmentStatus(getReplenishmentStatus());

        if (getReplenishmentRequest() != null) {
            item.setReplenishmentRequest(getReplenishmentRequest().copy());
        }

        return item;
    }
}
