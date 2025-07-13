package crud.prontuario.exception;

public abstract class BaseException extends Exception implements IException {
    
    private static final long serialVersionUID = 1L;

    protected BaseException(String msg) {
        super(msg);
    }
}
