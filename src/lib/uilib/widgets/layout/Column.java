package lib.uilib.widgets.layout;

import java.util.ArrayList;
import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.MainAxisAlignment;

public class Column extends Widget {
    final MainAxisAlignment mainAxisAlignment;
    final Widget[] children;

    public Column(MainAxisAlignment mainAxisAlignment, Widget... children) {
        this.mainAxisAlignment = mainAxisAlignment;
        this.children = children;
    }

    public Column(Widget... children) {
        this.mainAxisAlignment = MainAxisAlignment.START;
        this.children = children;
    }

    @Override
    public String build(BuildContext context) {
        List<String> renderLines = new ArrayList<String>();

        for (Widget child : children) {
            renderLines.add(child.build(context));
        }

        return String.join("\n", renderLines);
    }
    
}
