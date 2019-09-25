package store.business.util.transaction;

import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import java.util.Date;

public class Transaction {
    private final Logger logger = LoggerFactory.getLogger("Transaction");
    private final long clientUID;
    private final long productUID;
    private final int numberOfProduct;
    private final Date transactionDate;

    public Transaction(final long clientUID,
                       final long productUID,
                       final int numberOfProduct,
                       final Date transactionDate) {
        this.clientUID = clientUID;
        this.productUID = productUID;
        this.numberOfProduct = numberOfProduct;
        this.transactionDate = transactionDate;
        this.logger.log("New Transaction: " + this);
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

    public Date getTransactionDate() {
        return this.transactionDate;
    }

    @Override
    public String toString() {
        return this.transactionDate
               +"\n UID Product: " +this.productUID
               +"\n UID Client: " +this.clientUID
               +"\n Amount: " +this.numberOfProduct;
    }
}
