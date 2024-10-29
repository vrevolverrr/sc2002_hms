package lib.uilib.framework.enums;

public enum Border {
    NONE("", "", "", "", "", ""),
    THIN("\u2500", "\u2502", "\u250C", "\u2510", "\u2514", "\u2518"),
    THICK("\u2501", "\u2503", "\u250F", "\u2513", "\u2517", "\u251B"),
    DOUBLE("\u2550", "\u2551", "\u2554", "\u2557", "\u255A", "\u255D"),
    DASHED_THIN("\u2504", "\u2506", "\u250C", "\u2510", "\u2514", "\u2518");
    
    private final String horizontal;
    private final String vertical;
    private final String topLeft;
    private final String topRight;
    private final String bottomLeft;
    private final String bottomRight;

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

    public String horizontal() {
        return this.horizontal;
    }    

    public String vertical() {
        return this.vertical;
    }

    public String topLeft() {
        return this.topLeft;
    }

    public String topRight() {
        return this.topRight;
    }

    public String bottomLeft() {
        return this.bottomLeft;
    }

    public String bottomRight() {
        return this.bottomRight;
    }
}
