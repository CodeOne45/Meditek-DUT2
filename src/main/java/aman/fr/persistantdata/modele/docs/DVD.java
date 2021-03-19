package aman.fr.persistantdata.modele.docs;

import java.time.LocalDate;

public class DVD extends ADocument{
    private String realisateur;

    //private Qualite qualite;

    /*private enum Qualite {
        HD, FULLHD, SD, UHD
    }*/

    public DVD(String titre, LocalDate date, String realisateur,  String desc, EtatDocument etat) {
        super(titre, date, desc, etat);
        this.realisateur = realisateur;
    }

    public DVD(int numero, String titre, LocalDate date, String realisateur, String desc, EtatDocument etat) {
        super(titre, date, desc, etat);
        this.realisateur = realisateur;
    }

    public String getRealisateur() {
        return realisateur;
    }

}
