package exception;

public class ExameVinculadoException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ExameVinculadoException(String message) {
        super(message);
    }

    public ExameVinculadoException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExameVinculadoException(Throwable cause) {
        super(cause);
    }
}
