package se.ifmo.ru.webapplab4.exception;

public class RegistrationException extends Exception {

    public RegistrationException() {
        super();
    }

    public RegistrationException(String message) {
        super(message);
    }

    public RegistrationException(Throwable exception) {
        super(exception);
    }
}
