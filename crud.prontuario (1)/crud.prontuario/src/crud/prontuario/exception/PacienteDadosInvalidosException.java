package crud.prontuario.exception;

import java.io.Serializable;

public class PacienteDadosInvalidosException extends Exception{

	private static final long serialVersionUID = 1L;

	public PacienteDadosInvalidosException(String msg) {
		super(msg);
	}
}
