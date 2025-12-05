package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
//	private static String DBURL = "jdbc:mysql://localhost/healthcare";
//	private static String DBuser = "root";
//	private static String DBPass = "manager";
	private static Connection connection;
	
	public static Connection getConnection(String url,String usr,String pass) throws SQLException{
		connection = DriverManager.getConnection(url,usr,pass);
		return connection;
	}
}
