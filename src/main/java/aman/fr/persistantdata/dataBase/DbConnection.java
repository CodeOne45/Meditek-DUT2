package aman.fr.persistantdata.dataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbConnection {
	//Launch at load
	static {
		DbConnection.connection();
	}
	
	private static Connection con;
	private static final String url = "jdbc:mysql://localhost:3306/db_medlatek";
	private static final String user = "root";
	private static final String password = "";

	public static Connection getConnextion() {
		return con;
	}

	/**
	 * Make connection to DB
	 * @return a Connection
	 */
	private static Connection connection() {
		// Connection to JDBC driver
		try {
			System.out.println("Loading of driver..." );
	        Class.forName( "com.mysql.jdbc.Driver" );
	        System.out.println("Driver charged !" );
		} catch (Exception e) {
			System.err.println(e.getMessage() + " Error on loading JDBC driver!");
		}
		
		//Connection to db
		try {
			con = DriverManager.getConnection(url, user, password);
			System.out.println("Connected to the DB" );
		} catch (SQLException e) {
			System.err.println(e.getMessage() + "Connection to Database failed");
		}
		return con;
	}

	/**
	 * Test to DB connection
	 * @param args
	 * @throws SQLException
	 */
	public static void main(String[] args) throws SQLException {
		Connection coonex = DbConnection.getConnextion();
		String f1;
		String query = "Select * FROM users";
		Statement stat = coonex.createStatement();
		ResultSet rs = stat.executeQuery(query);
		while(rs.next()) {
			f1 = rs.getString(2);
			System.out.println(f1);
		}
	}

}
