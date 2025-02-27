package org.prontuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.prontuario.db.ConexaoMySQL;
import org.prontuario.db.IConnection;
import org.prontuario.exception.PacienteNaoEncontradoException;
import org.prontuario.model.IPaciente;
import org.prontuario.model.PacienteFeminino;
import org.prontuario.model.PacienteMasculino;

public class PacienteDAO implements EntityDAO<IPaciente>{

	private IConnection conn;
	
	public PacienteDAO(IConnection c) {
		this.conn = c;
	}
	
	@Override
	public void save(IPaciente e) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO PACIENTES VALUES (?, ?, ?, ?);";
		
		if (e instanceof PacienteMasculino) {
			try(PreparedStatement ptst = conn.getConnection().prepareStatement(sql)){
				ptst.setLong(1, e.getId());
				ptst.setString(2, e.getNome());
				ptst.setString(3, e.getCpf());
				ptst.setString(4, "Masculino");
				ptst.execute();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e instanceof PacienteFeminino) {
			try(PreparedStatement ptst = conn.getConnection().prepareStatement(sql)){
				ptst.setLong(1, e.getId());
				ptst.setString(2, e.getNome());
				ptst.setString(3, e.getCpf());
				ptst.setString(4, "Feminino");
				ptst.execute();
				
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
	}

	@Override
	public IPaciente findById(Long id) throws PacienteNaoEncontradoException{
		// TODO Auto-generated method stub
		String sqlM = "SELECT * FROM PACIENTES P WHERE P.IDPACIENTES = ? and P.SEXO = 'm';";
		String sqlF = "SELECT * FROM PACIENTES P WHERE P.IDPACIENTES = ? and P.SEXO = 'f';";
		
		ResultSet rs;
		IPaciente p = null;
		
		try (PreparedStatement pstm = conn.getConnection().prepareStatement(sqlM)) {
			pstm.setLong(1, id);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String nome = rs.getString("nome");
				String cpf = rs.getString("cpf");
				p = new PacienteMasculino(id, nome, cpf);
			} else {
				try (PreparedStatement pstmF = conn.getConnection().prepareStatement(sqlF)) {
					pstmF.setLong(1, id);
					rs = pstmF.executeQuery();
					if(rs.next()) {
						String nome = rs.getString("nome");
						String cpf = rs.getString("cpf");
						p = new PacienteFeminino(id, nome, cpf);
						return p;
					}
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}catch(SQLException e1) {
			e1.printStackTrace();
		}
		if(p == null)
			throw new PacienteNaoEncontradoException("Paciente nao encontrado");
		
		return p;
	}

	@Override
	public void update(IPaciente e) {
		// TODO Auto-generated method stub
		String sqlUpdate = "UPDATE PACIENTES SET NOME = ?, CPF = ? WHERE ID = ?;";
		
		try (PreparedStatement pstm = conn.getConnection().prepareStatement(sqlUpdate)) {
			pstm.setString(1, e.getNome());
			pstm.setString(2, e.getCpf());
			pstm.setLong(3, e.getId());
			
			int linhaAfetada = pstm.executeUpdate();
			if (linhaAfetada == 0) {
				throw new PacienteNaoEncontradoException(" Paciente não localizado para atualização.");
			}
		} catch (SQLException | PacienteNaoEncontradoException excecao) {
			// TODO: handle exception
			excecao.printStackTrace();
		}
	}

	@Override
	public void delete(IPaciente e) {
		// TODO Auto-generated method stub
		String sqlDelete = "DELETE FROM PACIENTES WHERE ID = ?;";
		
		try (PreparedStatement pstm = conn.getConnection().prepareStatement(sqlDelete)) {
			pstm.setLong(1, e.getId());
			
			int linhaAfetada = pstm.executeUpdate();
			if (linhaAfetada == 0) {
				throw new PacienteNaoEncontradoException(" Paciente não localizado para remoção.");
			}
		} catch (Exception exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
	}

	@Override
	public List<IPaciente> findAll() throws SQLException{
		// TODO Auto-generated method stub
		List<IPaciente> pacientes = new ArrayList<>();
		String sqlLocalizarPacientes = "SELECT * FROM PACIENTES";
		
		try (PreparedStatement pstm = conn.getConnection().prepareStatement(sqlLocalizarPacientes);
				ResultSet resultSet = pstm.executeQuery()) {
			
		}
		return null;
	}

}
