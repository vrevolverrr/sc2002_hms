package lib.uilib.widgets.layout;

import java.util.ArrayList;
import java.util.List;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;

public class Align extends Widget {
    final private Alignment alignment;
    final private Widget child;

    public Align(Alignment alignment, Widget child) {
        this.alignment = alignment;
        this.child = child;
    }

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
