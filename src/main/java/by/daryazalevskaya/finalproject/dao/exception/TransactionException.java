package by.daryazalevskaya.finalproject.dao.exception;

/**
 * TransactionException throws when occures error with database transaction
 */
public class TransactionException extends Exception {
    /**
     * @inheritDoc
     */
    public TransactionException() {
    }

    /**
     * @inheritDoc
     */
    public TransactionException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public TransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public TransactionException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    public TransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
