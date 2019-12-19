package store.business.util.transaction.exception;

public class MalformedTransactionParameterException extends Throwable {
    public MalformedTransactionParameterException() {
        super();
    }
    public MalformedTransactionParameterException(final String message) {
        super(message);
    }
}
