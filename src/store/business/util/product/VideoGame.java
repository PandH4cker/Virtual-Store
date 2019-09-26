package store.business.util.product;

public class VideoGame extends Product {
    private final VideoGameGenre genre;
    private final VideoGamePlatform platform;

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
        this.logger.log("New Video Game created [" + this + "]");
    }


    public VideoGameGenre getGenre() {
        return this.genre;
    }

    public VideoGamePlatform getPlatform() {
        return this.platform;
    }


    @Override
    public String toString() {
        return this.getName()
                +"\n" +this.genre
                +"\n" +this.platform
                +"\n" +this.price+" €"
                +"\n" +this.numberLeft+" restants";
    }

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

        VideoGameGenre(final String genreName) {
            this.genreName = genreName;
        }

        @Override
        public String toString() {
            return this.genreName;
        }

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

        VideoGamePlatform(final String platformName) {
            this.platformName = platformName;
        }

        @Override
        public String toString() {
            return this.platformName;
        }

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
