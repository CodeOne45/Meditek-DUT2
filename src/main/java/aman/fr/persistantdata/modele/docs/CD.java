package aman.fr.persistantdata.modele.docs;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.stream.Stream;

public class CD extends ADocument{
    private String artiste;

    public CD(String titre, LocalDate date, String genre, String artiste, EtatDocument etat) {
        super(titre, date, genre, etat);
        this.artiste = artiste;
    }

    public CD(int numero,String titre, LocalDate date, String genre, String artiste, EtatDocument etat) {
        super(numero,titre, date, genre, etat);
        this.artiste = artiste;
    }

    public String getArtiste() {
        return artiste;
    }

    @Override
    public Object[] data() {
        Object[] data = {artiste, CD.class};
        return Stream.of(super.data(), data).flatMap(Stream::of).toArray();
    }

    @Override
    public String toString() {
        return super.toString() + Arrays.toString(data());
    }
}
