package crud.prontuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import crud.prontuario.database.IConnection;
import crud.prontuario.model.Paciente;

public class PacienteDAO implements IEntityDAO<Paciente>{

	private IConnection conn;
	
	public PacienteDAO(IConnection connection) {
		this.conn = connection;
		this.conn.criabd();
	}
	

	public void create(Paciente t) {
	    try {
	  

	        PreparedStatement pstm = conn.getConnection()
	            .prepareStatement("INSERT INTO PACIENTES (NOME, CPF) VALUES ( ?, ?);");
	        pstm.setString(1, t.getNome());
	        pstm.setString(2, t.getCpf());
	        pstm.execute();
	        pstm.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } 
	}


	/**
	 * O método retorna null se não encontrar o paciente e
	 * mapeia o resultado do BD para o objeto paciente.
	 */
	@Override
	public Paciente findById(Long id) {
		// TODO Auto-generated method stub
		Paciente p = null;
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?;");
			pstm.setLong(1, id);
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				p = new Paciente();
				p.setCpf(rs.getString("cpf"));
				p.setId(rs.getLong("id"));
				p.setNome(rs.getString("nome"));
			}
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	/* O método remove o paciente pelo ID,
	 * e não verifica se a operação foi bem sucedida.
	 */
	@Override
	public void delete(Paciente t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?;");
			pstm.setLong(1, t.getId());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// O método retorna uma lista vázia se não houver pacientes.
	// Usa o construtor do paciente  para criar os objetos.
	@Override
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		List<Paciente> pacientes = new ArrayList<Paciente>();
		try {
			PreparedStatement pstm = conn.getConnection()
					.prepareStatement("SELECT * FROM PACIENTES;");
			ResultSet rs = pstm.executeQuery();
			while(rs.next()) {
				pacientes.add(new Paciente
						(rs.getLong("id"),
						 rs.getString("nome"),
						 rs.getString("cpf")));
			}
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pacientes;
	}

	/*
	 * O método atualiza todos os campos do paciente,
	 * e usa o ID como critério da atualização.
	 */
	@Override
	public void update(Paciente t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("UPDATE PACIENTES SET NOME = ?, CPF = ? WHERE ID = ?;");
			pstm.setString(1, t.getNome());
			pstm.setString(2, t.getCpf());
			pstm.setLong(3, t.getId());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
