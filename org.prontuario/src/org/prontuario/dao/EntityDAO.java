package org.prontuario.dao;

import java.util.List;

import org.prontuario.exception.PacienteNaoEncontradoException;

public interface EntityDAO <E>{

	void save(E e);
	E findById(Long id) throws PacienteNaoEncontradoException;
	void update(E e);
	void delete(E e);
	List<E> findAll();
}
