package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import exception.DatabaseConectionException;
import util.LoadParameter;

public class MySQLDatabaseConnection implements DatabaseConnection{

	private Connection conn;
	private final String DBNAME = LoadParameter.getValor("DBNAME");
	private final String DBADDRESS = LoadParameter.getValor("DBADDRESS");
	private final String DBPASSWORD = LoadParameter.getValor("DBPASSWORD");
	private final String DBPORT = LoadParameter.getValor("DBPORT");
	private final String DBUSER = LoadParameter.getValor("DBUSER");
	
	
	public Connection getConnection() {
		// TODO Auto-generated method stub
		
		try {
			conn = DriverManager.getConnection("jdbc:mysql://" + DBADDRESS + ":" + DBPORT + "/" + DBNAME, DBUSER, DBPASSWORD);
			System.out.println(" Conexão estabelecida com o banco de dado.");
			return conn;
		} catch (SQLException excecao) {
			// TODO Auto-generated catch block
			excecao.printStackTrace();
		}
		return null;
	}

	
	public void disconnect() throws DatabaseConectionException{
		// TODO Auto-generated method stub
		try {
			if (conn != null && !conn.isClosed()) {
			    try {
			        conn.close();
			        conn = null;
			    } catch (SQLException e) {
			        throw new DatabaseConectionException("Erro ao desconectar do banco de dados: " + e.getMessage(), e);
			    }
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
