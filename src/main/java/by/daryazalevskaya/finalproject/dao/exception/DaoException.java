package by.daryazalevskaya.finalproject.dao.exception;


/**
 * DaoException is thrown when occured error with access to database
 */
public class DaoException extends Exception {
    /**
     * @inheritDoc
     */
    public DaoException() {
        super();
    }

    /**
     * @inheritDoc
     */
    public DaoException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public DaoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public DaoException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    protected DaoException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
