package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;

/**
 * A {@code Text} widget that displays a string of text with optional styling.
 * This widget can truncate the text if it exceeds a specified length and applies
 * a text style to format the displayed string. 
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */

public class Text extends Widget {

    /**
     * The text content that will be displayed by the widget.
     */
    final String text;

    /**
     * The style applied to the text, such as normal, bold, etc.
     */
    final TextStyle style;

    /**
     * Constructs a {@code Text} widget with the specified text and the default 
     * text style (NORMAL).
     * 
     * @param text The text to display in the widget.
     */
    public Text(String text) {
        this.text = text;
        this.style = TextStyle.NORMAL;
    }

    /**
     * Constructs a {@code Text} widget with the specified text and style.
     * 
     * @param text The text to display in the widget.
     * @param style The text style to apply to the displayed text.
     */
    public Text(String text, TextStyle style) {
        this.text = text;
        this.style = style;
    }
    
    /**
     * Builds the text widget, applying the defined text style and truncating
     * the text if necessary based on the available width.
     * 
     * @param context The context in which the widget is being built.
     * @return A string that represents the text with applied style and truncation.
     */
    @Override
    public String build(BuildContext context) {
        return this.style.seqStart() + truncateString(context.getWidth()) + this.style.seqEnd();
    }

    /**
     * Truncates the text if it exceeds the available width.
     * 
     * @param maxLength The maximum length the text can have before being truncated.
     * @return The text, possibly truncated with ellipsis at the end.
     */
    private String truncateString(int maxLength) {
        if (this.text.length() > maxLength) {
            return this.text.substring(0, maxLength - 3) + "...";
        }

        return this.text;
    }
}
