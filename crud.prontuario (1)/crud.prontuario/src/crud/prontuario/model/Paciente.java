package crud.prontuario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*
 *  Esta classe atribui informações sobre algum individuo que realizou
 *  um exame no hospital ou unidade básica de saúde.  
 */
public class Paciente {

	// Atributo faz a identificação de um determinado paciente.
	private Long id;
	
	// Identifica nome e cpf do paciente.
	private String nome;
	private String cpf;
	
	// Lista para atribuir exames do paciente.
	private List<Exame> exames = new ArrayList<Exame>();

	public Paciente() {
	}

	public Paciente(Long id, String nome, String cpf) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Exame> getExames() {
		return exames;
	}

	public void setExames(List<Exame> exames) {
		this.exames = exames;
	}

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", exames=" + exames + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Paciente other = (Paciente) obj;
		return Objects.equals(cpf, other.cpf);
	}
}
