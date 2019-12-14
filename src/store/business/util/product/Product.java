package store.business.util.product;

import store.business.gui.model.Model;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;
import store.business.util.product.exception.MalformedProductParameterException;
import store.business.util.product.exception.NoMoreOfThisProductException;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>The Product object</h1>
 * <p>
 *     A product is an object that has:
 *     <li>A category</li>
 *     <li>A name</li>
 *     <li>A price</li>
 *     <li>An UID</li>
 *     <li>A number left in the stock</li>
 *     <li>An image path</li>
 *     <li>A hash code</li>
 *     It inherits of the Comparable<E> class, it can be sorted by price
 * </p>
 * <img src="../../../../uml/Product.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Comparable<Product>
 * @see ProductCategory
 * @see Logger
 */
public abstract class Product implements Comparable<Product>, Model<MalformedProductParameterException> {
    protected final Logger logger = LoggerFactory.getLogger("Product");
    protected final ProductCategory productCategory;
    protected final String name;
    protected final int price;
    protected final long uniqueID;
    protected int numberLeft;
    protected final String imagePath;
    private final int cachedHashCode;

    /**
     * Initialize the products' categories
     * @param productCategory ProductCategory The category of this product
     * @param name String The name of this product
     * @param price int The price of this product
     * @param uniqueID long The UID of this product
     * @param numberLeft int The number left in the stock of this product
     * @param imagePath String The image path of this product
     */
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

    /**
     * Compute hash code
     * @return int The hash code of this product
     */
    private int computeHashCode() {
        int result = this.productCategory.hashCode();
        result = 31 * result + this.name.hashCode();
        result = 31 * result + this.price;
        result = 31 * result +  Long.hashCode(this.uniqueID);
        result = 31 * result + this.numberLeft;
        result = 31 * result + this.imagePath.hashCode();
        return result;
    }

    /**
     * Overriding the equals method of the Object class
     * Assert if two products are equals
     * @param other Object The other object to compare to
     * @return boolean True|False
     * @see Object
     * @see Override
     */
    @Override
    public boolean equals(final Object other) {
        if(this == other) return true;
        if(!(other instanceof Product)) return false;

        final Product otherProduct = (Product) other;
        return this.uniqueID == otherProduct.getUniqueID();
    }

    /**
     * Overriding compareTo method of the Comparable class
     * Compare by price
     * @param other Product The other product to compare to
     * @return int (-1, 0, 1) --> (Inferior, Equal, Superior)
     * @see Override
     */
    @Override
    public int compareTo(Product other) {
        return Integer.compare(this.price, other.price);
    }

    /**
     * Getter of the name
     * @return String The name of the product
     */
    public String getName() {
        return this.name;
    }

    /**
     * Getter of the image path
     * @return String The image path of the product
     */
    public String getImagePath() {
        return this.imagePath;
    }

    /**
     * Getter of the UID
     * @return long The UID of the product
     */
    public long getUniqueID() {
        return this.uniqueID;
    }

    /**
     * Getter of the price
     * @return int The price of the product
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Getter of the number left
     * @return int The number left in the stock of the product
     */
    public int getNumberLeft() {
        return this.numberLeft;
    }

    /**
     * Overriding hashCode method of the Object class
     * @return int The hash code of the product
     * @see Override
     */
    @Override
    public int hashCode() {
        return this.cachedHashCode;
    }

    /**
     * Getter of the category
     * @return ProductCategory The category of the product
     * @see ProductCategory
     */
    public ProductCategory getProductCategory() {
        return this.productCategory;
    }

    /**
     * Take an amount of the product from the stock
     * @param amount The amount taken
     * @throws NoMoreOfThisProductException No more of this product
     * @exception NoMoreOfThisProductException No more of this product
     * @see NoMoreOfThisProductException
     */
    public void tookProduct(final int amount) throws NoMoreOfThisProductException {
        if(this.numberLeft - amount >= 0)
            this.numberLeft -= amount;
        else throw new NoMoreOfThisProductException("No More Of This Product [" + this + "]");
    }

    @Override
    public void validate() throws MalformedProductParameterException {
        List<String> errors = new ArrayList<>();

        boolean passes = ensureNotNull(this.productCategory, "Product Category is null", errors);
        if (passes) {
            passes = false;
            for(ProductCategory p : ProductCategory.values())
                if(this.productCategory.name().equals(p.name())) {
                    passes = true;
                    break;
                }
            if (!passes) errors.add("Product Category malformed");
        }

        if (!hasContent(this.name)) errors.add("Name/Title has no content.");
        if (!hasContent(this.imagePath)) errors.add("Image Path has no content.");

        passes = ensureNotNull(this.price, "Price is null", errors);
        if (passes)
            if (this.price < 0) errors.add("Price is negative");

        passes = ensureNotNull(this.uniqueID, "UID is null", errors);
        if (passes)
            if (this.uniqueID < 0) errors.add("UID is negative");

        passes = ensureNotNull(this.numberLeft, "Number Left is null", errors);
        if (passes)
            if (this.numberLeft < 0) errors.add("Number Left is negative");

        passes = this.imagePath.matches("^(files/resources/image/)([a-zA-Z0-9_\\-Çç]+)\\.(jpg|png)");
        if(!passes) errors.add("Image Path malformed");

        if(!errors.isEmpty()) {
            MalformedProductParameterException ex = new MalformedProductParameterException();
            for(String error : errors)
                ex.addSuppressed(new MalformedProductParameterException(error));
            throw ex;
        }
    }

    /**
     * <h1>The product category enumeration</h1>
     * <p>
     *     A product category is an enumeration of several category like:
     *     <li>Book</li>
     *     <li>DVD</li>
     *     <li>Video Game</li>
     * </p>
     */
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

        /**
         * Initialize the category name of the product
         * @param categoryName String The category name of the product
         */
        ProductCategory(final String categoryName) {
            this.categoryName = categoryName;
        }

        /**
         * Overriding toString method of the Object class
         * @return String The category name
         * @see Override
         */
        @Override
        public String toString() {
            return this.categoryName;
        }

        public abstract boolean isBook();
        public abstract boolean isDVD();
        public abstract boolean isVideoGame();

    }
}