package lib.uilib.framework;

/**
 * Represents a menu option in a menu.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
public class MenuOption {
    /**
     * The title of the menu option.
     */
    private final String title;

    /**
     * The action to run when the menu option is selected.
     */
    private final MenuAction action;
    
    /**
     * The constructor of {@link MenuOption}.
     * 
     * @param title the title of the menu option.
     * @param action the action to run when the menu option is selected.
     */
    public MenuOption(String title, MenuAction action) {
        this.title = title;
        this.action = action;
    }

    /**
     * Gets the title of the menu option.
     * 
     * @return the title of the menu option.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Gets the action of the menu option.
     * 
     * @return the action of the menu option.
     */
    public MenuAction getAction() {
        return this.action;
    }
}