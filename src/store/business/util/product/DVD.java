package store.business.util.product;

import store.business.util.logger.level.Level;
import store.business.util.product.description.CharacterName;

import java.util.ArrayList;
import java.util.Collections;

/**
 * <h1>The DVD object</h1>
 * <p>
 *     A DVD is an object that has:
 *     <li>A list of actors</li>
 *     <li>A genre</li>
 *     <li>A duration</li>
 *     It inherits of the {@code public abstract class Product} class
 * </p>
 * <img src="../../../../uml/DVD.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Product
 * @see CharacterName
 * @see DVDGenre
 */
public class DVD extends Product {
    private ArrayList<CharacterName> actors;
    private final DVDGenre genre;
    private final int duration;

    /**
     * Initializes the DVDs' attributes
     * @param title String The title of the DVD
     * @param price String The price of the DVD
     * @param uniqueID Long The UID of the DVD
     * @param numberLeft int The number in the stock of this DVD
     * @param image String The image path of the DVD
     * @param actors ArrayList<CharacterName> The list of the actors of this DVD
     * @param genre DVDGenre The genre of this DVD
     * @param duration int The duration of the DVD
     */
    public DVD(final String title,
               final int price,
               final long uniqueID,
               final int numberLeft,
               final String image,
               ArrayList<CharacterName> actors,
               final DVDGenre genre,
               final int duration) {
        super(ProductCategory.DVD, title, price, uniqueID, numberLeft, image);
        this.actors = actors;
        Collections.sort(this.actors);
        this.genre = genre;
        this.duration = duration;
        this.logger.log("New DVD Created [" + this + "]", Level.INFO);
    }

    public DVD(final String title,
               final String price,
               final String uniqueID,
               final String numberLeft,
               final String image,
               String actors,
               final String genre,
               final String duration) {
        super(ProductCategory.DVD, title, Integer.parseInt(price), Long.parseLong(uniqueID),
              Integer.parseInt(numberLeft), image);
        ArrayList<CharacterName> actorsList = new ArrayList<>();
        for(String s : actors.split(",")) {
            String[] splittedS = s.split(" ");
            actorsList.add(new CharacterName(splittedS[0], splittedS[1]));
        }
        this.actors = actorsList;
        Collections.sort(this.actors);
        this.genre = DVDGenre.toDVDGenre(genre);
        this.duration = Integer.parseInt(duration);
        this.logger.log("New DVD Created [" + this + "]", Level.INFO);
    }

    /**
     * Getter of the list of the actors
     * @return ArrayList<CharacterName> The list of actors of the DVD
     */
    public ArrayList<CharacterName> getActors() {
        return this.actors;
    }

    /**
     * Getter of the genre
     * @return DVDGenre The genre of the DVD
     */
    public DVDGenre getGenre() {
        return this.genre;
    }

    /**
     * Getter of the duration
     * @return int The duration of the DVD
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Overriding toString method of the Object class
     * @return String The description of the DVD
     * @see Override
     */
    @Override
    public String toString() {
        return this.getName()
               +"\n" +this.actors
               +"\n" +this.duration+" minutes"
               +"\n" +this.price+" €"
               +"\n" +this.numberLeft+" restants";
    }

    /**
     * <h1>The DVDGenre enumeration</h1>
     * <p>
     *     A DVDGenre is an enumeration of several genre like:
     *     <li>Comedy</li>
     *     <li>Action</li>
     *     <li>Drama</li>
     *     <li>Cartoon</li>
     *     <li>Family</li>
     *     <li>Fantasy</li>
     *     <li>Sci-Fi</li>
     *     <li>Horror</li>
     * </p>
     */
    public enum DVDGenre {
        COMEDY("Comédie") {
            @Override
            public boolean isComedy() {
                return true;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        ACTION("Action") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return true;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        DRAMA("Drame") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return true;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        CARTOON("Dessin Animé") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return true;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        FAMILY("Famille") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return true;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        FANTASY("Fantasy") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return true;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        SCIFI("Sci-Fi") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return true;
            }

            @Override
            public boolean isHorror() {
                return false;
            }
        },
        HORROR("Horreur") {
            @Override
            public boolean isComedy() {
                return false;
            }

            @Override
            public boolean isAction() {
                return false;
            }

            @Override
            public boolean isDrama() {
                return false;
            }

            @Override
            public boolean isCartoon() {
                return false;
            }

            @Override
            public boolean isFamily() {
                return false;
            }

            @Override
            public boolean isFantasy() {
                return false;
            }

            @Override
            public boolean isSciFi() {
                return false;
            }

            @Override
            public boolean isHorror() {
                return true;
            }
        };

        private final String genreName;

        /**
         * Initialize the genre name of the DVD
         * @param genreName String The genre name of the DVD
         */
        DVDGenre(final String genreName) {
            this.genreName = genreName;
        }

        /**
         * Overriding toString method of the Object class
         * @return String The genre name
         * @see Override
         */
        @Override
        public String toString() {
            return this.genreName;
        }

        /**
         * Convert string genre name to a DVD genre enum value
         * @param s String The genre name
         * @return DVDGenre The DVDGenre value from the string
         * @exception RuntimeException Not genre known
         * @see RuntimeException
         */
        public static DVDGenre toDVDGenre(String s) {
            for(DVDGenre d : DVDGenre.values()) if(d.toString().equalsIgnoreCase(s)) return d;
            throw new RuntimeException("Not a genre known");
        }

        public abstract boolean isComedy();
        public abstract boolean isAction();
        public abstract boolean isDrama();
        public abstract boolean isCartoon();
        public abstract boolean isFamily();
        public abstract boolean isFantasy();
        public abstract boolean isSciFi();
        public abstract boolean isHorror();
    }
}