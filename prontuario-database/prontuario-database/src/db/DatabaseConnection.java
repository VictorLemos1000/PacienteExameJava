package db;

import java.sql.Connection;

import exception.DatabaseConectionException;

public interface DatabaseConnection {

	    public Connection getConnection() throws DatabaseConectionException;
	    public void disconnect() throws DatabaseConectionException;

}
