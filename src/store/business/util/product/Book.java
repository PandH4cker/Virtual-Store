package store.business.util.product;

import store.business.util.product.description.CharacterName;

import javax.swing.*;

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
    }
}
