package lib.uilib.widgets.base;

import java.util.stream.*;
import lib.uilib.framework.BuildContext;
import lib.uilib.framework.MenuOption;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.layout.Column;
import view.View;

/**
 * A widget that displays a menu with options.
 * The menu is displayed as a list of options.
 * The user can select an option from the menu.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Menu extends Widget {

    /**
     * The options of the menu.
     */
    private final MenuOption[] options;    

    /**
     * Constructs a new menu widget.
     * 
     * @param options The options of the menu.
     */
    public Menu(MenuOption... options) {
        this.options = options;
    }

    /**
     * Checks if the input is an integer.
     * 
     * @param input The input to check.
     * @return True if the input is an integer, false otherwise.
     */
    private static boolean isInt(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }

    /**
     * Checks if the selection is in range.
     * 
     * @param selection The selection to check.
     * @return True if the selection is in range, false otherwise.
     */
    private boolean inRange(int selection) {
        if (selection < 1 || selection > this.options.length) {
            return false;
        }
        
        return true;
    }

    /**
     * Builds the menu widget.
     * 
     * @param context The build context.
     * @return The menu widget.
     */
    @Override
    public String build(BuildContext context) {
        Text[] optionLabels = IntStream.range(0, options.length).mapToObj((int i) -> new Text((i + 1) + ". " + options[i].getTitle())).toArray(Text[]::new);
        return new Column(optionLabels).build(context);
    }

    /**
     * Reads the selected option from the user and ensure it is valid.
     * 
     * @param context The build context.
     */
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
