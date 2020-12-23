package by.daryazalevskaya.finalproject.dao.exception;

public class NoEntityInDataBaseException extends Exception {
    public NoEntityInDataBaseException() {
        super();
    }

    public NoEntityInDataBaseException(String message) {
        super(message);
    }

    public NoEntityInDataBaseException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoEntityInDataBaseException(Throwable cause) {
        super(cause);
    }

    protected NoEntityInDataBaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
