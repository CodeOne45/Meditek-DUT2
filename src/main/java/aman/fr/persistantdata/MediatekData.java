package aman.fr.persistantdata;

import java.time.LocalDate;
import java.util.List;

import aman.fr.persistantdata.dataBase.Documents;
import aman.fr.persistantdata.dataBase.Users;
import aman.fr.persistantdata.modele.docs.CD;
import aman.fr.persistantdata.modele.docs.DVD;
import aman.fr.persistantdata.modele.docs.Livre;
import aman.fr.persistantdata.modele.statusdoc.Libre;
import mediatek2021.*;


// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de d�pendance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-Fran�ois Brette 01/01/2018
	static {
		// injection dynamique de la d�pendance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
	}
	
	private Documents docs;
	private Users user;
	
	private MediatekData() {
		this.docs = new Documents();
		this.user = new Users();
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public List<Document> catalogue(int type) {
		return docs.getAll();
	}

	// va r�cup�rer le User dans la BD et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Utilisateur getUser(String login, String password) {
		return user.authentification(login, password);
	}

	// va r�cup�rer le document de num�ro numDocument dans la BD
	// et le renvoie
	// si pas trouv�, renvoie null
	@Override
	public Document getDocument(int numDocument) {

		return docs.getFromId(numDocument);
	}

	// ajoute un nouveau document - exception � d�finir
	@Override
	public void newDocument(int type, Object... args) throws NewDocException {
		// args[0] -> le titre
		// args [1] --> l'auteur
		// etc en fonction du type et des infos optionnelles
		Document d = null;
		LocalDate dateDoc = LocalDate.parse(args[1].toString());

		switch (type){
			case 1:
				d = new Livre(args[0].toString(),dateDoc,args[2].toString(),new Libre());
				break;
			case 2:
				d = new DVD(args[0].toString(),dateDoc,args[2].toString(),args[3].toString(), new Libre());
				break;
			case 3:
				d = new CD(args[0].toString(),dateDoc,args[2].toString(),args[3].toString(), new Libre());
		}
		// Add doc ti DB
		docs.insert(d);
	}

	// supprime un document - exception � d�finir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		
	}

}
