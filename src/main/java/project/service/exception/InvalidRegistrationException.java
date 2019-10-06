package project.service.exception;

public class InvalidRegistrationException extends RuntimeException {
    public InvalidRegistrationException(String s) {
        super(s);
    }

    public InvalidRegistrationException(Throwable throwable) {
        super(throwable);
    }
}
