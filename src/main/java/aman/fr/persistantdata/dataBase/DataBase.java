package aman.fr.persistantdata.dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class DataBase <T> {
	
	/**
	 * Connection to DB (Mysql db)
	 */ 
	private Connection con;
	
	public DataBase() {
		this.con = DbConnection.getConnextion();
	}
	public abstract void insert(T tuple);
	
	public abstract List<T> getAll();
	
	public abstract T getFromId(int id);

	public abstract void delete(int id);

	public Connection getConnexion() {
		return con;
	}
	

	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
