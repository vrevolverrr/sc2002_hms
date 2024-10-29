package view;

import controller.UserManager;
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
import model.User;
import services.Navigator;

public class LoginView extends View {
    @Override
    public String getViewName() {
        return "Login";
    }

    UserManager userManager = UserManager.getInstance(UserManager.class);

    private boolean handleLogin(String userID, String password) {
        User user = userManager.authenticate(userID, password);
        if (user == null) {
            return false;
        }

        return true;
    }

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
            
        TextInputField userId = new TextInputField("User ID");
        TextInputField password = new TextInputField("Password");
        
        new MultiTextInput(userId, password).readAll(
            context, (String[] values) -> handleLogin(values[0], values[1]),
            "Incorrect User ID or password. Please try again.");

        Navigator.navigateTo(new PatientView());
    }
}
