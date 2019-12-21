package store.business.util.client.exception;

/**
 * <h1>Exception thrown in case of the Client constructor is malformed</h1>
 * <img src="../../../../uml/MalformedClientParameterExceptionDiagram.jpg" />
 *
 * @see Throwable
 */
public class MalformedClientParameterException extends Throwable {
    /**
     *
     */
    public MalformedClientParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedClientParameterException(final String message) {
        super(message);
    }
}
