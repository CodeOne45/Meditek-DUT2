package aman.fr.persistantdata.modele.docs;

import mediatek2021.Document;
import mediatek2021.Utilisateur;

import java.time.LocalDate;

public abstract class ADocument implements Document {
    private int numero;
    private final String titre;
    private LocalDate date;
    private EtatDocument etat;
    private final String description;

    public ADocument(String titre, LocalDate date, String desc, EtatDocument etat) {
        super();
        numero = 0;
        this.titre = titre;
        this.date = date;
        this.etat = etat;
        this.description = desc;
    }

    public ADocument(int id ,String titre, LocalDate date, String desc, EtatDocument etat) {
        this(titre,date,desc,etat);
        numero = id;
    }

    @Override
    public Object[] data() {
        return new Object[0];
    }


    public void emprunter(Utilisateur u) {
        this.etat = etat.emprunter(u, this);
    }

    public void retour() {
        this.etat = etat.retour(this);
    }

    public String getTitre() {
        return titre;
    }

    public LocalDate getDate() {
        return date;
    }

    public EtatDocument getEtat() {
        return etat;
    }

    public boolean isEmprunte() {
        return etat.isEmprunte();
    }

    public int getNumero() {
        return numero;
    }

    public String getDescription(){ return description;}

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.isEmprunte()?"[EMPRUNTE] ":"[LIBRE] ");
        sb.append(this.getNumero());
        sb.append(" - ");
        sb.append(this.getClass().getSimpleName());
        sb.append(" : ");
        sb.append(this.getTitre());
        sb.append(" (");
        sb.append(this.getDate().getYear());
        sb.append(" )");

        return sb.toString();
    }
}
