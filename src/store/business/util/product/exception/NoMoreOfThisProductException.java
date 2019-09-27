package store.business.util.product.exception;

/**
 * <h1>The No More Of This Product Exception</h1>
 * <p>
 *     Exception thrown when a product is out of stock
 *     It inherits of the {@code public class Throwable} class
 *     It means it can be thrown as an Exception
 * </p>
 * @see Throwable
 */
public class NoMoreOfThisProductException extends Throwable {
    public NoMoreOfThisProductException(final String message) {
        super(message);
    }
}
