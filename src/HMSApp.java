import java.time.LocalDate;

import controller.AppointmentManager;
import services.Navigator;
import utils.MockData;
import view.LoginView;
import model.appointments.TimeSlot;

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

// 1435