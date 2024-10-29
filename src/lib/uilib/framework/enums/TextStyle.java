package lib.uilib.framework.enums;

public enum TextStyle {
    NORMAL("", ""),
    BOLD("\u001B[1m", "\u001B[0m");
    
    private final String seqStart;
    private final String seqEnd;

    private TextStyle(String seqStart, String seqEnd) {
        this.seqStart = seqStart;
        this.seqEnd = seqEnd;
    }

    public String seqStart() {
        return seqStart;
    }

    public String seqEnd() {
        return seqEnd;
    }
}
