package crud.prontuario.database;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
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
	
	public void criabd() {
	    String urlSemDatabase = "jdbc:mysql://%s:%s/".formatted(ADDRESS, PORT);
	    try (Connection conn = DriverManager.getConnection(urlSemDatabase, USERNAME, PASSWORD);
	         Statement stmt = conn.createStatement()) {

	        String sql = """
	            CREATE DATABASE IF NOT EXISTS %s;
	            USE %s;

	            CREATE TABLE IF NOT EXISTS pacientes (
	                id BIGINT AUTO_INCREMENT PRIMARY KEY,
	                cpf VARCHAR(14) UNIQUE NOT NULL,
	                nome VARCHAR(255) NOT NULL
	            );

	            CREATE TABLE IF NOT EXISTS exames (
	                id BIGINT AUTO_INCREMENT PRIMARY KEY,
	                descricao VARCHAR(255) NOT NULL,
	                data_exame DATETIME NOT NULL,
	                paciente_id BIGINT NOT NULL,
	                CONSTRAINT fk_exames_paciente
	                    FOREIGN KEY (paciente_id)
	                    REFERENCES pacientes(id)
	            );
	            """.formatted(DATABASE, DATABASE);

	        for (String s : sql.split(";")) {
	            if (!s.trim().isEmpty()) {
	                stmt.execute(s);
	            }
	        }

	        System.out.println("Banco de dados e tabelas criados com sucesso.");

	  } catch (SQLException e) {
	        System.out.println("Erro ao criar banco ou tabelas:");
	        e.printStackTrace();
}
}

	@Override
	public Connection getConnection() 
	{
		try {
			String url = "jdbc:mysql://" + ADDRESS + ":" + PORT + "/" + DATABASE;
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
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
