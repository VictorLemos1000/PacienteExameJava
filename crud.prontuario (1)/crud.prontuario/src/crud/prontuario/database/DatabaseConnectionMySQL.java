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

	private Connection connection;
	private String USERNAME;
	private String PASSWORD;
	private String ADDRESS;
	private String PORT;
	private String DATABASE;
	
	public DatabaseConnectionMySQL() {
        ConfigLoader config = new ConfigLoader();
        config.load("config.properties");

        this.USERNAME = config.getValor("DB_USER");
        this.PASSWORD = config.getValor("DB_PASSWORD");
        this.ADDRESS = config.getValor("DB_ADDRESS");
        this.PORT = config.getValor("DB_PORT");
        this.DATABASE = config.getValor("DB_SCHEMA");
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Driver JDBC MySQL não encontrado", e);
        }
    }

	@Override
	public Connection getConnection() 
	{
		try {
			return DriverManager.getConnection("jdbc:mysql://%s:%s/".formatted(ADDRESS, PORT)+DATABASE, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	
	public void closeConnection() 
	{
		try {
			if (connection != null && !connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException e){
			System.err.println("Não foi possível encerrar a conexão: " + e.getMessage());
		}
		
	}

	@Override
	public void closeConnection(Connection conn) {
		// TODO Auto-generated method stub
		
	}


}
