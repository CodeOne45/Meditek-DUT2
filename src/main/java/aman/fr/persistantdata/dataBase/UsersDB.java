package aman.fr.persistantdata.dataBase;

import java.sql.*;
import java.util.List;

import aman.fr.persistantdata.modele.user.AUser;
import aman.fr.persistantdata.modele.user.Librarian;
import aman.fr.persistantdata.modele.user.User;
import mediatek2021.Utilisateur;


public class UsersDB extends DAO<Utilisateur> {
	
	public UsersDB() {
		super();
	}
	
	@Override
	public void insert(Utilisateur tuple) {
		AUser newUser = (AUser) tuple;
		PreparedStatement requeste = null;
		try {
			requeste = this.getConnexion().prepareStatement("INSERT INTO users(email,pwd, isBibliothecaire) VALUES(?,?,?)");
			requeste.setString(1, newUser.getLogin());
			requeste.setString(2,newUser.getPassword());
			requeste.setString(3, newUser.isBibliothecaire()?"1":"0");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				if(requeste != null) requeste.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}


	@Override
	public List<Utilisateur> getAll() {
		return null;
		
	}

	@Override
	public Utilisateur getFromId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(int id) {
		// TODO Auto-generated method stub
		return false;
	}

	public Utilisateur authentification(String login, String password) {
		ResultSet result = null;
		PreparedStatement stm = null;
		Utilisateur u = null;
		Statement stat = null;
		System.out.println("Get in authentification...");
		try {
			stm = this.getConnexion().prepareStatement("SELECT * FROM users WHERE email=?");
			stm.setString(1, login);
			result = stm.executeQuery();

			if (result.next())
			{
				String pwdDB = result.getString("pwd");
				if (password.equals(pwdDB))
				{
					System.out.println("Password correct, access granted to mediatheque!");
					if (result.getInt("isBibliothecaire") == 1) {
						u = new Librarian(result.getInt("id_user"), result.getString("email"),
								result.getString("pwd"));
					}
					else{
						u = new User(result.getInt("id_user"), result.getString("email"),
								result.getString("pwd"));
					}
				} else {
					System.out.println("Password is incorrect !");
					//TODO Create password incorrect exception
				}
			} else {
				System.out.println("Email is invalid !");
				//TODO Create UsernotExost exception
			}
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Connection to DB failed!");
		} finally {
			try{
				if(result != null) result.close();
				if(stm != null) stm.close();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
		return u;
	}
}
