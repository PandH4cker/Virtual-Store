package store.business.util.product.description.exception;

/**
 * <h1>Exception thrown in case of the CharacterName constructor is malformed</h1>
 * <img src="../../../../uml/MalformedCharacterNameParameterExceptionDiagram.jpg" />
 *
 * @see Throwable
 */
public class MalformedCharacterNameParameterException extends Throwable{
    /**
     *
     */
    public MalformedCharacterNameParameterException() {
        super();
    }

    /**
     * The constructor of the class
     * @param message The message describing the error
     */
    public MalformedCharacterNameParameterException(final String message) {
        super(message);
    }
}
