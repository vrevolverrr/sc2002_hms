package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;

/**
 * A widget that draws an empty space.
 * <pre>
 * new VSpacer(2).paint(context);
 * </pre>
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class VSpacer extends Widget {
    /**
     * The number of lines to draw.
     */
    public int lines;

    /**
     * Constructs a new vertical spacer.
     * 
     * @param lines The number of lines to draw.
     */
    public VSpacer(int lines) {
        this.lines = lines;
    }

    /**
     * Builds the string representation of the vertical spacer.
     */
    @Override
    public String build(BuildContext context) {
        String spaceLine = " ".repeat(context.getWidth()) + "\n";
        return spaceLine.repeat(lines - 1);
    }
}
