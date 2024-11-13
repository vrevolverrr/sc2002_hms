package lib.uilib.widgets.base;

import java.util.function.Predicate;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.TextInputField;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.framework.interfaces.InputWidget;
import lib.uilib.widgets.layout.Column;

public class Pause extends Widget implements InputWidget {
    private final String pauseString;

    public Pause() {
        this.pauseString = "Press any key to continue.";
    }

    public Pause(String pauseString) {
        this.pauseString = pauseString;
    }

    @Override
    public String build(BuildContext context) {
       return new Column(
            new VSpacer(1),
            new Text(pauseString, TextStyle.BOLD)
        ).build(context);
    }

    @Override
    public void read(BuildContext context, Predicate<String> predicate) {
        this.paint(context);
        
        TextInputField tempField = new TextInputField("");
        new TextInput(tempField).read(context, predicate);
    }
    
    @SuppressWarnings("unused")
    public void pause(BuildContext context) {
       this.read(context, (input) -> true);
    }
}
