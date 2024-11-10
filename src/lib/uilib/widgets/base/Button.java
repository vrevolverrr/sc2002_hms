package lib.uilib.widgets.base;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;

public class Button extends Widget {
    private Integer width;
    private Integer height;
    private String text;
    private Alignment hAlignment;
    private Alignment vAlignment;
    private Border border;

    public Button(String text, int width, int height, Alignment hAlignment, Alignment vAlignment, Border border) {
        this.text = text;
        this.width = width;
        this.height = height;
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
        this.border = border;
    }

    public static class Builder {
        private String text;
        private Integer width;
        private Integer height;
        private Alignment hAlignment;
        private Alignment vAlignment;
        private Border border;

        public Builder(String text) {
            this.text = text;
        }

        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        public Builder setBorder(Border border) {
            this.border = border;
            return this;
        }

        public Builder setHorizontalAlignment(Alignment alignment) {
            this.hAlignment = alignment;
            return this;
        }

        public Builder setVerticalAlignment(Alignment alignment) {
            this.vAlignment = alignment;
            return this;
        }

        public Button buildButton(BuildContext context) {
            if (this.width == null) {
                this.width = context.getWidth();
            }

            if (this.height == null) {
                this.height = context.getHeight();
            }

            return new Button(this.text, this.width, this.height, 
                    this.hAlignment == null ? Alignment.CENTER : this.hAlignment,
                    this.vAlignment == null ? Alignment.CENTER : this.vAlignment,
                    this.border == null ? Border.NONE : this.border);
        }
    }

    @Override
    public String build(BuildContext context) {
        BuildContext buttonContext = new BuildContext(this.width, this.height);

        // Align the button text
        String alignedText = alignText(buttonContext);

        // Generate the button's visual representation
        String topBottomBorder = this.border.horizontal().repeat(buttonContext.getWidth());
        String sideBorder = this.border.vertical();

        // Top Border
        StringBuilder render = new StringBuilder();
        render.append(this.border.topLeft() + topBottomBorder + this.border.topRight() + "\n");

        // Middle content with text, vertical alignment will adjust the line spacing
        render.append(getVerticalPadding(buttonContext));
        render.append(sideBorder + alignedText + sideBorder + "\n");
        render.append(getVerticalPadding(buttonContext));

        // Bottom Border
        render.append(this.border.bottomLeft() + topBottomBorder + this.border.bottomRight());

        return render.toString();
    }

    private String alignText(BuildContext context) {
        int padding = this.width - this.text.length() - 2;  // Subtracting borders
        int leftPadding = 0;
        int rightPadding = 0;

        // Truncate text if it exceeds the width (with "..." at the end)
        if (this.text.length() > this.width - 2) {
            this.text = this.text.substring(0, this.width - 5) + "...";
        }

        // Horizontal alignment logic (center, end, start)
        switch (this.hAlignment) {
            case CENTER:
                leftPadding = padding / 2;
                rightPadding = padding - leftPadding;
                break;
            case END:
                leftPadding = padding;
                break;
            default:
                break;
        }

        StringBuilder alignedText = new StringBuilder(" ".repeat(leftPadding));
        alignedText.append(this.text);
        alignedText.append(" ".repeat(rightPadding));

        return alignedText.toString();
    }

    private String getVerticalPadding(BuildContext context) {
        int topPadding = 0;
        int bottomPadding = 0;

        // Vertical alignment logic (top, center, bottom)
        switch (this.vAlignment) {
            case CENTER:
                topPadding = (this.height - 1) / 2;
                bottomPadding = this.height - 1 - topPadding;
                break;
            case END:
                topPadding = this.height - 1;
                break;
            default:
                break;
        }

        // Return the appropriate amount of padding based on vertical alignment
        return " ".repeat(topPadding) + "\n" + " ".repeat(bottomPadding) + "\n";
    }
}
