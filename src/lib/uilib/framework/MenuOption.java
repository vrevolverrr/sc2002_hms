package lib.uilib.framework;

public class MenuOption {
    private final String title;
    private final MenuAction action;
    
    public MenuOption(String title, MenuAction action) {
        this.title = title;
        this.action = action;
    }

    public String getTitle() {
        return this.title;
    }

    public MenuAction getAction() {
        return this.action;
    }
}