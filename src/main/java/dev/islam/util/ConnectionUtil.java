package dev.islam.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	private static Connection connection;

	public static Connection getHardCodedConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@//host:1521/ORCL";
		String username = "admin";
		String password = "p4ssw0rd";
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
		}
		return connection;
	}

	/*
	 * Can also use a properties file (https://www.java2blog.com/wp-content/uploads/2016/02/configFileScreenshot.png)
	 * which stores key value pairs. Then we can add this file to our .gitignore so that it's not uploaded.
	 */
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //registering Oracle Driver
		}
		catch(ClassNotFoundException ex) {
			ex.printStackTrace();
			System.exit(1);
		}

		String url = System.getenv("DB_URL"); // best not to hard code in your db credentials ! 
		String username = System.getenv("DB_USERNAME");
		String password = System.getenv("DB_PASSWORD");
		if(connection == null || connection.isClosed()) {
			connection = DriverManager.getConnection(url, username, password);
			System.out.println(connection.getMetaData().getDriverName());
		}
		return connection;
	}

}
