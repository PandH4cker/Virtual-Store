package store.business.util.transaction;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction {
    private final Logger logger = LoggerFactory.getLogger("Transaction");
    private final long clientUID;
    private final long productUID;
    private final int numberOfProduct;
    private final Date transactionDate;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

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

    public long getClientUID() {
        return this.clientUID;
    }

    public long getProductUID() {
        return this.productUID;
    }

    public int getNumberOfProduct() {
        return this.numberOfProduct;
    }

    public String getTransactionDate() {
        return DATE_FORMAT.format(this.transactionDate);
    }

    @Override
    public String toString() {
        return DATE_FORMAT.format(this.transactionDate)
               +"\n UID Product: " +this.productUID
               +"\n UID Client: " +this.clientUID
               +"\n Amount: " +this.numberOfProduct;
    }
}
