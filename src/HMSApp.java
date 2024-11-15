import services.Navigator;
import utils.MockData;
import view.Login.LoginView;

/**
 * The starting point of the application.
 * @version 1.0
 * @since 2024-10-18
 */
public class HMSApp {
    public static void main(String[] args) throws Exception {
        // Mocks sample data for the repositories.
        MockData.mockAllData();

        // Navigates to the login view.
        Navigator.navigateTo(new LoginView());
    }
}