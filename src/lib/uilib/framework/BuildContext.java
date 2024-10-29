package lib.uilib.framework;

public class BuildContext {
    final private int width;
    final private int height;

    public BuildContext(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }
}
