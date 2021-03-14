package aman.fr.persistantdata.modele.user;


public class Librarian extends AUser {

	public Librarian(int id, String login, String pwd) {
		super(id, login, pwd);
	}

	public Librarian(String login, String pwd) {
		super(login, pwd);
	}
	
	@Override
	public boolean isBibliothecaire() {
		return true;
	}
	
	
	@Override
	public Object[] data() {
		return null;
	}

	@Override
	public String login() {
		return null;
	}

	@Override
	public String password() {
		return null;
	}
}
