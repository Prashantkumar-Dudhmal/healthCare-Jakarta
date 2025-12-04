package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
	private static String DBURL = "jdbc:mysql://localhost/healthcare";
	private static String DBuser = "root";
	private static String DBPass = "manager";
	private static Connection connection;
	
	public static Connection getConnection() throws SQLException{
		connection = DriverManager.getConnection(DBURL, DBuser, DBPass);
		return connection;
	}
}
