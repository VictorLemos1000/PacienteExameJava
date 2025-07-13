package crud.prontuario.exception;

public class PacienteExistenteException extends Exception{

	private static final long serialVersionUID = 1L;

	public PacienteExistenteException(String msg) {
		super(msg);
	}
}
