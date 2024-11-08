package model;

import model.enums.ReplenishmentStatus;

public class Inventory extends BaseModel{

    private String medicId;
    private String medicName;
    private int stock;
    private int stockLevelAlert;
    ReplenishmentStatus replenishmentStatus;

    
    public Inventory(String medicId, String medicName, int stock, int stockLevelAlert, ReplenishmentStatus replenishmentStatus){
        super(medicId);

        this.medicId = medicId;
        this.medicName = medicName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
        this.replenishmentStatus = replenishmentStatus;
    }

    @Override
    public Inventory copy(){
        return new Inventory(medicId, medicName, stock, stockLevelAlert, replenishmentStatus);
    }
}
