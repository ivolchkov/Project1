package project.service.exception;

public class InvalidEncodingException extends RuntimeException {
    public InvalidEncodingException() {
    }

    public InvalidEncodingException(String message) {
        super(message);
    }

    public InvalidEncodingException(Throwable cause) {
        super(cause);
    }
}
