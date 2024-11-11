package lib.uilib.widgets.base;

import java.util.stream.*;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.layout.Column;
import view.View;

public class Menu extends Widget {
    private final MenuOption[] options;    

    public Menu(MenuOption... options) {
        this.options = options;
    }

    private static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

    private boolean inRange(int selection) {
        if (selection < 1 || selection > this.options.length) {
            return false;
        }
        
        return true;
    }

    @Override
    public String build(BuildContext context) {
        Text[] optionLabels = IntStream.range(0, options.length).mapToObj((int i) -> new Text((i + 1) + ". " + options[i].getTitle())).toArray(Text[]::new);
        return new Column(optionLabels).build(context);
    }

    @SuppressWarnings("unused")
    public void readOption(BuildContext context) {
        this.paint(context);
        new VSpacer(1).paint(context);

        TextInputField selectedOption = new TextInputField("Select an option");

        while (true) {
            new TextInput(selectedOption).read(context, (String input) -> true);

            if (isInt(selectedOption.getValue()) && inRange(Integer.parseInt(selectedOption.getValue()))) {
                this.options[Integer.parseInt(selectedOption.getValue()) - 1].getAction().run();
                break;
            }

            new VSpacer(1).paint(context);
            new Text("Invalid option selected.", TextStyle.BOLD).paint(context);

            View.gotoPrevNthLine(2);
            View.clearLines(1);
        }
    }
}
