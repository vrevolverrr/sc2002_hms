package lib.uilib.framework.enums;


/**
 * Enum representing the text style of a widget.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public enum TextStyle {
    /**
     * Represents the normal text style.
     */
    NORMAL("", ""),

    /**
     * Represents the bold text style.
     */
    BOLD("\u001B[1m", "\u001B[0m");
    
    /**
     * The sequence to start the text style.
     */
    private final String seqStart;

    /**
     * The sequence to end the text style.
     */
    private final String seqEnd;

    /**
     * Constructs a TextStyle object with the given start and end sequences.
     * 
     * @param seqStart the sequence to start the text style
     * @param seqEnd the sequence to end the text style
     */
    private TextStyle(String seqStart, String seqEnd) {
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
    }

    /**
     * Returns the sequence to start the text style.
     * 
     * @return the sequence to start the text style
     */
    public String seqStart() {
        return seqStart;
    }

    /**
     * Returns the sequence to end the text style.
     * 
     * @return the sequence to end the text style
     */
    public String seqEnd() {
        return seqEnd;
    }
}
