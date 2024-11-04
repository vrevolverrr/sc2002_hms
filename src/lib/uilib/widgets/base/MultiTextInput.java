package lib.uilib.widgets.base;

import java.util.function.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.interfaces.MultiInputWidget;
import view.View;

/**
 * A widget
 */
public class MultiTextInput extends Widget implements MultiInputWidget {
    private final TextInputField[] fields;

    public MultiTextInput(TextInputField... fields) {
        this.fields = fields;
    }

    @Override
    public String build(BuildContext context) {
        return "";
    }
    
    @Override
    public void readAll(BuildContext context, Predicate<String[]> predicate, String failedMessage) {
        while (true) {
            String[] fieldValues = new String[fields.length];
            int i = 0;

            for (TextInputField field : fields) {
                new TextInput(field).read(context, (String input) -> true);
                fieldValues[i] = field.getValue();
                i++;
            }

            if (predicate.test(fieldValues)) {
                break;
            }

            new VSpacer(1).paint(context);
            new Text("Invalid user credentials. Please try again.", TextStyle.BOLD).paint(context);

            View.gotoPrevNthLine(2);
            View.clearLines(fields.length);

            for (TextInputField field : fields) {
                new Text(field.getLabel() + ": ").paint(context);
            }

            View.gotoPrevNthLine(fields.length);
        }
    }
}
