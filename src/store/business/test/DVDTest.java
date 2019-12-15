package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.product.DVD;
import store.business.util.product.description.CharacterName;
import store.business.util.product.description.exception.MalformedCharacterNameParameterException;
import store.business.util.product.exception.MalformedDVDParameterException;
import store.business.util.product.exception.MalformedProductParameterException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class DVDTest {
    private DVD dvd = null;

    @DisplayName("Constructor of DVD Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void dvdConstructorTest(final String title,
                            final String price,
                            final String uniqueID,
                            final String numberLeft,
                            final String image,
                            final String actors,
                            final String genre,
                            final String duration) {
        try { this.dvd = new DVD(title, price, uniqueID, numberLeft, image, actors, genre, duration); }
        catch (MalformedCharacterNameParameterException | MalformedProductParameterException e) { e.printStackTrace(); }

        populateTests(title, price, uniqueID, numberLeft, image, actors, genre, duration);
    }

    private void populateTests(String title, String price, String uniqueID, String numberLeft, String image, String actors, String genre, String duration) {
        assertAll("DVD non conforme", executeConformityTests(title, price, uniqueID, numberLeft, image, actors, genre, duration));

        assertEquals(this.dvd.getName()
                +"\n" +this.dvd.getActors()
                +"\n" +this.dvd.getDuration()+" minutes"
                +"\n" +this.dvd.getPrice()+" €"
                +"\n" +this.dvd.getNumberLeft()+" restants", this.dvd.toString());

        assertAll("Données DVD non conformes",
                DVDTest::executeTitleTests,
                DVDTest::executePriceTests,
                DVDTest::executeUIDTests,
                DVDTest::executeNumberLeftTests,
                DVDTest::executeImagePathTests,
                DVDTest::executeActorsTests,
                DVDTest::executeGenreTests,
                DVDTest::executeDurationTests
        );
    }

    private Executable[] executeConformityTests(String title, String price, String uniqueID, String numberLeft, String image, String actors, String genre, String duration) {
        return new Executable[]{
                () -> assertEquals(title, this.dvd.getName()),
                () -> assertEquals(price, String.valueOf(this.dvd.getPrice())),
                () -> assertEquals(uniqueID, String.valueOf(this.dvd.getUniqueID())),
                () -> assertEquals(numberLeft, String.valueOf(this.dvd.getNumberLeft())),
                () -> assertEquals(image, this.dvd.getImagePath()),
                () -> {
                    List<CharacterName> actorsList = new ArrayList<>();
                    for (String s : actors.split(",")) {
                        String[] splittedS = s.split("\\s");
                        actorsList.add(new CharacterName(splittedS[0], splittedS[1]));
                    }
                    Collections.sort(actorsList);
                    for (int i = 0, actorsListSize = actorsList.size(); i < actorsListSize; i++) {
                        CharacterName cn = actorsList.get(i);
                        assertEquals(cn.toString(), this.dvd.getActors().get(i).toString());
                    }

                },
                () -> assertEquals(genre, this.dvd.getGenre().toString()),
                () -> assertEquals(duration, String.valueOf(this.dvd.getDuration()))
        };
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][] {
                        {
                            "Ça",
                            "13",
                            "660775668448474",
                            "100",
                            "files/resources/image/Ça.jpg",
                            "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                            "Fantaisie",
                            "128"
                        }
                });
    }

    private static void executeTitleTests() {
        assertAll("Titre non conforme",
                () -> assertThrows(NullPointerException.class, () ->
                        new DVD(null,
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertDoesNotThrow(() -> new DVD(
                        "Ça",
                        "13",
                        "660775668448474",
                        "100",
                        "files/resources/image/Ça.jpg",
                        "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                        "Fantaisie",
                        "128")
                )
        );
    }

    private static void executePriceTests() {
        assertAll("Prix non conforme",
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                null,
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "-1",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                )
        );
    }

    private static void executeUIDTests() {
        assertAll("UID non conforme",
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                null,
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                "",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "-1",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                )
        );
    }

    private static void executeNumberLeftTests() {
        assertAll("Nombre manquant non conforme",
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                null,
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "-1",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                )
        );
    }

    private static void executeImagePathTests() {
        assertAll("Image non conforme",
                () -> assertThrows(NullPointerException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                null,
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "test",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "test.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/test",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "128")
                )
        );
    }

    private static void executeActorsTests() {
        assertAll("Acteurs non conformes",
                () -> assertThrows(NullPointerException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                null,
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(ArrayIndexOutOfBoundsException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "",
                                "Fantaisie",
                                "128")
                ),
                () -> assertThrows(MalformedCharacterNameParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "111 111",
                                "Fantaisie",
                                "128")
                )
        );
    }

    private static void executeGenreTests() {
        assertAll("Genre non conforme",
                () -> assertThrows(MalformedDVDParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                null,
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "qzegreqagr",
                                "128")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "qzegreqagr",
                                "128")
                )
        );
    }

    private static void executeDurationTests() {
        assertAll("Durée non conforme",
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                null)
                ),
                () -> assertThrows(NumberFormatException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "")
                ),
                () -> assertThrows(MalformedProductParameterException.class, () ->
                        new DVD("Ça",
                                "13",
                                "660775668448474",
                                "100",
                                "files/resources/image/Ça.jpg",
                                "Bill Skarsgard,Jaeden Lieberher,Finn Wolfhard,Jack Dylan Grazer,Sophia Lillis",
                                "Fantaisie",
                                "-1")
                )
        );
    }
}