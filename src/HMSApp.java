import services.Navigator;
import view.LoginView;

/**
 * The starting point of the application.
 * @author
 * @version 1.0
 * @since 2024-10-18
 */
public class HMSApp {
    public static void main(String[] args) throws Exception {
        Navigator.navigateTo(new LoginView());
        
        /*
        UserRepository rp = new UserRepository();  
        rp.save(new Patient("P1001", "Alice Brown", "123", 
                            Gender.FEMALE, LocalDate.of(1980, 5, 14), 
                            "81888888", "alice.brown@example.com", BloodType.A_POSITIVE));
        */
        
    }
}
