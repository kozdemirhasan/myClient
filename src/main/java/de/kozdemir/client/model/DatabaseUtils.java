package de.kozdemir.client.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseUtils {
	
	//für Msql
	private static final String HOST = "localhost";
	private static final int PORT = 3306;
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String DB_NAME = "products";
	private static final String URL = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DB_NAME;
	

    private DatabaseUtils() {
    }

    public static Connection getConnection() throws SQLException {
//        return DriverManager.getConnection("jdbc:sqlite:products.db"); //für Sqlite
    	return DriverManager.getConnection(URL, USER, PASSWORD);  //für Mysql 
    }
}
