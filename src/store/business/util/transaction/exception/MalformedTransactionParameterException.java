package store.business.util.transaction.exception;

/**
 * <h1>Exception thrown in case of the Client constructor is malformed</h1>
 * <img src="../../../../uml/MalformedTransactionParameterExceptionDiagram.jpg" />
 *
 * @see Throwable
 */
public class MalformedTransactionParameterException extends Throwable {
    /**
     *
     */
    public MalformedTransactionParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedTransactionParameterException(final String message) {
        super(message);
    }
}
