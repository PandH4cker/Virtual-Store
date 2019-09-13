package store.business.util.product;

import store.business.util.product.description.CharacterName;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;

public class DVD extends Product {
    private ArrayList<CharacterName> actors;
    private final DVDGenre genre;
    private final int duration;


    public DVD(final String title,
               final int price,
               final int uniqueID,
               final int numberLeft,
               final ImageIcon image,
               ArrayList<CharacterName> actors,
               final DVDGenre genre,
               final int duration) {
        super(ProductCategory.DVD, title, price, uniqueID, numberLeft, image);
        this.actors = actors;
        Collections.sort(actors);
        this.genre = genre;
        this.duration = duration;

    }

    public ArrayList<CharacterName> getActors() {
        return this.actors;
    }

    public DVDGenre getGenre() {
        return this.genre;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return String.format("titre: %s%sacteurs: %s%sdurée: %d minutes%s",
                this.getName(), System.lineSeparator(),
                this.actors, System.lineSeparator(),
                this.duration, System.lineSeparator());
    }

    public enum DVDGenre {
        COMEDY("Comédie"),
        ACTION("Action"),
        DRAMA("Drame"),
        CARTOON("Dessin Animé"),
        FAMILY("Famille"),
        FANTASY("Fantasy"),
        SCIFI("Sci-Fi"),
        HORROR("Horreur");

        private final String genreName;

        DVDGenre(final String genreName) {
            this.genreName = genreName;
        }

        @Override
        public String toString() {
            return this.genreName;
        }

        //TODO improve this enum
    }
}
