package store.business.util.product;

import store.business.util.product.description.CharacterName;

import javax.swing.*;
import java.nio.file.Paths;

public class Book extends Product {
    private final CharacterName author;
    private final LanguageBook language;
    private final int numberOfPages;

    public Book(final String title,
                final int price,
                final int uniqueID,
                final int numberLeft,
                final ImageIcon image,
                final CharacterName author,
                final LanguageBook language,
                final int numberOfPages) {
        super(ProductCategory.BOOK, title, price, uniqueID, numberLeft, image);
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.logger.log("New Book Created: " + this);
    }

    public Book(final String title,
                final String price,
                final String uniqueID,
                final String numberLeft,
                final String image,
                final String author,
                final String language,
                final String numberOfPages) {
        super(ProductCategory.VIDEOGAME, title, Integer.parseInt(price), Integer.parseInt(uniqueID),
              Integer.parseInt(numberLeft), new ImageIcon(Paths.get(image).toAbsolutePath().toString()));
        final String[] splittedAuthor = author.split(" ");
        this.author = new CharacterName(splittedAuthor[0], splittedAuthor[1]);
        this.language = LanguageBook.toLanguageBook(language);
        this.numberOfPages = Integer.parseInt(numberOfPages);
        this.logger.log("New Book Created: " + this);
    }


    public CharacterName getAuthor() {
        return this.author;
    }

    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    public LanguageBook getLanguage() {
        return this.language;
    }

    @Override
    public String toString() {
        return String.format("titre: %s%sauteur: %s%slangage: %s%sN#pages: %d pages",
                this.getName(), System.lineSeparator(),
                this.author, System.lineSeparator(),
                this.language, System.lineSeparator(),
                this.numberOfPages);
    }

    public enum LanguageBook {
        ENGLISH("Anglais"),
        FRENCH("Français"),
        DEUTSCHE("Allemand"),
        SPANISH("Espagnol"),
        CHINESE("Chinois");

        private final String languageName;

        LanguageBook(final String languageName) {
            this.languageName = languageName;
        }

        @Override
        public String toString() {
            return this.languageName;
        }

        public static LanguageBook toLanguageBook(String s) {
            for(LanguageBook l : LanguageBook.values()) if(l.toString().equalsIgnoreCase(s)) return l;
            throw new RuntimeException("Not a language known");
        }

    }
}
