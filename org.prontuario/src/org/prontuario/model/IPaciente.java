package org.prontuario.model;

import java.util.List;

public interface IPaciente {

	public Float calcularIMC();

	public Float calcularMetabolismoBasal();

	public Long getId();

	public void setId(Long id);

	public String getNome();

	public void setNome(String nome);

	public String getCpf();

	public void setCpf(String cpf);

	public List<Exame> getExames();

	public void setExames(List<Exame> exames);
}
