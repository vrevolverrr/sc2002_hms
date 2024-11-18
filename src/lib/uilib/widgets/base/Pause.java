package lib.uilib.widgets.base;

import java.util.function.Predicate;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.interfaces.InputWidget;
import lib.uilib.widgets.layout.Column;

/**
 * A widget that displays a pause message.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Pause extends Widget implements InputWidget {
    /**
     * The message to display.
     */
    private final String pauseString;

    /**
     * Constructs a new pause widget and displays the default message.
     */
    public Pause() {
        this.pauseString = "Press any key to continue.";
    }

    /**
     * Constructs a new pause widget and displays the specified message.
     * 
     * @param pauseString The message to display.
     */
    public Pause(String pauseString) {
        this.pauseString = pauseString;
    }

    /**
     * Creates a new pause widget with the default message.
     * 
     * @return The pause widget.
     */
    public static Pause goBack() {
        return new Pause("Press any key to go back.");
    }

    /**
     * Builds the pause widget.
     * 
     * @param context The build context.
     * @return The pause widget.
     */
    @Override
    public String build(BuildContext context) {
       return new Column(
            new VSpacer(1),
            new Text(pauseString, TextStyle.BOLD)
        ).build(context);
    }

    /**
     * Reads the input.
     * 
     * @param context The build context.
     * @param predicate The predicate to test the input.
     */
    @Override
    public void read(BuildContext context, Predicate<String> predicate) {
        this.paint(context);
        
        TextInputField tempField = new TextInputField("");
        new TextInput(tempField).read(context, predicate);
    }
    
    /**
     * Pauses the widget.
     * 
     * @param context The build context.
     */
    @SuppressWarnings("unused")
    public void pause(BuildContext context) {
       this.read(context, (input) -> true);
    }
}
