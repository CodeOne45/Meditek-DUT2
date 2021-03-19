package aman.fr.persistantdata.dataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;


public abstract class DAO<T> {
	
	/**
	 * Connection to DB (Mysql db)
	 */ 
	private Connection con;
	
	public DAO() {
		this.con = DbConnection.getConnextion();
	}
	public abstract void insert(T tuple);
	
	public abstract List<T> getAll();
	
	public abstract T getFromId(int id);

	public abstract boolean delete(int id);

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
