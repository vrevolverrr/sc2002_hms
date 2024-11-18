package services;

import java.util.*;

import view.View;

/**
 * The class that provides {@link View} navigation functionality.
 * 
 * <PRE>
 * Navigator.navigateTo(new LoginView());
 * </PRE>
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-28
 */
public class Navigator {
    /**
     * The view stack, which stores the logical history of {@code Views} that were navigated to.
     */
    private static List<View> stack = new ArrayList<View>();

    /**
     * The constructor is private since {@link Navigator} should be used statically.
     */
    private Navigator() {}

    /**
     * Navigates to the instance of {@link View} provided by painting its contents and
     * adding it to the view stack.
     * @param view
     */
    public static void navigateTo(View view) {
        stack.addLast(view);
        view.paint();
    }

    /**
     * Pops the latest {@link View} from the view stack and navigating back to the previous
     * {@link View} from the view stack. If the stack is empty when popping, the application
     * is exited.
     */
    public static void pop() {
        if (stack.size() <= 1) {
            System.exit(0);
        }

        stack.removeLast();
        stack.getLast().paint();
    }

    /**
     * Generate the logical history of {@code Views} (breadcrumbs) that were navigated to.
     * @return the formatted string of breadcrumbs.
     */
    public static String getBreadcrumbs() {
        return String.join(" > ", stack.stream()
            .map((view) -> view.getViewName()).filter(name -> name != "").toList());
    }
}
