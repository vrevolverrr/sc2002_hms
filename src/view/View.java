package view;

import services.Navigator;
import view.Login.LoginView;
import view.Patient.*;
import lib.uilib.widgets.base.*;
import lib.uilib.widgets.layout.*;
import lib.uilib.framework.*;

/**
 * This abstract class represents a view of the application. Each actual view must implement all the methods
 * of this class.
 * @see LoginView
 * @see PatientView
 */
public abstract class View {
    /**
     * The default {@link BuildContext} implemented by a view. Can be overriden to use
     * a new build context.
     */
    public BuildContext context = new BuildContext(100, 5000);

    /**
     * Gets the name of the {@link View} to be used in generating the breadcrumbs using
     * {@link Navigator#getBreadcrumbs}.
     * @return the name of the view.
     * @see Navigator
     */
    public abstract String getViewName();

    /**
     * Describes the user interface of this {@link View}. This method should <b>contain only view logic</b>.
     * Here's an example of the render method of a view that is 100 characters wide by 10 lines tall.
     * <pre>
     * public void render() {
     *     // The BuildContext specifies the context surrounding the widget,
     *     // in our case is just the dimensions of the canvas.
     *     BuildContext context = new BuildContext(100, 10);
     *     
     *     // The main view widgets to draw. Widgets can be composed and painted 
     *     // together using multichild widgets.
     *     new Column(
     *         new Text("Welcome to HMS app!"),
     *         new VSpacer(1),
     *         new Text("Login Now")
     *     ).paint(context);
     *     
     *     // Multiple Widgets can also be used and painted separately.
     *     new Text("Bye").paint(context);
     * 
     *     // Traditional methods also work.
     *     System.out.println("Say no to widgets");
     * 
     *     // Text inputs should be at the last after all view widgets.
     *     TextInputField password = new TextInputField("Password");
     *     new TextInput(password).read(context, (password) -> password.equals("123"));
     * 
     *     // This line is called after the read completes and the input is valid.
     *     Navigator.navigateTo(new HomeScreen());
     * }
     * </pre>
     * @see BuildContext
     * @see Column
     * @see Text
     * @see VSpacer
     * @see TextInput
     * @see Navigator
     * @see Widget
     */
    public abstract void render();

    /**
     * Clears the screen and paints the view by calling {@link View#render()}.
     */
    public void paint() {
        clear();
        this.render();
    }

    /**
     * Clears the screen and paints the view again. This method is used solely for 
     * contextual purposes and is identical to paint.
     */
    public void repaint() {
        paint();
    }
    
    /**
     * Utility method to clear the screen.
     */
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();
        gotoPrevLine();
    }

    /**
     * Utility method to move the cursor up to the previous line.
     */
    public static void gotoPrevLine() {
        System.out.print(String.format("\033[1A"));
    }

    /**
     * Utility method to move the cursor up by n lines.
     * @param n the number of lines to move up by.
     */
    public static void gotoPrevNthLine(int n) {
        for (int i = 0; i < n; i++) {
            gotoPrevLine();
        }
    }

    /**
     * Utility method to move up the cursor by one line, and clear that line.
     */
    public static void clearLine() {
        System.out.print(String.format("\033[1A"));
        System.out.print("\033[2K");
    }

    /**
     * Utility method to clear the last {@code count} lines.
     * @param count the number of previous lines to clear.
     */
    public static void clearLines(int count) {
        for (int i = 0; i < count; i++) {
            clearLine();
        }
    }
}
