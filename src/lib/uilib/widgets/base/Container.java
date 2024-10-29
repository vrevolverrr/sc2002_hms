package lib.uilib.widgets.base;

import java.util.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.widgets.layout.Align;

public class Container extends Widget{
    private Integer width;
    private Integer height;
    private Alignment hAlignment;
    private Alignment vAlignment;
    private Border border;
    private Widget child;

    public Container(Widget child, int width, int height, Alignment hAlignment, Alignment vAlignment, Border border) {
        this.child = child;
        this.width = width;
        this.height = height;
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
        this.border = border;
    }

    public static class Builder {
        private Widget child;
        private Integer width;
        private Integer height;
        private Alignment hAlignment;
        private Alignment vAlignment;
        private Border border;
        private Boolean shrink;

        public Builder(Widget child) {
            this.child = child;
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

        public Builder setShrink(boolean shouldShrink) {
            this.shrink = shouldShrink;
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

        public Container buildContainer(BuildContext context) {
            if (this.width == null) {
                if (this.shrink) {
                    this.width = child.getWidth(context);
                } else {
                    this.width = context.getWidth();
                }
            }
    
            if (this.height == null) {
                if (this.shrink) {
                    this.height = child.getHeight(context);
                } else {
                    this.height = context.getHeight();
                }
            }

            return new Container(this.child, this.width, this.height, 
                    this.hAlignment == null ? Alignment.START : this.hAlignment,
                    this.vAlignment == null ? Alignment.START : this.vAlignment,
                    this.border == null ? Border.NONE : this.border);
        }
    }

    @Override
    public String build(BuildContext context) {
        BuildContext containerContext = new BuildContext(this.width, this.height);
        Widget alignedChild = new Align(this.hAlignment, child);
        String childLines = alignedChild.build(containerContext);

        ArrayList<String> renderLines = new ArrayList<String>();

        int childHeight = alignedChild.getHeight(containerContext);
        int topPadding;
        int bottomPadding;

        switch (vAlignment) {
            case Alignment.START:
                topPadding = 1;
                bottomPadding = this.height - childHeight - 1;
                break;
            
            case Alignment.CENTER:
                topPadding = (this.height - alignedChild.getHeight(containerContext)) / 2;
                bottomPadding = this.height - topPadding - alignedChild.getHeight(containerContext);
                break;

            case Alignment.END:
                topPadding = this.height - childHeight - 1;
                bottomPadding = 1;
                break;

            default:
                topPadding = 1;
                bottomPadding = this.height - childHeight;
            }

        for (int i = 0; i < topPadding; i++) {
            if (i == 0) {
                renderLines.add(
                    this.border.topLeft() + this.border.horizontal().repeat(containerContext.getWidth() - 2) + this.border.topRight());
            } else {
                renderLines.add(this.border.vertical() + " ".repeat(containerContext.getWidth() - 2) + this.border.vertical());
            }
        }

        for (String childLine : childLines.split("\n")) {
            if (this.hAlignment == Alignment.CENTER) {
                renderLines.add(
                    this.border.vertical() + childLine.substring(1, childLine.length() - 1) + this.border.vertical());
            } else if (this.hAlignment == Alignment.END) {
                renderLines.add(
                    this.border.vertical() + childLine.substring(2, childLine.length()) + this.border.vertical());
            } else {
                renderLines.add(
                    this.border.vertical() + childLine.substring(0, childLine.length() - 2) + this.border.vertical());
            } 
        }

        for (int i = 0; i < bottomPadding; i++) {
            if (i == bottomPadding - 1) {
                renderLines.add(
                    this.border.bottomLeft() + this.border.horizontal().repeat(containerContext.getWidth() - 2) + this.border.bottomRight());
            } else {
                renderLines.add(this.border.vertical() + " ".repeat(containerContext.getWidth() - 2) + this.border.vertical());
            }
        }

        return String.join("\n", renderLines);
    }
    
}
