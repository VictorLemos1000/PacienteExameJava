package crud.prontuario.exception;

public class PacienteNaoEncontradoException extends Exception{

	private static final long serialVersionUID = 1L;

	public PacienteNaoEncontradoException(String msg) {
		super(msg);
	}
}
