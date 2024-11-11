import services.Navigator;
import view.LoginView;

/**
 * The starting point of the application.
 * @version 1.0
 * @since 2024-10-18
 */
public class HMSApp {
    public static void main(String[] args) throws Exception {
        Navigator.navigateTo(new LoginView());
        // MockData.mockAllData();
    }
}