package se.ifmo.ru.webapplab4.exception;

public class HitBoundaryException extends Exception{
    public HitBoundaryException() {
        super();
    }

    public HitBoundaryException(String message) {
        super(message);
    }

    public HitBoundaryException(Throwable exception) {
        super(exception);
    }
}
