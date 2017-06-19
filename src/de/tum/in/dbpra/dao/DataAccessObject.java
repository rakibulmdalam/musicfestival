package de.tum.in.dbpra.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import de.tum.in.dbpra.Config;

public abstract class DataAccessObject {

	protected Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName("org.postgresql.Driver");
        return DriverManager.getConnection(
        		"jdbc:postgresql://" + Config.HOST + ":" + Config.PORT + "/" + Config.DB, 
        Config.USER,
        Config.PASSWORD);
    }
	
}
