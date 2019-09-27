package store.business.util.product;

import store.business.util.logger.level.Level;

/**
 * <h1>The Video Game object</h1>
 * <p>
 *     A Video Game is an object that has:
 *     <li>A genre</li>
 *     <li>A platform</li>
 *     It inherits of the {@code public abstract class Product} class
 * </p>
 * <img src="../../../../uml/VideoGame.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Product
 * @see VideoGameGenre
 * @see VideoGamePlatform
 */
public class VideoGame extends Product {
    private final VideoGameGenre genre;
    private final VideoGamePlatform platform;

    /**
     * Initializes the Video games' attributes
     * @param title String The title of the Video Game
     * @param price int The price of the Video Game
     * @param uniqueID long The UID of the Video Game
     * @param numberLeft int The number left in the stock of the Video Game
     * @param image String The image path of the Video Game
     * @param genre VideoGameGenre The genre of the Video Game
     * @param platform VideoGamePlatform The platform of the Video Game
     * @see VideoGameGenre
     * @see VideoGamePlatform
     */
    public VideoGame(final String title,
                     final int price,
                     final long uniqueID,
                     final int numberLeft,
                     final String image,
                     final VideoGameGenre genre,
                     final VideoGamePlatform platform) {
        super(ProductCategory.VIDEOGAME, title, price, uniqueID, numberLeft, image);
        this.genre = genre;
        this.platform = platform;
        this.logger.log("New Video Game Created [" + this + "]", Level.INFO);
    }

    public VideoGame(final String title,
                     final String price,
                     final String uniqueID,
                     final String numberLeft,
                     final String image,
                     final String genre,
                     final String platform) {
        super(ProductCategory.VIDEOGAME, title, Integer.parseInt(price), Long.parseLong(uniqueID),
              Integer.parseInt(numberLeft), image);
        this.genre = VideoGameGenre.toVideoGameGenre(genre);
        this.platform = VideoGamePlatform.toVideoGamePlatform(platform);
        this.logger.log("New Video Game created [" + this + "]", Level.INFO);
    }

    /**
     * Getter of the genre
     * @return VideoGameGenre The genre of the Video Game
     * @see VideoGameGenre
     */
    public VideoGameGenre getGenre() {
        return this.genre;
    }

    /**
     * Getter of the platform
     * @return VideoGamePlatform The platform of the Video Game
     * @see VideoGamePlatform
     */
    public VideoGamePlatform getPlatform() {
        return this.platform;
    }

    /**
     * Overriding the toString method of the Object class
     * @return String The description of the Video Game
     */
    @Override
    public String toString() {
        return this.getName()
                +"\n" +this.genre
                +"\n" +this.platform
                +"\n" +this.price+" €"
                +"\n" +this.numberLeft+" restants";
    }

    /**
     * <h1>The Video Game Genre enumeration</h1>
     * <p>
     *     A Video Game Genre is an enumeration of several genre like:
     *     <li>Arcade</li>
     *     <li>Adventure</li>
     *     <li>Roleplay</li>
     *     <li>MMO</li>
     *     <li>Educative</li>
     *     <li>Strategy</li>
     * </p>
     */
    public enum VideoGameGenre {
        ARCADE("Arcade") {
            @Override
            public boolean isArcade() {
                return true;
            }

            @Override
            public boolean isAventure() {
                return false;
            }

            @Override
            public boolean isRoleplay() {
                return false;
            }

            @Override
            public boolean isMMO() {
                return false;
            }

            @Override
            public boolean isEducative() {
                return false;
            }

            @Override
            public boolean isStrategy() {
                return false;
            }
        },
        ADVENTURE("Aventure") {
            @Override
            public boolean isArcade() {
                return false;
            }

            @Override
            public boolean isAventure() {
                return true;
            }

            @Override
            public boolean isRoleplay() {
                return false;
            }

            @Override
            public boolean isMMO() {
                return false;
            }

            @Override
            public boolean isEducative() {
                return false;
            }

            @Override
            public boolean isStrategy() {
                return false;
            }
        },
        ROLEPLAY("Rôle") {
            @Override
            public boolean isArcade() {
                return false;
            }

            @Override
            public boolean isAventure() {
                return false;
            }

            @Override
            public boolean isRoleplay() {
                return true;
            }

            @Override
            public boolean isMMO() {
                return false;
            }

            @Override
            public boolean isEducative() {
                return false;
            }

            @Override
            public boolean isStrategy() {
                return false;
            }
        },
        MMO("Multi-joueurs") {
            @Override
            public boolean isArcade() {
                return false;
            }

            @Override
            public boolean isAventure() {
                return false;
            }

            @Override
            public boolean isRoleplay() {
                return false;
            }

            @Override
            public boolean isMMO() {
                return true;
            }

            @Override
            public boolean isEducative() {
                return false;
            }

            @Override
            public boolean isStrategy() {
                return false;
            }
        },
        EDUCATIVE("Educatif") {
            @Override
            public boolean isArcade() {
                return false;
            }

            @Override
            public boolean isAventure() {
                return false;
            }

            @Override
            public boolean isRoleplay() {
                return false;
            }

            @Override
            public boolean isMMO() {
                return false;
            }

            @Override
            public boolean isEducative() {
                return true;
            }

            @Override
            public boolean isStrategy() {
                return false;
            }
        },
        STRATEGY("Stratégie") {
            @Override
            public boolean isArcade() {
                return false;
            }

            @Override
            public boolean isAventure() {
                return false;
            }

            @Override
            public boolean isRoleplay() {
                return false;
            }

            @Override
            public boolean isMMO() {
                return false;
            }

            @Override
            public boolean isEducative() {
                return false;
            }

            @Override
            public boolean isStrategy() {
                return true;
            }
        };

