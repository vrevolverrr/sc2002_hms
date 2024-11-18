package lib.uilib.widgets.base;

import java.util.*;
import java.util.function.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.interfaces.InputWidget;
import services.Navigator;
import view.View;

/**
 * A {@code TextInput} widget that represents an input field for text data.
 * This widget allows the user to enter text, validates it using a predicate,
 * and displays a label associated with the input field.

 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */
public class TextInput extends Widget implements InputWidget {

    /**
     * The text input field that this widget is bound to.
     * It holds the label and value for the input field.
     */
    private final TextInputField field;

    /**
     * A scanner object to read user input from the console.
     */
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Constructs a {@code TextInput} widget that binds to the specified 
     * {@link TextInputField}.
     * 
     * @param field The {@link TextInputField} that represents the text input field.
     */
    public TextInput(TextInputField field) {
        this.field = field;
    }

    /**
     * Builds the string representation of the text input field, including its label.
     * The label is displayed in bold text style.
     * 
     * @param context The context used to build the widget's string representation.
     * @return A string that represents the label of the input field, if the label is non-empty.
     */
    @Override
    public String build(BuildContext context) {
        if (field.getLabel() == "") {
            return "";
        }

        return new Text(field.getLabel() + ": ", TextStyle.BOLD).build(context);
    }

    /**
     * Paints the text input widget to the console by printing its label.
     * 
     * @param context The context in which the widget is being painted.
     */
    @Override
    public void paint(BuildContext context) {
        System.out.print(this.build(context));
    }

     /**
     * Reads the user input, validating it against the provided predicate.
     * The user is prompted to enter a value, and the input is checked using the predicate.
     * If the input is valid, it is set in the field. If the input is invalid, the user is prompted again.
     * The global "Back" command ('0') allows the user to return to the previous view.
     * 
     * @param context The context in which the widget is painted and input is read.
     * @param predicate A {@link Predicate} that validates the user input.
     */
    @Override
    public void read(BuildContext context, Predicate<String> predicate) {
        String line = null;

        while (true) {
            this.paint(context);
            line = scanner.nextLine();

            if (line.equals("0")) {
                Navigator.pop();
                return;
            }

            if (predicate.test(line)) {
                break;
            }

            View.clearLine();
        }

        field.setValue(line);
    }

    /**
     * Reads the user input, validating it against the provided predicate.
     * If the input is invalid, an error message is displayed. The user is prompted again until valid input is provided.
     * The global "Back" command ('0') allows the user to return to the previous view.
     * 
     * @param context The context in which the widget is painted and input is read.
     * @param failedMessage The message to display if the input is invalid.
     * @param predicate A {@link Predicate} that validates the user input.
     */
    public void read(BuildContext context, String failedMessage, Predicate<String> predicate) {
        String line = null;

        while (true) {
            this.paint(context);
            line = scanner.nextLine();

            // Global "Back" command
            if (line.equals("0")) {
                Navigator.pop();
                return;
            }

            if (predicate.test(line)) {
                new VSpacer(2).paint(context);
                View.clearLines(2);        
                break;
            }

            new VSpacer(1).paint(context);
            new Text(failedMessage, TextStyle.BOLD).paint(context);

            View.gotoPrevNthLine(2);
            View.clearLines(1);
        }

        field.setValue(line);
    }
    
}
