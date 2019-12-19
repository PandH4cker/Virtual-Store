package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.product.VideoGame;
import store.business.util.product.exception.MalformedProductParameterException;
import store.business.util.product.exception.MalformedVideoGameParameterException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class VideoGameTest {
    private VideoGame videoGame = null;

    @DisplayName("Constructor of VideoGame Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void videoGameConstructorTest(final String title,
                                  final String price,
                                  final String uniqueID,
                                  final String numberLeft,
                                  final String image,
                                  final String genre,
                                  final String platform) {
        try { this.videoGame = new VideoGame(title, price, uniqueID, numberLeft, image, genre, platform); }
        catch (MalformedProductParameterException e) { e.printStackTrace(); }

        populateTests(title, price, uniqueID, numberLeft, image, genre, platform);
    }

    private void populateTests(String title, String price, String uniqueID, String numberLeft, String image, String genre, String platform) {
        assertAll("Jeux Vidéo non conforme", executeConformityTests(title, price, uniqueID, numberLeft, image, genre, platform));

        assertEquals(this.videoGame.getName()
                +"\n" +this.videoGame.getGenre()
                +"\n" +this.videoGame.getPlatform()
                +"\n" +this.videoGame.getPrice()+" €"
                +"\n" +this.videoGame.getNumberLeft()+" restants", this.videoGame.toString());

        assertAll("Données Jeux Vidéo non conformes",
                VideoGameTest::executeTitleTests,
                VideoGameTest::executePriceTests,
                VideoGameTest::executeUIDTests,
                VideoGameTest::executeNumberLeftTests,
                VideoGameTest::executeImagePathTests,
                VideoGameTest::executeGenreTests,
                VideoGameTest::executePlatformTests
        );
    }

    private Executable[] executeConformityTests(String title, String price, String uniqueID, String numberLeft, String image, String genre, String platform) {
        return new Executable[]{() -> assertEquals(title, this.videoGame.getName()),
                () -> assertEquals(price, String.valueOf(this.videoGame.getPrice())),
                () -> assertEquals(uniqueID, String.valueOf(this.videoGame.getUniqueID())),
                () -> assertEquals(numberLeft, String.valueOf(this.videoGame.getNumberLeft())),
                () -> assertEquals(image, this.videoGame.getImagePath()),
                () -> assertEquals(genre, this.videoGame.getGenre().toString()),
                () -> assertEquals(platform, this.videoGame.getPlatform().toString())};
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][]{
                        {
                            "Cyberpunk 2077",
                            "60",
                            "1184658967007",
                            "100",
                            "files/resources/image/cyberpunk-2077-cover.jpg",
                            "Rôle",
                            "XBOX ONE X"
                        }
                });
    }

    private static void executeTitleTests() {
        assertAll("Titre non conforme",
                () -> assertThrows(NullPointerException.class,
                        () -> new VideoGame(null,
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                )
        );
    }

    private static void executePriceTests() {
        assertAll("Prix non conforme",
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                null,
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "-1",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                )
        );
    }

    private static void executeUIDTests() {
        assertAll("UID non conforme",
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                null,
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "-1",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                )
        );
    }

    private static void executeNumberLeftTests() {
        assertAll("Nombre manquant non conforme",
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                null,
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(NumberFormatException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "-1",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "XBOX ONE X")
                )
        );
    }

    private static void executeImagePathTests() {
        assertAll("Image non conforme",
                () -> assertThrows(NullPointerException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                null,
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "test",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "test.jpg",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/",
                                "Rôle",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/test",
                                "Rôle",
                                "XBOX ONE X")
                )
        );
    }

    private static void executeGenreTests() {
        assertAll("Genre non conforme",
                () -> assertThrows(MalformedVideoGameParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                null,
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "",
                                "XBOX ONE X")
                ),
                () -> assertThrows(MalformedProductParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "ervgeztg",
                                "XBOX ONE X")
                )
        );
    }

    private static void executePlatformTests() {
        assertAll("Plateforme non conforme",
                () -> assertThrows(MalformedVideoGameParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                null)
                ),
                () -> assertThrows(MalformedVideoGameParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "")
                ),
                () -> assertThrows(MalformedVideoGameParameterException.class,
                        () -> new VideoGame("Cyberpunk 2077",
                                "60",
                                "1184658967007",
                                "100",
                                "files/resources/image/cyberpunk-2077-cover.jpg",
                                "Rôle",
                                "ergezagrze")
                )
        );
    }

}
