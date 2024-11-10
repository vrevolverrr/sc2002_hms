package model;

import model.enums.ReplenishmentStatus;

public class InventoryItem extends BaseModel{

    private String itemId;
    private String itemName;
    private int stock;
    private int stockLevelAlert;
    ReplenishmentStatus replenishmentStatus;

    public InventoryItem(String itemId, String itemName, int stock, int stockLevelAlert, ReplenishmentStatus replenishmentStatus){
        super(itemId);

        this.itemId = itemId;
        this.itemName = itemName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
        this.replenishmentStatus = replenishmentStatus;
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

    @Override
    public InventoryItem copy(){
        return new InventoryItem(getId(), getItemName(), getStock(), getStockLevelAlert(), getReplenishmentStatus());
    }
}
