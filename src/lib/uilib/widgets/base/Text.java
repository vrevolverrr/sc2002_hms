package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.TextStyle;

public class Text extends Widget {
    final String text;
    final TextStyle style;

    public Text(String text) {
        this.text = text;
        this.style = TextStyle.NORMAL;
    }

    public Text(String text, TextStyle style) {
        this.text = text;
        this.style = style;
    }
    
    @Override
    public String build(BuildContext context) {
        return this.style.seqStart() + truncateString(context.getWidth()) + this.style.seqEnd();
    }

    private String truncateString(int maxLength) {
        if (this.text.length() > maxLength) {
            return this.text.substring(0, maxLength - 3) + "...";
        }

        return this.text;
    }
}
