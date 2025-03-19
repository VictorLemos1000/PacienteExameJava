package exception;

public class DatabaseConectionException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public DatabaseConectionException(String message) {
        super(message);
    }

    public DatabaseConectionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DatabaseConectionException(Throwable cause) {
        super(cause);
    }
}
