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
                final long uniqueID,
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
        super(ProductCategory.VIDEOGAME, title, Integer.parseInt(price), Long.parseLong(uniqueID),
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
        return this.getName()
                +"\n" +this.author
                +"\n" +this.language
                +"\n" +this.numberOfPages+" pages"
                +"\n" +this.price+" €";
    }

    public enum LanguageBook {
        ENGLISH("Anglais") {
            @Override
            public boolean isEnglish() {
                return true;
            }

            @Override
            public boolean isFrench() {
                return false;
            }

            @Override
            public boolean isDeutsche() {
                return false;
            }

            @Override
            public boolean isSpanish() {
                return false;
            }

            @Override
            public boolean isChinese() {
                return false;
            }
        },
        FRENCH("Français") {
            @Override
            public boolean isEnglish() {
                return false;
            }

            @Override
            public boolean isFrench() {
                return true;
            }

            @Override
            public boolean isDeutsche() {
                return false;
            }

            @Override
            public boolean isSpanish() {
                return false;
            }

            @Override
            public boolean isChinese() {
                return false;
            }
        },
        DEUTSCHE("Allemand") {
            @Override
            public boolean isEnglish() {
                return false;
            }

            @Override
            public boolean isFrench() {
                return false;
            }

            @Override
            public boolean isDeutsche() {
                return true;
            }

            @Override
            public boolean isSpanish() {
                return false;
            }

            @Override
            public boolean isChinese() {
                return false;
            }
        },
        SPANISH("Espagnol") {
            @Override
            public boolean isEnglish() {
                return false;
            }

            @Override
            public boolean isFrench() {
                return false;
            }

            @Override
            public boolean isDeutsche() {
                return false;
            }

            @Override
            public boolean isSpanish() {
                return true;
            }

            @Override
            public boolean isChinese() {
                return false;
            }
        },
        CHINESE("Chinois") {
            @Override
            public boolean isEnglish() {
                return false;
            }

            @Override
            public boolean isFrench() {
                return false;
            }

            @Override
            public boolean isDeutsche() {
                return false;
            }

            @Override
            public boolean isSpanish() {
                return false;
            }

            @Override
            public boolean isChinese() {
                return true;
            }
        };

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

        public abstract boolean isEnglish();
        public abstract boolean isFrench();
        public abstract boolean isDeutsche();
        public abstract boolean isSpanish();
        public abstract boolean isChinese();

    }
}
