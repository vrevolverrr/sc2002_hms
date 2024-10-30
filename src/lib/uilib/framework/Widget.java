package lib.uilib.framework;

import lib.uilib.framework.enums.TextStyle;

/**
 * The abstract base class that represents the description of part of the user interface. 
 * Widgets can be composed together to build declarative user interfaces.
 */
public abstract class Widget {
    /**
     * The calculated width of the widget.
     */
    private Integer width;

    /**
     * The calculated height of the widget.
     */
    private Integer height;

    /**
     * The context surrounding the widget that was last used to calculate the 
     * width and height, which essentially tells the widget how much space it 
     * has to work with.
     * @see BuildContext
     */
    private BuildContext context;

    /**
     * Describes the part of the widget represented by this widget. "Building" refers
     * to constructing the actual text {@code String} to be printed onto the console, 
     * given the current {@link BuildContext}.
     * @param context the context to build the widget with respect to.
     * @return the built text string described by the widget.
     */
    public abstract String build(BuildContext context);

    /**
     * Paints (prints) the widget's description of the user interface onto the console.
     * Basically, prints the built string obtained by calling {@link Widget#build()}.
     * @param context the context to build the widget with respect to.
     */
    public void paint(BuildContext context) {
        System.out.println(this.build(context));
    }
    
    /**
     * Gets the calculated width of the widget given the context. 
     * This is done by measuring the maximum line width of the built string.
     * @param context the context to build the widget with respect to.
     * @return the calculated width of the widget.
     */
    public int getWidth(BuildContext context) {
        if (this.width == null || context != this.context) {
           calculateDimensions(context);
        }

        return this.width;
    }

    /**
     * Gets the calculated height of the widget given the context. 
     * This is done by measuring the number of lines of the built string.
     * @param context the context to build the widget with respect to.
     * @return the calculated height of the widget.
     */
    public int getHeight(BuildContext context) {
        if (this.height == null || context != this.context) {
            calculateDimensions(context);;
        }

        return this.height;
    }

    /**
     * The actual method that builts the widget and calculates its width and height.
     * @param context the context to build the widget with respect to.
     */
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
    
    /**
     * A utility method to remove ANSI escape sequences from the built string as they 
     * interfere with the calculation of width and height.
     * These escape sequences are used to render effects like color, text styles etc.
     * @param renderLine the built string to sanitise.
     * @return the sanitised string.
     */
    protected static String sanitiseRenderLine(String renderLine) {
        String sanitisedLine = renderLine;

        for (TextStyle style : TextStyle.values()) {
            sanitisedLine = sanitisedLine.replace(style.seqStart(), "");
            sanitisedLine = sanitisedLine.replace(style.seqEnd(), "");
        }

        return sanitisedLine;
    }
}
