package store.business.util.product;

import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

public abstract class Product {
    protected final Logger logger = LoggerFactory.getLogger("Product");
    protected final ProductCategory productCategory;
    protected final String name;
    protected final int price;
    protected final long uniqueID;
    protected int numberLeft;
    protected final String imagePath;
    private final int cachedHashCode;

    Product(final ProductCategory productCategory,
            final String name,
            final int price,
            final long uniqueID,
            final int numberLeft,
            final String imagePath) {
        this.productCategory = productCategory;
        this.name = name;
        this.price = price;
        this.uniqueID = uniqueID;
        this.numberLeft = numberLeft;
        this.imagePath = imagePath;
        this.cachedHashCode = computeHashCode();
    }

    private int computeHashCode() {
        int result = this.productCategory.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.price;
        result = 31 * result +  Long.hashCode(this.uniqueID);
        result = 31 * result + this.numberLeft;
        result = 31 * result + this.imagePath.hashCode();
        return result;
    }

    @Override
    public boolean equals(final Object other) {
        if(this == other) return true;
        if(!(other instanceof Product)) return false;

        final Product otherProduct = (Product) other;
        return this.uniqueID == otherProduct.getUniqueID();
    }

    public String getName() {
        return this.name;
    }

    public String getImagePath() {
        return this.imagePath;
    }

    public long getUniqueID() {
        return this.uniqueID;
    }

    public int getPrice() {
        return this.price;
    }

    public int getNumberLeft() {
        return this.numberLeft;
    }

    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    public ProductCategory getProductCategory() {
        return this.productCategory;
    }

    public enum ProductCategory {
        BOOK("Livre") {
            @Override
            public boolean isBook() {
                return true;
            }

            @Override
            public boolean isDVD() {
                return false;
            }

            @Override
            public boolean isVideoGame() {
                return false;
            }
        },
        DVD("DVD") {
            @Override
            public boolean isBook() {
                return false;
            }

            @Override
            public boolean isDVD() {
                return true;
            }

            @Override
            public boolean isVideoGame() {
                return false;
            }
        },
        VIDEOGAME("Jeux Vidéo") {
            @Override
            public boolean isBook() {
                return false;
            }

            @Override
            public boolean isDVD() {
                return false;
            }

            @Override
            public boolean isVideoGame() {
                return true;
            }
        };

        private final String categoryName;

        ProductCategory(final String categoryName) {
            this.categoryName = categoryName;
        }

        @Override
        public String toString() {
            return this.categoryName;
        }

        public abstract boolean isBook();
        public abstract boolean isDVD();
        public abstract boolean isVideoGame();

        //TODO Improve this enum class
    }
}
