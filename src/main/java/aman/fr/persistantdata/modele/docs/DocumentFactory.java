package aman.fr.persistantdata.modele.docs;

import aman.fr.persistantdata.modele.statusdoc.Emprunte;
import aman.fr.persistantdata.modele.statusdoc.Libre;
import mediatek2021.Document;

import java.time.LocalDate;


public class DocumentFactory {

    public static Document newDocument(int idDocument, String titreDocument, DocType type, boolean free, Object... args) {
        switch (type){
            case BOOK:
                return new Livre(idDocument, titreDocument, LocalDate.now(), args[1].toString(), args[0].toString(), free? new Libre() : new Emprunte());
            case CD:
                return new CD(idDocument, titreDocument, LocalDate.now(), args[0].toString(), args[1].toString(), free? new Libre() : new Emprunte());
            case DVD:
                return new DVD(idDocument, titreDocument, LocalDate.now(), args[1].toString(), args[0].toString(), free? new Libre() : new Emprunte());
            default:
                return null;
        }
    }

    public static Document newDocument(String titreDocument, DocType type, boolean free, Object... args) {
        switch (type){
            case BOOK:
                return new Livre(titreDocument, LocalDate.now(), args[1].toString(), args[0].toString(), free? new Libre() : new Emprunte());
            case CD:
                return new CD(titreDocument, LocalDate.now(), args[0].toString(), args[1].toString(), free? new Libre() : new Emprunte());
            case DVD:
                return new DVD(titreDocument, LocalDate.now(), args[1].toString(), args[0].toString(), free? new Libre() : new Emprunte());
            default:
                return null;
        }
    }

}