        private final String genreName;

        /**
         * Initialize the genre name of the Video Game
         * @param genreName String The genre name of the Video Game
         */
        VideoGameGenre(final String genreName) {
            this.genreName = genreName;
        }

        /**
         * Overriding the toString method of the Object class
         * @return String The genre name of the Video Game
         */
        @Override
        public String toString() {
            return this.genreName;
        }

        /**
         * Convert string genre name to a Video Game genre value
         * @param s String The genre name
         * @return VideoGameGenre The VideoGameGenre value from the string
         * @exception RuntimeException Not a genre known
         * @see RuntimeException
         */
        public static VideoGameGenre toVideoGameGenre(String s) {
            for(VideoGameGenre g : VideoGameGenre.values()) if(g.toString().equalsIgnoreCase(s)) return g;
            throw new RuntimeException("Not a genre known");
        }

        public abstract boolean isArcade();
        public abstract boolean isAventure();
        public abstract boolean isRoleplay();
        public abstract boolean isMMO();
        public abstract boolean isEducative();
        public abstract boolean isStrategy();
    }

    /**
     * <h1>The Video Game Platform enumeration</h1>
     * <p>
     *     A Video Game Platform is an enumeration of several genre like:
     *     <li>Nintendo</li>
     *     <li>PC</li>
     *     <li>Playstation</li>
     *     <li>XBOX</li>
     * </p>
     */
    public enum VideoGamePlatform {
        NINTENDO("Nintendo") {
            @Override
            public boolean isNintendo() {
                return true;
            }

            @Override
            public boolean isPC() {
                return false;
            }

            @Override
            public boolean isPlaystation() {
                return false;
            }

            @Override
            public boolean isXBOX() {
                return false;
            }
        },
        PC("Microsoft Windows") {
            @Override
            public boolean isNintendo() {
                return false;
            }

            @Override
            public boolean isPC() {
                return true;
            }

            @Override
            public boolean isPlaystation() {
                return false;
            }

            @Override
            public boolean isXBOX() {
                return false;
            }
        },
        PLAYSTATION("PS4") {
            @Override
            public boolean isNintendo() {
                return false;
            }

            @Override
            public boolean isPC() {
                return false;
            }

            @Override
            public boolean isPlaystation() {
                return true;
            }

            @Override
            public boolean isXBOX() {
                return false;
            }
        },
        XBOX("XBOX ONE X") {
            @Override
            public boolean isNintendo() {
                return false;
            }

            @Override
            public boolean isPC() {
                return false;
            }

            @Override
            public boolean isPlaystation() {
                return false;
            }

            @Override
            public boolean isXBOX() {
                return true;
            }
        };

        private final String platformName;

        /**
         * Initializes the platform name of the Video Game
         * @param platformName String The platform name of the Video Game
         */
        VideoGamePlatform(final String platformName) {
            this.platformName = platformName;
        }

        /**
         * Overriding the toString method of the Object class
         * @return String The platform name of the Video Game
         * @see Override
         */
        @Override
        public String toString() {
            return this.platformName;
        }

        /**
         * Convert string platform name to a Video Game platform enum value
         * @param s String The platform name
         * @return VideoGamePlatform The VideoGamePlatform value from the string
         * @exception RuntimeException Not a platform known
         * @see RuntimeException
         */
        public static VideoGamePlatform toVideoGamePlatform(String s) {
            for(VideoGamePlatform p : VideoGamePlatform.values()) if(p.toString().equalsIgnoreCase(s)) return p;
            throw new RuntimeException("Not a platform known");
        }

        public abstract boolean isNintendo();
        public abstract boolean isPC();
        public abstract boolean isPlaystation();
        public abstract boolean isXBOX();
    }
}
