package org.prontuario.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.prontuario.util.ConfigLoader;

public class ConexaoMySQL implements IConnection{

	private final String DB_USER;
	private final String DB_PASSWORD;
	private final String DB_HOST;
	private final String DB_NAME;
	private final String DB_PORT;
	
	{
		DB_USER = ConfigLoader.getInstance("config").getProperty("DB_USER");
		DB_PASSWORD = ConfigLoader.getInstance("config").getProperty("DB_PASSWORD");
		DB_HOST = ConfigLoader.getInstance("config").getProperty("DB_HOST");
		DB_NAME = ConfigLoader.getInstance("config").getProperty("DB_NAME");
		DB_PORT = ConfigLoader.getInstance("config").getProperty("DB_PORT");
	}
	
	@Override
	public Connection getConnection() {
		// TODO Auto-generated method stub
		Connection conexao = null;
		try {
			conexao = DriverManager.getConnection("jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME, DB_USER, DB_PASSWORD);
		} catch (SQLException excecao) {
			// TODO Auto-generated catch block
			excecao.printStackTrace();
		}
		return conexao;
	}

	@Override
	public void closeConnection() {
		// TODO Auto-generated method stub
	}
}
