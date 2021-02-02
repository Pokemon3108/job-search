package by.daryazalevskaya.finalproject.dao.exception;

/**
 * NoEntityInDataBaseException throws when database doesn't contains requested object
 */
public class NoEntityInDataBaseException extends Exception {
    /**
     * @inheritDoc
     */
    public NoEntityInDataBaseException() {
        super();
    }

    /**
     * @inheritDoc
     */
    public NoEntityInDataBaseException(String message) {
        super(message);
    }

    /**
     * @inheritDoc
     */
    public NoEntityInDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @inheritDoc
     */
    public NoEntityInDataBaseException(Throwable cause) {
        super(cause);
    }

    /**
     * @inheritDoc
     */
    protected NoEntityInDataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
