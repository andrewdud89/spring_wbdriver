package base.core.exception;

public class CarsException extends AutomatronException {

    public CarsException() {
    }

    public CarsException(String message) {
        super(message);
    }

    public CarsException(String message, Throwable cause) {
        super(message, cause);
    }

    public CarsException(Throwable cause) {
        super(cause);
    }

    public CarsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
