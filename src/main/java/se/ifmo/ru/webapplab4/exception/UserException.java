package se.ifmo.ru.webapplab4.exception;

public class UserException extends Exception{

    public UserException() {
        super();
    }

    public UserException(String message) {
        super(message);
    }

    public UserException(Throwable exception) {
        super(exception);
    }

}
