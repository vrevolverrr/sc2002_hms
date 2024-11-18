package lib.uilib.framework.exceptions;

/**
 * Exception thrown when the table dimensions are invalid. This class extends
 * the RuntimeException class.
 * 
 * @author Bryan Soong
 * @version 1.0
 * @since 2024-11-10
 */

public class InvalidTableDimensionsException extends RuntimeException {
    /**
     * Constructor for the exception
     * 
     * @param err the error message.
     * @param e the exception.
     */
    public InvalidTableDimensionsException(String err, Throwable e) {
        super(err, e);
    }
}
