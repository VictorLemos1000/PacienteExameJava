package crud.prontuario.database;
		
import java.sql.Connection;
		
public interface IConnection {
	
	// Método para pegar conecxão.
	public Connection getConnection();
	
	// Método para fechar a conexão com o DB.
	public void closeConnection(Connection conn);
	
	public void criabd();
}
