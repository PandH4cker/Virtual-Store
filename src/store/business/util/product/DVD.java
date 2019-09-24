package store.business.util.product;

import store.business.util.product.description.CharacterName;

import javax.swing.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class DVD extends Product {
    private ArrayList<CharacterName> actors;
    private final DVDGenre genre;
    private final int duration;


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
        this.logger.log("New DVD Created: " + this);
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
        this.logger.log("New DVD Created: " + this);
    }


    public ArrayList<CharacterName> getActors() {
        return this.actors;
    }

    public DVDGenre getGenre() {
        return this.genre;
    }

    public int getDuration() {
        return this.duration;
    }

    @Override
    public String toString() {
        return this.getName()
               +"\n" +this.actors
               +"\n" +this.duration+" minutes"
               +"\n" +this.price+" €";
    }

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

        DVDGenre(final String genreName) {
            this.genreName = genreName;
        }

        @Override
        public String toString() {
            return this.genreName;
        }

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
