package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
//STEP 1: this class establishes the connection to the database
	
	public static synchronized Connection getConnection() throws SQLException{
		String dbURL = "jdbc:mysql://localhost:3306/prs2";
		String username = "prs_user"; //refers to the grant statement in MYSQL
		String password = "sesame";
		Connection connection = DriverManager.getConnection(dbURL, username, password);
		return connection;
	}
	
}
