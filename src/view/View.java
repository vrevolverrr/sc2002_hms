package view;

public abstract class View {
    public abstract String getViewName();
    public abstract void render();

    public void paint() {
        clear();
        this.render();
    }
    
    public static void clear() {
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
    }

    public static void gotoPrevLine() {
        System.out.print(String.format("\033[1A"));
    }

    public static void gotoPrevNthLine(int n) {
        for (int i = 0; i < n; i++) {
            gotoPrevLine();
        }
    }

    public static void clearLine() {
        System.out.print(String.format("\033[1A"));
        System.out.print("\033[2K");
    }

    public static void clearLines(int count) {
        for (int i = 0; i < count; i++) {
            clearLine();
        }
    }
}
