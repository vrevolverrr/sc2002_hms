package lib.uilib.widgets.base;

import java.util.function.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.interfaces.MultiInputWidget;
import view.View;

/**
 * A widget that displays multiple text input fields.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class MultiTextInput extends Widget implements MultiInputWidget {

    /**
     * The text input fields.
     */
    private final TextInputField[] fields;


    /**
     * Constructs a new multi text input widget.
     * 
     * @param fields The text input fields.
     */
    public MultiTextInput(TextInputField... fields) {
        this.fields = fields;
    }

    /**
     * Builds the multi text input widget.
     * 
     * @param context The build context.
     * @return The multi text input widget.
     */
    @Override
    public String build(BuildContext context) {
        return "";
    }
    
    /**
     * Reads all the text input fields.
     * 
     * @param context The build context.
     * @param predicate The predicate to test the input.
     * @param failedMessage The message to display if the input is invalid.
     */
    @Override
    public void readAll(BuildContext context, Predicate<String[]> predicate, String failedMessage) {
        while (true) {
            String[] fieldValues = new String[fields.length];
            int i = 0;

            for (TextInputField field : fields) {
                new TextInput(field).read(context, (@SuppressWarnings("unused") String input) -> true);
                fieldValues[i] = field.getValue();
                i++;
            }

            if (predicate.test(fieldValues)) {
                break;
            }

            new VSpacer(1).paint(context);
            new Text(failedMessage, TextStyle.BOLD).paint(context);

            View.gotoPrevNthLine(2);
            View.clearLines(fields.length);

            for (TextInputField field : fields) {
                new Text(field.getLabel() + ": ").paint(context);
            }

            View.gotoPrevNthLine(fields.length);
        }
    }
}
