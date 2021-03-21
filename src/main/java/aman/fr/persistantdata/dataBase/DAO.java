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

	/**
	 * Method to insert data into DB
	 * @param tuple any type of object to insert
	 */
	public abstract void insert(T tuple);

	/**
	 * Get all data from DB for a particulaire object/table
	 * @return List of <T>(document,User...)
	 */
	public abstract List<T> getAll();

	/**
	 * Get a object from DB by id
	 * @param id id of a object(document,User...)
	 * @return a object(document,User...)
	 */
	public abstract T getFromId(int id);

	/**
	 * Delete an object/element from the DB
	 * @param id id of a object(document,User...)
	 * @return boolean
	 */
	public abstract boolean delete(int id);

	/**
	 * Get a Connection object --> connect to DB
	 * @return Connection
	 */
	public Connection getConnexion() {
		return con;
	}

	/**
	 * Close an DB connection
	 */
	public void close() {
		try {
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
}
