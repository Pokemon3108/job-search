package by.daryazalevskaya.finalproject.dao.exception;

/**
 * PoolException throws when occures error in connection pool to database
 */
public class PoolException extends Exception {
    /**
     * @inheritDoc
     */
    public PoolException() {
    }

    /**
     * @inheritDoc
     */
    public PoolException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public PoolException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public PoolException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    public PoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
