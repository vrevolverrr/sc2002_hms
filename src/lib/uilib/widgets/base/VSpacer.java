package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;

/**
 * A widget that draws an empty space.
 * <pre>
 * new VSpacer(2).paint(context);
 * </pre>
 */
public class VSpacer extends Widget {
    public int lines;

    public VSpacer(int lines) {
        this.lines = lines;
    }

    @Override
    public String build(BuildContext context) {
        String spaceLine = " ".repeat(context.getWidth()) + "\n";
        return spaceLine.repeat(lines - 1);
    }
}
