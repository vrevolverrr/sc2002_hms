package services;

import java.util.*;

import view.View;

public class Navigator {
    private static List<View> stack = new ArrayList<View>();

    public static void navigateTo(View view) {
        stack.addLast(view);
        view.paint();
    }

    public static void pop() {
        stack.removeLast();
    }

    public static String getBreadcrumbs() {
        return String.join(" > ", stack.stream().map((view) -> view.getViewName()).toList());
    }
}
