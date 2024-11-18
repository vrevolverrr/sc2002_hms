package lib.uilib.framework.enums;

/**
 * Enum representing the border style of a widget.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public enum Border {
    /**
     * No border.
     */
    NONE("", "", "", "", "", ""),

    /**
     * Thin border.
     */
    THIN("\u2500", "\u2502", "\u250C", "\u2510", "\u2514", "\u2518"),

    /**
     * Thick border.
     */
    THICK("\u2501", "\u2503", "\u250F", "\u2513", "\u2517", "\u251B"),

    /**
     * Double border.
     */
    DOUBLE("\u2550", "\u2551", "\u2554", "\u2557", "\u255A", "\u255D"),

    /**
     * Dashed border.
     */
    DASHED_THIN("\u2504", "\u2506", "\u250C", "\u2510", "\u2514", "\u2518");
    
    /**
     * The horizontal line character.
     */
    private final String horizontal;

    /**
     * The vertical line character.
     */
    private final String vertical;

    /**
     * The top left corner character.
     */
    private final String topLeft;

    /**
     * The top right corner character.
     */
    private final String topRight;

    /**
     * The bottom left corner character.
     */
    private final String bottomLeft;

    /**
     * The bottom right corner character.
     */
    private final String bottomRight;

    /**
     * Creates a new border style.
     * 
     * @param horizontal The horizontal line character.
     * @param vertical The vertical line character.
     * @param topLeft The top left corner character.
     * @param topRight The top right corner character.
     * @param bottomLeft The bottom left corner character.
     * @param bottomRight The bottom right corner character.
     */
    private Border(String horizontal, String vertical, 
                    String topLeft, String topRight, 
                    String bottomLeft, String bottomRight) {
                        
        this.horizontal = horizontal;
        this.vertical = vertical;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.bottomLeft = bottomLeft;
        this.bottomRight = bottomRight;
    }

    /**
     * Gets the horizontal line character.
     * 
     * @return The horizontal line character.
     */
    public String horizontal() {
        return this.horizontal;
    }    

    /**
     * Gets the vertical line character.
     * 
     * @return The vertical line character.
     */
    public String vertical() {
        return this.vertical;
    }

    /**
     * Gets the top left corner character.
     * 
     * @return The top left corner character.
     */
    public String topLeft() {
        return this.topLeft;
    }

    /**
     * Gets the top right corner character.
     * 
     * @return The top right corner character.
     */
    public String topRight() {
        return this.topRight;
    }

    /**
     * Gets the bottom left corner character.
     * 
     * @return The bottom left corner character.
     */
    public String bottomLeft() {
        return this.bottomLeft;
    }

    /**
     * Gets the bottom right corner character.
     * 
     * @return The bottom right corner character.
     */
    public String bottomRight() {
        return this.bottomRight;
    }
}
