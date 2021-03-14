package aman.fr.persistantdata.modele.user;


public class User extends AUser {

	public User(int id, String login, String pwd) {
		super(id, login, pwd);
	}
	
	public User(String login, String pwd) {
		super(login, pwd);
	}

	@Override
	public boolean isBibliothecaire() {
		return false;
	}
	
	@Override
	public String toString() {
		return this.getLogin();
	}

	@Override
	public Object[] data() {
		return new Object[]{super.getId(),super.getLogin(),super.getPassword()};
	}

	@Override
	public String login() {
		return super.getLogin();
	}

	@Override
	public String password() {
		return super.getLogin();
	}
}
