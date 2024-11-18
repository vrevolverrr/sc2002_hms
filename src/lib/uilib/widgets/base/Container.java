package lib.uilib.widgets.base;

import java.util.*;

import lib.uilib.framework.BuildContext;
import lib.uilib.framework.Widget;
import lib.uilib.framework.enums.Alignment;
import lib.uilib.framework.enums.Border;
import lib.uilib.widgets.layout.Align;


/**
 * A widget that contains another widget and aligns it within the container.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class Container extends Widget{
    /**
     * The width of the container.
     */
    private Integer width;

    /**
     * The height of the container.
     */
    private Integer height;

    /**
     * The horizontal alignment of the child widget.
     */
    private Alignment hAlignment;

    /**
     * The vertical alignment of the child widget.
     */
    private Alignment vAlignment;

    /**
     * The border of the container.
     */
    private Border border;

    /**
     * The child widget.
     */
    private Widget child;

    /**
     * Constructs a new container widget. 
     * 
     * @param child The child widget.
     * @param width The width of the container.
     * @param height The height of the container.
     * @param hAlignment The horizontal alignment of the child widget.
     * @param vAlignment The vertical alignment of the child widget.
     * @param border The border of the container.
     */
    public Container(Widget child, int width, int height, Alignment hAlignment, Alignment vAlignment, Border border) {
        this.child = child;
        this.width = width;
        this.height = height;
        this.hAlignment = hAlignment;
        this.vAlignment = vAlignment;
        this.border = border;
    }

    /**
     * A builder class for the container widget.
     */
    public static class Builder {
        private Widget child;
        private Integer width;
        private Integer height;
        private Alignment hAlignment;
        private Alignment vAlignment;
        private Border border;
        private Boolean shrink;

        /**
         * Constructs a new builder for the container widget.
         * 
         * @param child The child widget.
         */
        public Builder(Widget child) {
            this.child = child;
        }

        /**
         * Sets the width of the container.
         * 
         * @param width The width of the container.
         * @return The builder instance.
         */
        public Builder setWidth(int width) {
            this.width = width;
            return this;
        }

        /**
         * Sets the height of the container.
         * 
         * @param height The height of the container.
         * @return The builder instance.
         */
        public Builder setHeight(int height) {
            this.height = height;
            return this;
        }

        /**
         * Sets the border of the container.
         * 
         * @param border The border of the container.
         * @return The builder instance.
         */
        public Builder setBorder(Border border) {
            this.border = border;
            return this;
        }

        /**
         * Sets whether the container should shrink to fit the child widget.
         * 
         * @param shouldShrink Whether the container should shrink.
         * @return The builder instance.
         */
        public Builder setShrink(boolean shouldShrink) {
            this.shrink = shouldShrink;
            return this;
        }

        /**
         * Sets the horizontal alignment of the child widget.
         * 
         * @param alignment The horizontal alignment of the child widget.
         * @return The builder instance.
         */
        public Builder setHorizontalAlignment(Alignment alignment) {
            this.hAlignment = alignment;
            return this;
        }

        /**
         * Sets the vertical alignment of the child widget.
         * 
         * @param alignment The vertical alignment of the child widget.
         * @return The builder instance.
         */
        public Builder setVerticalAlignment(Alignment alignment) {
            this.vAlignment = alignment;
            return this;
        }

        /**
         * Builds the container widget.
         * 
         * @param context The build context.
         * @return The container widget.
         */
        public Container buildContainer(BuildContext context) {
            if (this.width == null) {
                if (this.shrink) {
                    this.width = child.getWidth(context);
                } else {
                    this.width = context.getWidth();
                }
            }
    
            if (this.height == null) {
                if (this.shrink == null || this.shrink) {
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

    /**
     * Builds the container widget.
     */
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
