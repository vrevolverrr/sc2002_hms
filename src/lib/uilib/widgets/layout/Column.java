package lib.uilib.widgets.layout;

import java.util.ArrayList;
import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.MainAxisAlignment;

/**
 * The {@code Column} widget is a layout widget that arranges its child widgets
 * vertically in a column. It also allows for alignment of its children along
 * the main axis, based on the specified {@link MainAxisAlignment}.
 * 
 * <p>The column widget can be used to arrange widgets in a vertical stack, with
 * control over how the child widgets are aligned along the main axis (vertically).</p>
 * 
 * <p>The main axis alignment determines how the children are positioned vertically:
 * <ul>
 *   <li>{@link MainAxisAlignment#START} - Align children at the top (default).</li>
 *   <li>{@link MainAxisAlignment#CENTER} - Align children in the center vertically.</li>
 *   <li>{@link MainAxisAlignment#END} - Align children at the bottom.</li>
 *   <li>{@link MainAxisAlignment#SPACE_BETWEEN} - Distribute children evenly with space between them.</li>
 *   <li>{@link MainAxisAlignment#SPACE_AROUND} - Distribute children with space before the first and after the last element.</li>
 *   <li>{@link MainAxisAlignment#SPACE_EVENLY} - Distribute children with equal space between them.</li>
 * </ul>
 * </p>
 * 
 * @see MainAxisAlignment
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Column extends Widget {
    /**
     * The alignment of children along the main axis (vertical axis in this case).
     * Determines how the children are positioned within the column.
     */
    final MainAxisAlignment mainAxisAlignment;

    /**
     * The child widgets that will be arranged vertically in the column.
     */
    final Widget[] children;

    /**
     * Constructs a {@code Column} widget with a specific main axis alignment
     * and a list of child widgets to be arranged vertically.
     * 
     * @param mainAxisAlignment The alignment to be applied to the children along the main axis (vertical axis).
     * @param children The widgets that will be arranged in the column.
     */
    public Column(MainAxisAlignment mainAxisAlignment, Widget... children) {
        this.mainAxisAlignment = mainAxisAlignment;
        this.children = children;
    }

    /**
     * Constructs a {@code Column} widget with a default main axis alignment
     * of {@link MainAxisAlignment#START} and a list of child widgets to be arranged vertically.
     * 
     * @param children The widgets that will be arranged in the column.
     */
    public Column(Widget... children) {
        this.mainAxisAlignment = MainAxisAlignment.START;
        this.children = children;
    }

    /**
     * Builds the string representation of the column's content by rendering each child widget
     * and arranging them vertically based on the specified main axis alignment.
     * 
     * @param context The context used for building the widget, providing the necessary information to render the content.
     * @return A string representing the vertically arranged children, with the specified alignment applied.
     */
    @Override
    public String build(BuildContext context) {
        List<String> renderLines = new ArrayList<String>();

        for (Widget child : children) {
            renderLines.add(child.build(context));
        }

        return String.join("\n", renderLines);
    }
    
}
