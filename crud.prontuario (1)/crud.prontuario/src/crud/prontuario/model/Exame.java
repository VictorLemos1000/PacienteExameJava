package crud.prontuario.model;

import java.time.LocalDateTime;
import java.util.Objects;

// A classe guarda informações importantes sobre algum exame médico.
public class Exame {

	// O id é o atributo identificador da classe.
	private Long id;
	
	// a descriação serve para descrever qual o laudo do exame do paciente.
	private String descricao; 
	
	// a data determina dia e horário em que o exame foi ou será realizado.
	private LocalDateTime data;
	
	// Métodos construtores implícitos e explícitos.
	public Exame() {
		super();
	}

	public Exame(Long id, String descricao, LocalDateTime data) {
		super();
		this.id = id;
		this.descricao = descricao;
		this.data = data;
	}

	// métodos seters e geters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	// Método que evita que o exame exiba uma hash para um usuário final.
	@Override
	public String toString() {
		return "Exame [id=" + id + ", descricao=" + descricao + ", data=" + data + "]";
	}

	// Métodos que comparam exames
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Exame other = (Exame) obj;
		return Objects.equals(id, other.id);
	}
}
