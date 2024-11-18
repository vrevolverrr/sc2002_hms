package lib.uilib.framework.interfaces;

import java.util.function.*;

import lib.uilib.framework.BuildContext;

/**
 * The interface of a generic widget that continuously reads an input and validates 
 * it against the given predicate until the input is valid.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */
public interface InputWidget {
    /**
     * Paints the widget, then blocks the console to read the input and validate it against 
     * the predicate, up until the input is valid.
     * 
     * @param context the context to use when building the widget.
     * @param predicate the predicate to validate against.
     */
    public void read(BuildContext context, Predicate<String> predicate);
}
