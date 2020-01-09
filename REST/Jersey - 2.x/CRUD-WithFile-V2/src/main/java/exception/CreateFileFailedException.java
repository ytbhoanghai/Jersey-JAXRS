package exception;

public class CreateFileFailedException extends RuntimeException {

    public CreateFileFailedException(String message) {
        super(message);
    }
}
