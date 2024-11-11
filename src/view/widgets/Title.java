package view.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;

public class Title extends Widget {
    private final String title;

    public Title(String title) {
        this.title = title;
    }

    @Override
    public String build(BuildContext context) {
        return new Align(Alignment.CENTER, new Text(String.format("[ %s ]", title), TextStyle.BOLD)).build(context);
    }
    
}
