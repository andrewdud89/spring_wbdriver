package base.core.exception;

public class AutomatronException extends Exception {

    public AutomatronException() {
    }

    public AutomatronException(String message) {
        super(message);
    }

    public AutomatronException(String message, Throwable cause) {
        super(message, cause);
    }

    public AutomatronException(Throwable cause) {
        super(cause);
    }

    public AutomatronException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
