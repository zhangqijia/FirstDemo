package week0.exception;

public class TooCoolException extends TempertureException {

    public TooCoolException() {
    }

    public TooCoolException(String message) {
        super(message);
    }
}
