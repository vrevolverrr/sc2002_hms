package lib.uilib.widgets.layout;

import java.util.ArrayList;
import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;

/**
 * The {@code Align} widget is a layout widget that aligns its child widget's content
 * based on the specified alignment: {@link Alignment#START}, {@link Alignment#CENTER}, 
 * or {@link Alignment#END}. This widget can be used to control how a child widget is 
 * positioned horizontally within a certain width.
 * 
 * <p>For {@link Alignment#START}, the content is left-aligned. For {@link Alignment#CENTER}, 
 * the content is centered. For {@link Alignment#END}, the content is right-aligned.</p>
 * 
 * <p>This widget ensures that the child widget's content is padded accordingly to 
 * the chosen alignment and the width provided by the build context.</p>
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Align extends Widget {

    /**
     * The alignment type for positioning the child widget's content.
     * This determines whether the content is aligned to the start, center, or end.
     */
    final private Alignment alignment;

    /**
     * The child widget whose content will be aligned.
     */
    final private Widget child;

    /**
     * Constructs an {@code Align} widget that aligns the given child widget based on the specified alignment.
     * 
     * @param alignment The alignment type, such as {@link Alignment#START}, {@link Alignment#CENTER}, 
     *                  or {@link Alignment#END}.
     * @param child The child widget whose content will be aligned.
     */
    public Align(Alignment alignment, Widget child) {
        this.alignment = alignment;
        this.child = child;
    }

    /**
     * Builds the string representation of the aligned child widget's content based on the specified alignment.
     * The content is padded with spaces to align it according to the alignment setting.
     * 
     * @param context The context used for building the widget, which provides the width to align the content.
     * @return A string representing the aligned content of the child widget.
     */
    @Override
    public String build(BuildContext context) {
        String renderChild = child.build(context);
        String[] renderChildLines = renderChild.split("\n");

        List<String> alignedLines = new ArrayList<String>();

        if (alignment == Alignment.CENTER) {
            for (String renderLine : renderChildLines) {
                int totalPadding = context.getWidth() - Widget.sanitiseRenderLine(renderLine).length();
                int paddingLeft = totalPadding / 2;
                int paddingRight = totalPadding - paddingLeft;

                String line = " ".repeat(paddingLeft) + renderLine + " ".repeat(paddingRight);
                alignedLines.add(line);
            }

        } else if (alignment == Alignment.END) {
            for (String renderLine : renderChildLines) {
                int paddingLeft = context.getWidth() - Widget.sanitiseRenderLine(renderLine).length();

                String line = " ".repeat(paddingLeft) + renderLine;
                alignedLines.add(line);
            }
        } else {
            for (String renderLine : renderChildLines) {
                int paddingRight = context.getWidth() - Widget.sanitiseRenderLine(renderLine).length();

                String line = renderLine + " ".repeat(paddingRight);
                alignedLines.add(line);
            }
        }

        return String.join("\n", alignedLines);
    }
    
}
