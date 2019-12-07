package store.business.util.transaction;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

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
public class Transaction {
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
                       final Date transactionDate) {
        this.clientUID = clientUID;
        this.productUID = productUID;
        this.numberOfProduct = numberOfProduct;
        this.transactionDate = transactionDate;
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
}