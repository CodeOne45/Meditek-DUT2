package aman.fr.persistantdata.modele.docs;

import mediatek2021.Document;
import mediatek2021.Utilisateur;

public interface EtatDocument {
    EtatDocument emprunter(Utilisateur u, Document d);
    EtatDocument retour(Document d);
    boolean isEmprunte();
}
