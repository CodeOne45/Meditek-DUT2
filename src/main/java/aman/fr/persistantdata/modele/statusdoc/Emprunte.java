package aman.fr.persistantdata.modele.statusdoc;

import aman.fr.persistantdata.modele.docs.EtatDocument;
import mediatek2021.Document;
import mediatek2021.Utilisateur;

public class Emprunte implements EtatDocument {
    @Override
    public EtatDocument emprunter(Utilisateur u, Document d) {
        // TODO: In next version implement borrow document function
        return null;
    }

    @Override
    public EtatDocument retour(Document d) {
        // TODO: In next version implement return document function
        return null;
    }

    @Override
    public boolean isEmprunte() {
        return false;
    }
}
