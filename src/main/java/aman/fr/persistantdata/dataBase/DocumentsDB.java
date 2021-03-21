package aman.fr.persistantdata.dataBase;

import java.sql.*;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;

import aman.fr.persistantdata.modele.docs.*;
import mediatek2021.Document;

public class DocumentsDB extends DAO<Document> {

	@Override
	public void insert(Document tuple) {
		PreparedStatement requete = null;
		ResultSet resultat = null;
		ADocument doc = (ADocument) tuple;
		try {
			this.getConnexion().setAutoCommit(false);
			requete = this.getConnexion().prepareStatement(
					"INSERT INTO documents(titre_doc,description,borrow,type,id_borrower,date_doc) VALUES(?,?,?,?,Null,?)",
					Statement.RETURN_GENERATED_KEYS);
			// Insert all data
			requete.setString(1, doc.getTitre());
            requete.setString(2, doc.getDescription());
			requete.setDate(5, Date.valueOf(doc.getDate()));
			requete.setString(3, doc.getEtat().getClass().getSimpleName().toLowerCase());
			requete.setString(4, doc.getClass().getSimpleName());

			// Add to DB
			requete.executeUpdate();
			resultat = requete.getGeneratedKeys();

			if(!resultat.next())
			    throw new SQLException("Error: No genrated key!");

			int id = (int) resultat.getLong(1);

			// Add diffrente type of doc to DB
			if (tuple instanceof Livre) {
				Livre l = (Livre) doc;

				requete = this.getConnexion().prepareStatement("INSERT INTO book(id_doc,auteur) VALUES(?,?)");
				requete.setInt(1, id);
				requete.setString(2, l.getAuteur());
			} else if (tuple instanceof DVD) {
				DVD d = (DVD) doc;
				requete = this.getConnexion()
						.prepareStatement("INSERT INTO DVD(id_doc, realisateur) VALUES(?,?)");
				requete.setInt(1, id);
				requete.setString(2, d.getRealisateur());
			} else if (tuple instanceof CD) {
				CD c = (CD) doc;
				requete = this.getConnexion().prepareStatement("INSERT INTO CD(id_doc,artiste) VALUES(?,?)");
				requete.setInt(1, id);
				requete.setString(2, c.getArtiste());
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
            resultat = requete.executeQuery("SELECT * FROM documents");

            while (resultat.next()) {
            	// Colect data from DB--> documents table
                int idDocument = resultat.getInt("id_doc");
                String titreDocument = resultat.getString("titreDocument");
                String dexcDoc = resultat.getString("description");
                LocalDate dateDocument = resultat.getDate("dateDocument").toLocalDate();
                String state = resultat.getString("borrow");
                String type = resultat.getString("type");

                ResultSet getDoc = getDocumentById(idDocument, type);

                if (getDoc == null || !getDoc.next())
                    return null;
                // Get data based on type of doc
                String args;
                switch (type) {
                    case "book":
                        args = getDoc.getString("auteur");
                        break;
                    case "cd":
                        args = getDoc.getString("artiste");
                        break;
                    case "dvd":
                        args = getDoc.getString("realisateur");
                        break;
                    default:
                        throw new SQLException("Document type invalid");
                }
                Document doc = DocumentFactory.newDocument(idDocument, titreDocument, DocType.getTypeFromString(type),
                        state.equals("free"), dexcDoc, args);
                documents.add(doc);
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
		String getQuery = "SELECT * FROM documents WHERE id_doc = ?";
		try (PreparedStatement preparedStatement = super.getConnexion().prepareStatement(getQuery)) {
			preparedStatement.setInt(1, id);
			ResultSet resultat = preparedStatement.executeQuery();

			if (!resultat.next())
				return null;

			int idDocument = resultat.getInt("id_doc");
			String titreDocument = resultat.getString("titreDocument");
			String dexcDoc = resultat.getString("description");
			LocalDate dateDocument = resultat.getDate("dateDocument").toLocalDate();
			String state = resultat.getString("borrow");
			String type = resultat.getString("type");

			ResultSet docSet = getDocumentById(idDocument, type.toLowerCase());

			if (docSet == null || !docSet.next())
				return null;

			switch (type) {
				case "book":
					return DocumentFactory.newDocument(idDocument, titreDocument, DocType.getTypeFromString(type),
							state.equals("free"), dexcDoc, docSet.getString("auteur"));
				case "cd":
					return DocumentFactory.newDocument(idDocument, titreDocument, DocType.getTypeFromString(type),
							state.equals("free"), dexcDoc, docSet.getString("artiste"));
				case "dvd":
					return DocumentFactory.newDocument(idDocument, titreDocument, DocType.getTypeFromString(type),
							state.equals("free"), dexcDoc, docSet.getString("realisateur"));
				default:
					throw new SQLException("Error : Document type invalid");
			}
		} catch (SQLException throwable) {
			throwable.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean delete(int id) {
		String deletQuery = "DELETE FROM documents WHERE id_doc = ? AND  id_borrower IS NULL";
		try(PreparedStatement stm = super.getConnexion().prepareStatement(deletQuery)){
			stm.setInt(1, id);
			int rowCount = stm.executeUpdate();
			return rowCount == 0;
		}catch (SQLException throwables){
			throwables.printStackTrace();
		}
		return false;
	}

    private ResultSet getDocumentById(int id_doc, String nameTable) {
        String queryDoc = "SELECT * FROM" + nameTable.toUpperCase()  + "WHERE " + id_doc + " = ?"; //TODO: Mofify to string bulder
		try {
            PreparedStatement preparedStatement = super.getConnexion().prepareStatement(queryDoc);
            preparedStatement.setInt(1, id_doc);

            return preparedStatement.executeQuery();
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            return null;
        }
    }

}
