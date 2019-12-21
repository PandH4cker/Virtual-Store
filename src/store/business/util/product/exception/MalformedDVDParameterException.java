package store.business.util.product.exception;

/**
 * <h1>Exception thrown in case of the DVD constructor is malformed</h1>
 * <img src="../../../../uml/MalformedBookParameterExceptionDiagram.jpg" />
 *
 * @see MalformedProductParameterException
 */
public class MalformedDVDParameterException extends MalformedProductParameterException {
    /**
     *
     */
    public MalformedDVDParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedDVDParameterException(final String message) {
        super(message);
    }
}
