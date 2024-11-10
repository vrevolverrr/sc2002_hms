package model;

public class InventoryItem extends BaseModel {
    private String itemId;
    private String itemName;
    private int stock;
    private int stockLevelAlert;
    
    public InventoryItem(String itemId, String itemName, int stock, int stockLevelAlert){
        super(itemId);

        this.itemId = itemId;
        this.itemName = itemName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return this.itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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

    @Override
    public InventoryItem copy(){
        return new InventoryItem(getId(), getItemName(), getStock(), getStockLevelAlert());
    }
}
