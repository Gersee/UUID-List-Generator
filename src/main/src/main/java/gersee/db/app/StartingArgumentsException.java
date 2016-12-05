package gersee.db.app;

/**
 * Exception for wrong starting arguments
 * Created by Gersee on 22.11.16.
 */
public class StartingArgumentsException extends Exception {
    public StartingArgumentsException() {
        super();
    }

    public StartingArgumentsException(String message) {
        super(message);
    }

    public StartingArgumentsException(String message, Throwable cause) {
        super(message, cause);
    }

    public StartingArgumentsException(Throwable cause) {
        super(cause);
    }

    public StartingArgumentsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
