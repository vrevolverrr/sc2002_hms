package view.Login;

import controller.interfaces.IUserManager;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Container;
import lib.uilib.widgets.base.MultiTextInput;
import lib.uilib.widgets.base.Pause;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.base.VSpacer;
import lib.uilib.widgets.layout.Column;
import model.users.User;
import services.Navigator;
import services.ServiceLocator;
import view.View;

/**
 * View for updating the password of a user upon login.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-17
 */
public class LoginUpdatePasswordView extends View {

    /**
     * Instance of {@link UserManager} for handling user-related operations.
     */
    private final IUserManager userManager = ServiceLocator.getService(IUserManager.class);
    
    /**
     * Instance of {@link User} updating their password.
     */
    private final User user;

    /**
     * Constructs a new view for updating the password of a user upon login.
     * 
     * @param user The {@link User} updating their password.
     */
    public LoginUpdatePasswordView(User user) {
        this.user = user;
    }

    /**
     * Gets the name of the view for the breadcrumbs.
     * 
     * @return The name of the view.
     */
    @Override
    public String getViewName() {
        return "Update Password";
    }

    /**
     * Renders the view to update the password of a user upon login.
     */
    @Override
    public void render() {
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
        
        new VSpacer(1).paint(context);
        new Text("For security reasons, you are required to update your password.").paint(context);
        new VSpacer(1).paint(context);

        TextInputField passwordField = new TextInputField("New Password");
        TextInputField confirmPasswordField = new TextInputField("Confirm Password");
        
        new MultiTextInput(passwordField, confirmPasswordField).readAll(
            context, (String[] values) -> values[0].equals(values[1]) || values[0].equals(user.getPassword()),
            "Password is the same as old one or passwords do not match. Please try again.");

        userManager.updatePassword(user, passwordField.getValue());

        new Pause("Password updated successfully. Press any key to go back.").pause(context);
        Navigator.pop();
    }
    
}
