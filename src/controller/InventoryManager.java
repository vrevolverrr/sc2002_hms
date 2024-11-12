package controller;
import java.util.ArrayList;
import java.util.List;
import model.InventoryItem;
import model.enums.ReplenishmentStatus;
import repository.InventoryRepository;

public class InventoryManager extends Manager<InventoryManager>{

    InventoryRepository inventoryRepository = new InventoryRepository();

    public boolean findInventoryItem(String medicName){
        if(inventoryRepository.itemExists(medicName)){
            return true;
        }
        else{
            return false;
        }
    }



    public void addNewInventory(String medicID, String medicName, int stock, int stockLevelAlert, ReplenishmentStatus replenishmentStatus) {
        InventoryItem newItem = new InventoryItem(medicID, medicName, stock, stockLevelAlert, replenishmentStatus);
        inventoryRepository.save(newItem);
    }


    public void updateInventory(String medicName, int stock) {
        inventoryRepository.updateStockByItemName(medicName, stock);
    }

    public List<InventoryItem> getInventory() {
        return inventoryRepository.findAll(); 
    }


    public boolean requestInventory(String medicName){
        if(!inventoryRepository.itemExists(medicName)){
            inventoryRepository.setReplenishmentRequest(medicName);
            return true;
        }

        else{
            return false;
        }
    }

    public void approveRequestByItemName(String medicName){
        inventoryRepository.approveRequestByItemName(medicName);
    }

    public void rejectRequestByItemName(String medicName){
        inventoryRepository.approveRequestByItemName(medicName);
    }
    
}
