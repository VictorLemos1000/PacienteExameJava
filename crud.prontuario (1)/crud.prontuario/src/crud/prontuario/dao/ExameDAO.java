package crud.prontuario.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import crud.prontuario.database.IConnection;
import crud.prontuario.model.Exame;

public class ExameDAO implements IEntityDAO<Exame>{

	private IConnection conn;
	
	// Construtor da classe.
	public ExameDAO(IConnection connection) {
		this.conn = connection;
	}
	
	// Método para criar um exame em um BD.
	@Override
	public void create(Exame t) {
		// TODO Auto-generated method stub
		if (t.getDescricao() == null || t.getData() == null) {
			throw new IllegalArgumentException("\n Descrição e data são campos obrigatórios.");
		}
	
		try {
			PreparedStatement pstm = conn.getConnection()
					.prepareStatement("INSERT INTO EXAMES VALUES(?, ?, ?);");
			pstm.setLong(1, t.getId());
			pstm.setString(2, t.getDescricao());
			pstm.setObject(3, t.getData());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	@Override
	public Exame findById(Long id) {
		// TODO Auto-generated method stub
		Exame e = null;
		
		try {
			PreparedStatement pstm = conn.getConnection()
					.prepareStatement("SELECT * FROM EXAMES WHERE id = ?;");
			pstm.setLong(1, id);
			ResultSet rs = pstm.executeQuery();
			
			if (rs.next()) {
				e = new Exame();
				e.setId(rs.getLong("id"));
				e.setDescricao(rs.getString("descricao"));
				e.setData(rs.getObject("data", LocalDateTime.class));
			}
			pstm.close();
		} catch (SQLException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		return e;
	}

	// Método para apagar um exame em um BD.
	@Override
	public void delete(Exame t) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("DELETE FROM EXAMES WHERE id = ?;");
			pstm.setLong(1, t.getId());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	// Realiza a listagem dos exames
	@Override
	public List<Exame> findAll() {
		// TODO Auto-generated method stub
		List<Exame> exames = new ArrayList<>();
		
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("SELECT * FROM EXAMES;");
			
			ResultSet rs = pstm.executeQuery();
			
			while (rs.next()) {
				Exame e = new Exame();
				e.setId(rs.getLong("id"));
				e.setDescricao(rs.getString("descricao"));
				e.setData(rs.getObject("data", LocalDateTime.class));
			}
			pstm.close();
		} catch (SQLException exception) {
			// TODO: handle exception
			exception.printStackTrace();
		}
		return null;
	}

	// Atualização dos exames
	@Override
	public void update(Exame t) {
		// TODO Auto-generated method stub
		if (t.getDescricao() == null || t.getData() == null) {
			throw new IllegalArgumentException("\n Os campos descrição e data são obrigatórios");
		}
		
		try {
			PreparedStatement pstm = conn.getConnection()
				.prepareStatement("UPDATE EXAMES SET descricao = ?, data = ? WHERE id = ?;");
			pstm.setLong(1, t.getId());
			pstm.setString(2, t.getDescricao());
			pstm.setObject(3, t.getData());
			pstm.execute();
			pstm.close();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
