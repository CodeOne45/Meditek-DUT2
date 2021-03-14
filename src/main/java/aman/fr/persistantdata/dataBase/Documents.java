package aman.fr.persistantdata.dataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import aman.fr.persistantdata.modele.docs.*;
import aman.fr.persistantdata.modele.statusdoc.Emprunte;
import aman.fr.persistantdata.modele.statusdoc.Libre;
import mediatek2021.Document;

public class Documents extends DataBase<Document> {

	@Override
	public void insert(Document tuple) {
		PreparedStatement requete = null;
		ResultSet resultat = null;
		ADocument doc = (ADocument) tuple;
		try {
			this.getConnexion().setAutoCommit(false);
			requete = this.getConnexion().prepareStatement(
					"INSERT INTO documents(titre_doc,date_doc,borrow,type,id_borrower) VALUES(?,?,?,?,Null)",
					Statement.RETURN_GENERATED_KEYS);
			requete.setString(1, doc.getTitre());
			requete.setDate(2, Date.valueOf(doc.getDate()));
			requete.setBoolean(3, doc.isEmprunte());
			requete.setString(4, doc.getClass().getSimpleName());

			requete.executeUpdate();

			resultat = requete.getGeneratedKeys();
			resultat.next();
			int id = (int) resultat.getLong(1);

			if (tuple instanceof Livre) {
				Livre l = (Livre) doc;

				requete = this.getConnexion().prepareStatement("INSERT INTO livre(idLivre,	auteur) VALUES(?,?)");
				requete.setInt(1, id);
				requete.setString(2, l.getAuteur());
			} else if (tuple instanceof DVD) {
				DVD d = (DVD) doc;
				requete = this.getConnexion()
						.prepareStatement("INSERT INTO DVD(idDVD, realisateur, qualite) VALUES(?,?,?)");
				requete.setInt(1, id);
				requete.setString(2, d.getRealisateur());
				requete.setString(3, d.getQualite().toString());
			} else if (tuple instanceof CD) {
				CD c = (CD) doc;

				requete = this.getConnexion().prepareStatement("INSERT INTO CD(idCD, genre,artiste) VALUES(?,?,?)");
				requete.setInt(1, id);
				requete.setString(2, c.getGenre());
				requete.setString(3, c.getArtiste());
			}

			requete.execute();
			this.getConnexion().commit();
			this.getConnexion().setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultat != null)
					resultat.close();
				if (requete != null)
					requete.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Document> getAll() {
        List<Document> documents = new LinkedList<Document>();

        ResultSet resultat = null;
        Statement requete = null;

        try {
            requete = this.getConnexion().createStatement();
            resultat = requete.executeQuery("SELECT * FROM documents d INNER JOIN livre c ON d.id_doc=c.IdLivre");

            while (resultat.next()) {
                int idDocument = resultat.getInt("idDocument");
                String titreDocument = resultat.getString("titreDocument");
                LocalDate dateDocument = resultat.getDate("dateDocument").toLocalDate();
                boolean emprunte = resultat.getBoolean("emprunte");
                //EtatDocument etat = (emprunte ? new Emprunte() : new Libre());

                String auteur = resultat.getString("auteur");

                Livre l = new Livre(idDocument, titreDocument, dateDocument, auteur, etat);
                documents.add(l);
            }

            requete = this.getConnexion().createStatement();
            resultat = requete.executeQuery("SELECT * FROM documents d INNER JOIN DVD c ON d.id_doc=c.IdDVD");

            while (resultat.next()) {
                int idDocument = resultat.getInt("idDocument");
                String titreDocument = resultat.getString("titreDocument");
                LocalDate dateDocument = resultat.getDate("dateDocument").toLocalDate();
                boolean emprunte = resultat.getBoolean("emprunte");
                //EtatDocument etat = (emprunte ? new Emprunte() : new Libre());

                String realisateur = resultat.getString("realisateur");
                String qualite = resultat.getString("qualite");

                DVD d = new DVD(idDocument, titreDocument, dateDocument, realisateur, qualite, etat);
                documents.add(d);
            }

            requete = this.getConnexion().createStatement();
            resultat = requete.executeQuery("SELECT * FROM documents d INNER JOIN CD c ON d.id_doc=c.IdCD");

            while (resultat.next()) {
                int idDocument = resultat.getInt("idDocument");
                String titreDocument = resultat.getString("titreDocument");
                LocalDate dateDocument = resultat.getDate("dateDocument").toLocalDate();
                boolean emprunte = resultat.getBoolean("emprunte");
                //EtatDocument etat = (emprunte ? new Emprunte() : new Libre());

                String artiste = resultat.getString("artiste");
                String genre = resultat.getString("genre");

                CD c = new CD(idDocument, titreDocument, dateDocument, genre, artiste, etat);
                documents.add(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultat != null)
                    resultat.close();
                if (requete != null)
                    requete.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return documents;
	}

	@Override
	public Document getFromId(int id) {
		return null;
	}

	@Override
	public void delete(int id) {

	}

}
