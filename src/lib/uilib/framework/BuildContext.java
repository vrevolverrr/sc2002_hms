package lib.uilib.framework;

/**
 * The context to use when building a widget. Essentially, it represents the 
 * width and height of the canvas the widget can work with when calculating 
 * its dimensions.
 * @see Widget
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */
public class BuildContext {
    /**
     * The width of the the context (1 unit = 1 char in the terminal).
     */
    private final int width;

    /**
     * The height of the context (1 unit = 1 line in the terminal).
     */
    private final int height;

    /**
     * The constructor of {@link BuildContext}.
     * @param width the width of the context in characters.
     * @param height the height of the context in lines.
     */
    public BuildContext(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a context with an finite width and unbounded (theoretically) height.
     * @param width the width of the context in characters.
     * @return a new context with the specified width and unbounded height.
     */
    public static BuildContext unboundedVertical(int width) {
        return new BuildContext(width, 999999999);
    }

    /**
     * Gets the width of the context.
     * @return the width of the context in characters.
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Gets the height of the context.
     * @return the height of the context in lines.
     */
    public int getHeight() {
        return this.height;
    }
}
