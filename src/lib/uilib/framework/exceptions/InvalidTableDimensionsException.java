package lib.uilib.framework.exceptions;

public class InvalidTableDimensionsException extends RuntimeException {
    public InvalidTableDimensionsException(String err, Throwable e) {
        super(err, e);
    }
}
