package exception.person;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String message) {
        super(message);
        System.out.println("object sjjgg");
    }
    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
