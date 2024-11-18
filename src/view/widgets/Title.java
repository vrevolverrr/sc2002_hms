package view.widgets;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.TextStyle;
import lib.uilib.widgets.base.Text;
import lib.uilib.widgets.layout.Align;

/**
 * Widget for displaying a title.
 * 
 * @author Bryan Soong, Joyce Lee
 * @version 1.0
 * @since 2024-11-10
 */
public class Title extends Widget {
    /**
     * The title to display.
     */
    private final String title;

    /**
     * Constructs a new title widget.
     * 
     * @param title The title to display.
     */
    public Title(String title) {
        this.title = title;
    }

    /**
     * Builds the title widget.
     * 
     * @param context The build context.
     * @return The built widget.
     */
    @Override
    public String build(BuildContext context) {
        return new Align(Alignment.CENTER, new Text(String.format("[ %s ]", title), TextStyle.BOLD)).build(context);
    }
    
}
