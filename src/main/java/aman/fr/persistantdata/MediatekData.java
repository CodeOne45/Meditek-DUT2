package aman.fr.persistantdata;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import aman.fr.persistantdata.dataBase.DocumentsDB;
import aman.fr.persistantdata.dataBase.UsersDB;
import aman.fr.persistantdata.modele.docs.DocType;
import aman.fr.persistantdata.modele.docs.DocumentFactory;
import mediatek2021.*;


// classe mono-instance : l'unique instance est connue de la bibliotheque
// via une injection de d�pendance dans son bloc static

public class MediatekData implements PersistentMediatek {
// Jean-Fran�ois Brette 01/01/2018
	static {
		// injection dynamique de la d�pendance dans le package stable mediatek2021
		Mediatek.getInstance().setData(new MediatekData());
	}
	
	private DocumentsDB docs;
	private UsersDB user;
	
	private MediatekData() {
		this.docs = new DocumentsDB();
		this.user = new UsersDB();
	}

	// renvoie la liste de tous les documents de la biblioth�que
	@Override
	public List<Document> catalogue(int type) {
		//TODO: Change/make it different
		DocType dType = DocType.getTypeById(type);
		if (dType == null)
			return docs.getAll();
		return docs.getAll().stream().filter(d -> {
			Class<?> docClass = (Class<?>) d.data()[6];
			return docClass.getSimpleName().equalsIgnoreCase(dType.toString());
		}).collect(Collectors.toList());
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
		DocType docType = DocType.getTypeById(type);

		if (docType == null)
			throw new NewDocException("This type doesn't exist");

		List<Object> normalizedArgs = new ArrayList<>(Arrays.asList(args));
		normalizedArgs.remove(0);
		Document d = DocumentFactory.newDocument(args[0].toString(), docType, true, normalizedArgs.toArray());

		// Add doc to DB
		docs.insert(d);
	}

	// supprime un document - exception � d�finir
	@Override
	public void suppressDoc(int numDoc) throws SuppressException {
		boolean isDeleted = this.docs.delete(numDoc);
		if (!isDeleted)
			throw new SuppressException("Borrowed document ! You can't delete this document");
	}

}
