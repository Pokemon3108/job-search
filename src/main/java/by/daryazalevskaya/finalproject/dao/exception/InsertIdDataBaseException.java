package by.daryazalevskaya.finalproject.dao.exception;

public class InsertIdDataBaseException extends Exception {
    public InsertIdDataBaseException() {
        super();
    }

    public InsertIdDataBaseException(String message) {
        super(message);
    }

    public InsertIdDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public InsertIdDataBaseException(Throwable cause) {
        super(cause);
    }

    protected InsertIdDataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
