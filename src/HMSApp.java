import repository.InventoryRepository;
import services.Navigator;
import view.LoginView;

/**
 * The starting point of the application.
 * @version 1.0
 * @since 2024-10-18
 */
public class HMSApp {
    public static void main(String[] args) throws Exception {
        // Initialize repository and populate with mock data
        InventoryRepository inventoryRepository = new InventoryRepository();
        inventoryRepository.populateMockData();
        
        // Ensure Navigator has access to repositories if needed, and proceed to the initial view
        Navigator.navigateTo(new LoginView());
    }
}

