package store.business.util.product.exception;

/**
 * <h1>Exception thrown in case of the Product constructor is malformed</h1>
 * <img src="../../../../uml/MalformedBookParameterExceptionDiagram.jpg" />
 *
 * @see Throwable
 */
public class MalformedProductParameterException extends Throwable {
    /**
     *
     */
    public MalformedProductParameterException () {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedProductParameterException (final String message) {
        super(message);
    }
}
