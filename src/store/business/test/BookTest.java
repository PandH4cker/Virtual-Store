package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.product.Book;
import store.business.util.product.description.CharacterName;
import store.business.util.product.description.exception.MalformedCharacterNameParameterException;
import store.business.util.product.exception.MalformedBookParameterException;
import store.business.util.product.exception.MalformedProductParameterException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookTest {
    private Book book = null;

    @DisplayName("Constructor of Book Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void bookConstructorTest(final String title,
                             final String price,
                             final String uniqueID,
                             final String numberLeft,
                             final String image,
                             final String author,
                             final String language,
                             final String numberOfPages) {
        try { this.book = new Book(title, price, uniqueID, numberLeft, image, author, language, numberOfPages); }
        catch (MalformedCharacterNameParameterException | MalformedProductParameterException e) { e.printStackTrace(); }

        assertAll("Livre non conforme",
                () -> assertEquals(title, this.book.getName()),
                () -> assertEquals(Integer.parseInt(price), this.book.getPrice()),
                () -> assertEquals(Long.parseLong(uniqueID), this.book.getUniqueID()),
                () -> assertEquals(Integer.parseInt(numberLeft), this.book.getNumberLeft()),
                () -> assertEquals(image, this.book.getImagePath()),
                () -> assertEquals(author, this.book.getAuthor().toString()),
                () -> assertEquals(language, this.book.getLanguage().toString()),
                () -> assertEquals(Integer.parseInt(numberOfPages), this.book.getNumberOfPages())
        );

        assertEquals(this.book.getName()
                +"\n" +this.book.getAuthor()
                +"\n" +this.book.getLanguage()
                +"\n" +this.book.getNumberOfPages()+" pages"
                +"\n" +this.book.getPrice()+" €"
                +"\n" +this.book.getNumberLeft()+" restants", this.book.toString());

        assertAll("Données livre non conformes",
                BookTest::executeTitleTests,
                BookTest::executePriceTests,
                BookTest::executeUIDTests,
                BookTest::executeNumberLeftTests,
                BookTest::executeImageTests,
                BookTest::executeAuthorTests,
                BookTest::executeLanguageTests,
                BookTest::executeNumberOfPagesTests
        );
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][]{
                        {
                            "L'autoroute du millionaire : La voie express vers la richesse",
                            "25",
                            "2849334650",
                            "100",
                            "files/resources/image/LautorouteMillionaire.jpg",
                            "MJ DeMarco",
                            "Francais",
                            "352"
                        }
                });
    }

    private static void executeTitleTests() {
        assertAll("Titre non conforme",
                () -> assertThrows(NullPointerException.class,
                        () -> new Book(null,
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executePriceTests() {
        assertAll("Prix non conforme",
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                -1,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executeUIDTests() {
        assertAll("UID non conforme",
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                -1,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executeNumberLeftTests() {
        assertAll("Nombre manquant non conforme",
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                -1,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executeImageTests() {
        assertAll("Image non conforme",
                () -> assertThrows(NullPointerException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                null,
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "test.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/test",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertDoesNotThrow(
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/test.png",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertDoesNotThrow(
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/test.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executeAuthorTests() {
        assertAll("Nom personnage non conforme",
                () -> assertThrows(NullPointerException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName(null, "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(NullPointerException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", null),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedCharacterNameParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedCharacterNameParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", ""),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedCharacterNameParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("11MJ", "DeMarco"),
                                Book.LanguageBook.FRENCH,
                                352)
                ),
                () -> assertThrows(MalformedCharacterNameParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMa11rco"),
                                Book.LanguageBook.FRENCH,
                                352)
                )
        );
    }

    private static void executeLanguageTests() {
        assertAll("Langage non conforme",
                () -> assertThrows(MalformedBookParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                null,
                                352)
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                "25",
                                "2849334650",
                                "100",
                                "files/resources/image/LautorouteMillionaire.jpg",
                                "MJ DeMarco",
                                "Neerlandais",
                                "352")
                )
        );
    }

    private static void executeNumberOfPagesTests() {
        assertAll("Nombre de pages non conforme",
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                25,
                                2849334650L,
                                100,
                                "files/resources/image/LautorouteMillionaire.jpg",
                                new CharacterName("MJ", "DeMarco"),
                                null,
                                -1)
                )
        );
    }

}