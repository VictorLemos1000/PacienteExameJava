package crud.prontuario.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import crud.prontuario.util.ConfigLoader;

// Esta classe é a que vai ligar o
public class DatabaseConnectionMySQL implements IConnection {

	private final String USERNAME = ConfigLoader.getValor("DB_USER");
	private final String PASSWORD = ConfigLoader.getValor("DB_PASSWORD");
	private final String ADDRESS = ConfigLoader.getValor("DB_ADDRESS");
	private final String PORT = ConfigLoader.getValor("DB_PORT");
	private final String DATABASE = ConfigLoader.getValor("DB_SCHEMA");
	
//	private void loadProperties() {
//		// TODO Auto-generated method stub
//		Properties props = new Properties();
//		
//		try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")){
//			if (input == null) {
//				System.err.println("\n Arquivo config.properties não encontrado na classe path");
//				return ;
//			}
//			props.load(input);
//			USERNAME = props.getProperty("DB_USER");
//			PASSWORD = props.getProperty("DB_PASSWORD");
//			ADDRESS = props.getProperty("DB_ADDRESS");
//			PORT = props.getProperty("DB_PORT");
//			DATABASE = props.getProperty("DB_SCHEMA");
//		} catch (IOException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//	}
//	
//	public DatabaseConnectionMySQL() {
//		// TODO Auto-generated constructor stub
//		loadProperties();
//	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:"+PORT+"/"+DATABASE, USERNAME, PASSWORD);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		try {
			if (conn != null &&  conn.isClosed()) {
				conn.close();
			}
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
