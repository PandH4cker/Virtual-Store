package store.business.util.transaction;

import store.business.gui.model.Model;
import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;
import store.business.util.transaction.exception.MalformedTransactionParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>The transaction object</h1>
 * <p>
 *     A transaction is an object that has:
 *     <li>A client UID</li>
 *     <li>A product UID</li>
 *     <li>A number of product</li>
 *     <li>A date</li>
 * </p>
 * <img src="../../../../uml/Transaction.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 * @see Date
 * @see SimpleDateFormat
 */
public class Transaction implements Model<MalformedTransactionParameterException> {
    private final Logger logger = LoggerFactory.getLogger("Transaction");
    private final long clientUID;
    private final long productUID;
    private final int numberOfProduct;
    private final Date transactionDate;

    /**
     * {@value "YYYY-MM-dd HH:mm:ss"}
     */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * Initializes the transactions' attributes
     * @param clientUID long The client UID of the transaction
     * @param productUID long The product UID of the transaction
     * @param numberOfProduct int The number of product of the transaction
     * @param transactionDate Date The date of the transaction
     */
    public Transaction(final long clientUID,
                       final long productUID,
                       final int numberOfProduct,
                       final Date transactionDate) throws MalformedTransactionParameterException {
        this.clientUID = clientUID;
        this.productUID = productUID;
        this.numberOfProduct = numberOfProduct;
        this.transactionDate = transactionDate;
        validate();
        this.logger.log("New Transaction [" + this + "]", Level.INFO);
    }

    /**
     * Getter of the client UID
     * @return long The client UID of the transaction
     */
    public long getClientUID() {
        return this.clientUID;
    }

    /**
     * Getter of the product UID
     * @return long The product UID of the transaction
     */
    public long getProductUID() {
        return this.productUID;
    }

    /**
     * Getter of the number of product
     * @return int The number of product of the transaction
     */
    public int getNumberOfProduct() {
        return this.numberOfProduct;
    }

    /**
     * Getter of the formatted date of the transaction
     * @return String The formatted date of the transaction
     * @see SimpleDateFormat
     */
    public String getTransactionDate() {
        return DATE_FORMAT.format(this.transactionDate);
    }

    /**
     * Overriding toString method of the Object class
     * @return String The description of the transaction
     * @see Override
     * @see SimpleDateFormat
     */
    @Override
    public String toString() {
        return this.getTransactionDate()
               +"\n UID Product: " +this.productUID
               +"\n UID Client: " +this.clientUID
               +"\n Amount: " +this.numberOfProduct;
    }

    @Override
    public void validate() throws MalformedTransactionParameterException {
        List<String> errors = new ArrayList<>();

        boolean passes = ensureNotNull(this.clientUID, "Client UID is null", errors);
        if(passes)
            if(this.clientUID < 0) errors.add("Client UID is negative");

        passes = ensureNotNull(this.productUID, "Product UID is null", errors);
        if(passes)
            if(this.productUID < 0) errors.add("Product UID is negative");

        passes = ensureNotNull(this.numberOfProduct, "Number of Product is null", errors);
        if(passes)
            if(this.numberOfProduct <= 0 || this.numberOfProduct > 100) errors.add("Number of Product mismatches the interval");

        passes = ensureNotNull(this.transactionDate, "Transaction date is null", errors);
        if(passes) {
            try {
                Date START = DATE_FORMAT.parse("1900-01-01 00:00:00");
                Date END = DATE_FORMAT.parse("2099-12-31 00:00:00");
                if(this.transactionDate.before(START) || this.transactionDate.after(END))
                    errors.add("Transactio date is outside the normal range: " + START + ".." + END);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        if(!errors.isEmpty()) {
            MalformedTransactionParameterException ex = new MalformedTransactionParameterException();
            for (String error : errors)
                ex.addSuppressed(new MalformedTransactionParameterException(error));
            throw ex;
        }
    }
}