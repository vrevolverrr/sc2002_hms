package lib.uilib.framework.interfaces;

import java.util.function.*;

import lib.uilib.framework.BuildContext;

/**
 * The interface for a generic widget that takes multiple inputs and validates them
 * together against the given predicate until the combination of inputs is valid.
 * For the basic single input widget, see {@link InputWidget}.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-10-29
 */
public interface MultiInputWidget {
    /**
     * Paints the widget, then blocks the console to read all of required inputs sequentially
     * and validates them together against a given predicate, up until the combination of inputs
     * is valid.
     * @param context the context to use when building the widget.
     * @param predicate the multistring predicate to validate against.
     * @param failedMessage the message to display when the inputs are not valid.
     */
    public void readAll(BuildContext context, Predicate<String[]> predicate, String failedMessage);
} 