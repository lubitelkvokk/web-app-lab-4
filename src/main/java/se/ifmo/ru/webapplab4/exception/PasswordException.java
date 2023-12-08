package se.ifmo.ru.webapplab4.exception;

public class PasswordException extends UserException{

    public PasswordException() {
        super();
    }

    public PasswordException(String message) {
        super(message);
    }

    public PasswordException(Throwable exception) {
        super(exception);
    }
}
