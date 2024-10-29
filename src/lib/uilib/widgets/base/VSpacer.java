package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;

public class VSpacer extends Widget {
    public int lines;

    public VSpacer(int lines) {
        this.lines = lines;
    }

    @Override
    public String build(BuildContext context) {
        String newLine = "\n";
        return newLine.repeat(lines - 1);
    }
}
