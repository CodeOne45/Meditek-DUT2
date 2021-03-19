package aman.fr.persistantdata.modele.docs;

import java.time.LocalDate;

public class Livre extends ADocument {

    private String auteur;


    public Livre(String titre, LocalDate date, String auteur, String desc, EtatDocument etat) {
        super(titre, date, desc, etat);
        this.auteur = auteur;
    }

    public Livre(int numero, String titre, LocalDate date, String auteur,String desc ,EtatDocument etat ) {
        super(numero,titre, date, desc, etat);
        this.auteur = auteur;
    }

    public String getAuteur() {
        return auteur;
    }
}
