import services.Navigator;
import services.ServiceLocator;
import utils.MockData;
import view.Login.LoginView;

/**
 * The starting point of the application.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-18
 */
public final class HMSApp {
    /**
     * The flag to enable debug mode.
     */
    static boolean DEBUG = false;

    public static void main(String[] args) throws Exception {
        // Mocks sample data for the repositories, if required.
        if (DEBUG || args.length > 0 && args[0].equals("--mock")) {
            MockData.mockAllData();
        }

        // Registers services.
        ServiceLocator.registerHMSServices();

        // Navigates to the login view.
        Navigator.navigateTo(new LoginView());
    }
}