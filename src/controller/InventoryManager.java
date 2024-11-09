package controller;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager extends Manager<InventoryManager>{

    private static List<InventoryItem> inventory = new ArrayList<>();
    private static List<InventoryRequest> inventoryRequests = new ArrayList<>();

    // Method to add new inventory
    public static void addNewInventory(String medicName, int quantity, int reorderLevel) {
        inventory.add(new InventoryItem(medicName, quantity, reorderLevel));
    }

    // Method to get the entire inventory, to be used by the view for display
    public static List<InventoryItem> getInventory() {
        return new ArrayList<>(inventory); // Return a copy to avoid direct manipulation
    }

    // Method to request new inventory
    public static boolean requestInventory(String medicName, int quantity) {
        try {
            inventoryRequests.add(new InventoryRequest(medicName, quantity));
            return true; // Assuming the request is always successful
        } catch (Exception e) {
            return false;
        }
    }

    // Method to get all inventory requests, to be used by the view
    public static List<InventoryRequest> getInventoryRequests() {
        return new ArrayList<>(inventoryRequests); // Return a copy to avoid direct manipulation
    }

    // Inner class to represent inventory items
    public static class InventoryItem {
        private String medicName;
        private int quantity;
        private int reorderLevel;

        public InventoryItem(String medicName, int quantity, int reorderLevel) {
            this.medicName = medicName;
            this.quantity = quantity;
            this.reorderLevel = reorderLevel;
        }

        @Override
        public String toString() {
            return medicName + " - Quantity: " + quantity + ", Reorder Level: " + reorderLevel;
        }
    }

    // Inner class to represent inventory requests
    public static class InventoryRequest {
        private String medicName;
        private int quantity;

        public InventoryRequest(String medicName, int quantity) {
            this.medicName = medicName;
            this.quantity = quantity;
        }

        public String getMedicName() {
            return medicName;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return medicName + " - " + quantity;
        }
    }
}

