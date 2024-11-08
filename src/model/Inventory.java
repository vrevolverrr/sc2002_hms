package model;

public class Inventory extends BaseModel{

    private String medicId;
    private String medicName;
    private int stock;
    private int stockLevelAlert;

    
    public Inventory(String medicId, String medicName, int stock, int stockLevelAlert){
        super(medicId);

        this.medicId = medicId;
        this.medicName = medicName;
        this.stock = stock;
        this.stockLevelAlert = stockLevelAlert;
    }

    @Override
    public Inventory copy(){
        return new Inventory(medicId, medicName, stock, stockLevelAlert);
    }
}
