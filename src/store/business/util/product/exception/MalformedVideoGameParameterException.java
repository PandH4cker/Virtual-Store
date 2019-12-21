package store.business.util.product.exception;

/**
 * <h1>Exception thrown in case of the VideoGame constructor is malformed</h1>
 * <img src="../../../../uml/MalformedBookParameterExceptionDiagram.jpg" />
 *
 * @see Throwable
 */
public class MalformedVideoGameParameterException extends MalformedProductParameterException {
    /**
     *
     */
    public MalformedVideoGameParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedVideoGameParameterException(final String message) {
        super(message);
    }
}
