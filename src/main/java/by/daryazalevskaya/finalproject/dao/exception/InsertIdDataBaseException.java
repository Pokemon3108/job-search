package by.daryazalevskaya.finalproject.dao.exception;

/**
 * InsertIdDataBaseException is thrown when id wasn't generated after inserting in database
 */
public class InsertIdDataBaseException extends Exception {
    /**
     * @inheritDoc
     */
    public InsertIdDataBaseException() {
        super();
    }

    /**
     * @inheritDoc
     */
    public InsertIdDataBaseException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public InsertIdDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public InsertIdDataBaseException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    protected InsertIdDataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
