package model.inventory;

import model.BaseModel;
import model.enums.ReplenishmentStatus;

public class InventoryItem extends BaseModel {

    private String itemId;
    private String itemName;
    private int stock;
    private int stockLevelAlert;
    ReplenishmentStatus replenishmentStatus;
    ReplenishmentRequest replenishmentRequest;
    

    public InventoryItem(String itemId, String itemName, int stock, int stockLevelAlert){
        super(itemId);

        this.itemId = itemId;
        this.itemName = itemName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
        
        this.replenishmentStatus = ReplenishmentStatus.NULL;
        this.replenishmentRequest = null;
    }

    public String getItemId() {
        return this.itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getStockLevelAlert() {
        return this.stockLevelAlert;
    }

    public void setStockLevelAlert(int stockLevelAlert) {
        this.stockLevelAlert = stockLevelAlert;
    }

    public ReplenishmentStatus getReplenishmentStatus() {
        return this.replenishmentStatus;
    }

    public void setReplenishmentStatus(ReplenishmentStatus replenishmentStatus) {
        this.replenishmentStatus = replenishmentStatus;
    }

    public ReplenishmentRequest getReplenishmentRequest() {
        return this.replenishmentRequest;
    }

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
