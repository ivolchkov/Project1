package project.service.exception;

public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String s) {
        super(s);
    }

    public StudentNotFoundException(Throwable throwable) {
        super(throwable);
    }
}
