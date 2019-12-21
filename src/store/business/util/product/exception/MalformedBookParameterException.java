package store.business.util.product.exception;

/**
 * <h1>Exception thrown in case of the Book constructor is malformed</h1>
 * <img src="../../../../uml/MalformedBookParameterExceptionDiagram.jpg" />
 *
 * @see MalformedProductParameterException
 */
public class MalformedBookParameterException extends MalformedProductParameterException {
    /**
     *
     */
    public MalformedBookParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedBookParameterException(final String message) {
        super(message);
    }
}
