package by.daryazalevskaya.finalproject.service.exception;

public class IncorrectFormDataException extends Exception {
    public IncorrectFormDataException() {
        super();
    }

    public IncorrectFormDataException(String message) {
        super(message);
    }

    public IncorrectFormDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFormDataException(Throwable cause) {
        super(cause);
    }

    protected IncorrectFormDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
