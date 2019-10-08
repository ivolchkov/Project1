package project.service.exception;

public class InvalidRangeException extends RuntimeException {
    public InvalidRangeException() {
    }

    public InvalidRangeException(String message) {
        super(message);
    }

    public InvalidRangeException(Throwable cause) {
        super(cause);
    }
}
