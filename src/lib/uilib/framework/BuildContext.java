/**
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */

package lib.uilib.framework;

/**
 * The context to use when building a widget. Essentially, it represents the 
 * width and height of the canvas the widget can work with when calculating 
 * its dimensions.
 * @see Widget
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
     * @param width
     * @param height
     */
    public BuildContext(int width, int height) {
        this.width = width;
        this.height = height;
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
