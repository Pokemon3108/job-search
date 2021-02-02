package by.daryazalevskaya.finalproject.dao.exception;


/**
 * The type Connection exception. It is thrown when happens error with database connection
 */
public class ConnectionException extends Exception {
    /**
     * @inheritDoc
     */
    public ConnectionException() {
    }

    /**
     * @inheritDoc
     */
    public ConnectionException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public ConnectionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public ConnectionException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    public ConnectionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
