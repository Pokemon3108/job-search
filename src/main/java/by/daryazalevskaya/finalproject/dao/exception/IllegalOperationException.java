package by.daryazalevskaya.finalproject.dao.exception;

/**
 * IllegalOperationException is thrown when user tries to run method, that isn't allowed to be executed
 */
public class IllegalOperationException extends RuntimeException {
    /**
     * @inheritDoc
     */
    public IllegalOperationException() {
    }

    /**
     * @inheritDoc
     */
    public IllegalOperationException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public IllegalOperationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public IllegalOperationException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    public IllegalOperationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
