package store.business.util.product;

import store.business.util.logger.level.Level;
import store.business.util.product.description.CharacterName;

/**
 * <h1>The book object</h1>
 * <p>
 *     A book is an object that has:
 *     <li>An author</li>
 *     <li>A language</li>
 *     <li>A number of pages</li>
 *     It inherits of the {@code public abstract class Product} class
 * </p>
 * <img src="../../../../uml/Book.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Product
 * @see CharacterName
 * @see LanguageBook
 */
public class Book extends Product {
    private final CharacterName author;
    private final LanguageBook language;
    private final int numberOfPages;

    /**
     * Initialize the books' attributes
     * @param title String The title of the book
     * @param price int The price of the book
     * @param uniqueID long The UID of the book (ISBN)
     * @param numberLeft int The number in the stock of this book
     * @param image String The image path of the book
     * @param author CharacterName The author of the book
     * @param language LanguageBook The language of the book
     * @param numberOfPages int The number of pages of the book
     * @see CharacterName
     * @see LanguageBook
     */
    public Book(final String title,
                final int price,
                final long uniqueID,
                final int numberLeft,
                final String image,
                final CharacterName author,
                final LanguageBook language,
                final int numberOfPages) {
        super(ProductCategory.BOOK, title, price, uniqueID, numberLeft, image);
        this.author = author;
        this.language = language;
        this.numberOfPages = numberOfPages;
        this.logger.log("New Book Created [" + this + "]", Level.INFO);
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
              Integer.parseInt(numberLeft), image);
        final String[] splittedAuthor = author.split(" ");
        this.author = new CharacterName(splittedAuthor[0], splittedAuthor[1]);
        this.language = LanguageBook.toLanguageBook(language);
        this.numberOfPages = Integer.parseInt(numberOfPages);
        this.logger.log("New Book Created [" + this + "]", Level.INFO);
    }

    /**
     * Getter of the author
     * @return CharacterName The author of the book
     * @see CharacterName
     */
    public CharacterName getAuthor() {
        return this.author;
    }

    /**
     * Getter of the number of pages
     * @return int The number of pages
     */
    public int getNumberOfPages() {
        return this.numberOfPages;
    }

    /**
     * Getter of the language
     * @return LanguageBook The language of the book
     * @see LanguageBook
     */
    public LanguageBook getLanguage() {
        return this.language;
    }

    /**
     * Overriding the toString method of the Object class
     * @return String The description of the book
     */
    @Override
    public String toString() {
        return this.getName()
                +"\n" +this.author
                +"\n" +this.language
                +"\n" +this.numberOfPages+" pages"
                +"\n" +this.price+" €"
                +"\n" +this.numberLeft+" restants";
    }

    /**
     * <h1>The LanguageBook enumeration</h1>
     * <p>
     *     A LanguageBook is an enumeration of several languages like:
     *     <li>English</li>
     *     <li>French</li>
     *     <li>Deutsche</li>
     *     <li>Spanish</li>
     *     <li>Chinese</li>
     * </p>
     */
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
        FRENCH("Francais") {
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

        /**
         * Initialize the language name of the book
         * @param languageName String The language of the book
         */
        LanguageBook(final String languageName) {
            this.languageName = languageName;
        }

        /**
         * Overriding toString method of Object class
         * @return String The language name
         */
        @Override
        public String toString() {
            return this.languageName;
        }

        /**
         * Convert string language name to a language book enum value
         * @param s String The language name of the language enum
         * @return LanguageBook The LanguageBook value from the string
         * @exception RuntimeException Not a language known
         * @see RuntimeException
         */
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