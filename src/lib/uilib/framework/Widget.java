package lib.uilib.framework;

import lib.uilib.framework.enums.TextStyle;

public abstract class Widget {
    private Integer width;
    private Integer height;
    private BuildContext context;

    public abstract String build(BuildContext context);

    public void paint(BuildContext context) {
        System.out.println(this.build(context));
    }
    
    public int getWidth(BuildContext context) {
        if (this.width == null || context != this.context) {
           calculateDimensions(context);
        }

        return this.width;
    }

    public int getHeight(BuildContext context) {
        if (this.height == null || context != this.context) {
            calculateDimensions(context);;
        }

        return this.height;
    }

    private void calculateDimensions(BuildContext context) {
        this.context = context;

        String renderChild = this.build(context);
        String[] renderChildLines = renderChild.split("\n");

        this.height = renderChildLines.length - 1;
        
        int width = 0;

        for (String renderLine : renderChildLines) {
            renderLine = sanitiseRenderLine(renderLine);

            int renderLineWidth = renderLine.length();

            if (renderChild.length() > width) {
                width = renderLineWidth;
            }
        }

        this.width = width;
    }
    
    protected static String sanitiseRenderLine(String renderLine) {
        String sanitisedLine = renderLine;

        for (TextStyle style : TextStyle.values()) {
            sanitisedLine = sanitisedLine.replace(style.seqStart(), "");
            sanitisedLine = sanitisedLine.replace(style.seqEnd(), "");
        }

        return sanitisedLine;
    }
}
