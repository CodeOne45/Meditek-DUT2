package aman.fr.persistantdata.modele.user;

import mediatek2021.Utilisateur;

public abstract class AUser implements Utilisateur{
	
	private String password;
	private String login;
	private int id;

	public AUser(int id, String login, String pwd) {
		super();
		this.id = id;
		this.login = login;
		this.password = pwd;
	}
	
	public AUser(String login, String pseudo) {
		super();
		this.login = login;
		this.password = pseudo;
	}

	public String getPassword() {
		return password;
	}

	public String getLogin() {
		return login;
	}
	
	public int getId() {
		return id;
	}

	public boolean isBibliothecaire() {
		return false;
	}
	
	
}
