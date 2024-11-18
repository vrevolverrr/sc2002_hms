package lib.uilib.framework;

/**
 * The interface for a menu action.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */
@FunctionalInterface
public interface MenuAction {

    /**
     * The method to run when the menu action is selected.
     */
    public abstract void run();
} 
