/**
 * The starting point of the application.
 * @author
 * @version 1.0
 * @since 2024-10-18
 */

import services.Navigator;
import view.LoginView;


public class HMSApp {
    public static void main(String[] args) throws Exception {
        Navigator.navigateTo(new LoginView());
    }
}
