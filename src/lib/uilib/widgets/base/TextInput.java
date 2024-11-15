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

public class TextInput extends Widget implements InputWidget {
    private final TextInputField field;
    private static Scanner scanner = new Scanner(System.in);

    public TextInput(TextInputField field) {
        this.field = field;
    }

    @Override
    public String build(BuildContext context) {
        if (field.getLabel() == "") {
            return "";
        }

        return new Text(field.getLabel() + ": ", TextStyle.BOLD).build(context);
    }

    @Override
    public void paint(BuildContext context) {
        System.out.print(this.build(context));
    }

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
