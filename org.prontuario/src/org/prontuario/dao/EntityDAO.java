package org.prontuario.dao;

import java.sql.SQLException;
import java.util.List;

import org.prontuario.exception.PacienteNaoEncontradoException;

public interface EntityDAO <Entidade>{

	void save(Entidade e);
	Entidade findById(Long id) throws PacienteNaoEncontradoException;
	void update(Entidade e);
	void delete(Entidade e);
	List<Entidade> findAll() throws SQLException;
}
