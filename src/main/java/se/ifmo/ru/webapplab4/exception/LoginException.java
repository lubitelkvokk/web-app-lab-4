package se.ifmo.ru.webapplab4.exception;

public class LoginException extends UserException{

    public LoginException() {
        super();
    }

    public LoginException(String message) {
        super(message);
    }

    public LoginException(Throwable exception) {
        super(exception);
    }
}
