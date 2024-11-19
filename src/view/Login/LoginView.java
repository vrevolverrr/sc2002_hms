package view.Login;

import controller.UserManager;
import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Container;
import lib.uilib.widgets.base.MultiTextInput;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Column;
import model.users.User;
import services.Navigator;
import services.ServiceLocator;
import view.View;
import view.Admin.AdminView;
import view.Doctor.DoctorView;
import view.Patient.PatientView;
import view.Pharmacist.PharmacistView;

/**
 * View for the login screen.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class LoginView extends View {

    /**
     * Default constructor for the {@link LoginView} class.
     */
    public LoginView() {}
    
    /**
     * The singleton instance of {@link UserManager}.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);

    @Override
    public String getViewName() {
        // Omitted since this view doesn't have to be in the breadcrumbs. 
        return "";
    }

    /**
     * Event handler for the login action.
     * @param userID the user ID entered by the user.
     * @param password the password entered by the user.
     * @return whether the login was succesful or not.
     */
    private boolean handleLogin(String userID, String password) {
        // All handlers must be defined in their own methods and prefixed with "handleXXX"
        User user = userManager.authenticate(userID, password);
        if (user == null) {
            return false;
        }

        return true;
    }

    /**
     * Renders the login screen.
     */
    @Override
    public void render() {
        // Specify the size of the view (100 lines by 5 lines)
        BuildContext context = new BuildContext(100, 5);

        new Container.Builder(
            new Column(
                new Text("Hospital Managemeny System (HMS)", TextStyle.BOLD),
                new Text("version 1.0"),
                new VSpacer(1)
            ))
            .setHorizontalAlignment(Alignment.CENTER)
            .setVerticalAlignment(Alignment.CENTER)
            .setBorder(Border.DOUBLE)
            .setShrink(false).buildContainer(context).paint(context);
        
        // Define the fields required to be read and their labels.
        TextInputField userId = new TextInputField("User ID");
        TextInputField password = new TextInputField("Password");
        
        // Use MultiTextInput on each of the required fields to read all the fields
        // and perform validation logic on all the fieds read.
        new MultiTextInput(userId, password).readAll(
            context, (String[] values) -> handleLogin(values[0], values[1]),
            "Incorrect User ID or password. Please try again.");

        // Logic after the input is typically navigation logic, or actions that trigger
        // after the input is validated and complete, since each read() or readAll() call
        // will block the UI

        final User activeUser = userManager.getActiveUser(); 

        if (activeUser.isDefaultPassword()) {
            Navigator.navigateTo(new LoginUpdatePasswordView(activeUser));
            return;
        }

        View nextView;

        switch (userManager.getActiveUser().getRole()) {
            case PATIENT:
                nextView = new PatientView();
                break;

            case ADMIN:
                nextView = new AdminView();
                break;

            case PHARMACIST:
                nextView = new PharmacistView();
                break;

            case DOCTOR:
                nextView = new DoctorView();
                break;
        
            default:
                nextView = new LoginView();
                break;
        }
           
         Navigator.navigateTo(nextView);        
    }
}
