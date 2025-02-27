package org.prontuario.db;

import java.sql.Connection;

public interface IConnection {

	Connection getConnection();
	
	void closeConnection();
}
