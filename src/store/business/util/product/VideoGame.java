package store.business.util.product;

import javax.swing.*;

public class VideoGame extends Product {
    private final VideoGameGenre genre;
    private final VideoGamePlatform platform;

    public VideoGame(final String title,
                     final int price,
                     final int uniqueID,
                     final int numberLeft,
                     final ImageIcon image,
                     final VideoGameGenre genre,
                     final VideoGamePlatform platform) {
        super(ProductCategory.VIDEOGAME, title, price, uniqueID, numberLeft, image);
        this.genre = genre;
        this.platform = platform;
    }

    public VideoGameGenre getGenre() {
        return this.genre;
    }

    public VideoGamePlatform getPlatform() {
        return this.platform;
    }

    public enum VideoGameGenre {
        ARCADE("Arcade"),
        ADVENTURE("Aventure"),
        ROLEPLAY("Rôle"),
        MMO("Multi-joueurs"),
        EDUCATIVE("Educatif"),
        STRATEGY("Stratégie");

        private final String genreName;

        VideoGameGenre(final String genreName) {
            this.genreName = genreName;
        }

        @Override
        public String toString() {
            return this.genreName;
        }
    }

    public enum VideoGamePlatform {
        NINTENDO("Nintendo"),
        PC("Microsoft Windows"),
        PLAYSTATION("PS4"),
        XBOX("XBOX ONE X");

        private final String platformName;

        VideoGamePlatform(final String platformName) {
            this.platformName = platformName;
        }

        @Override
        public String toString() {
            return this.platformName;
        }
    }
}
