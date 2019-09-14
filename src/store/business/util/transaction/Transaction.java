package store.business.util.transaction;

import java.util.Date;

public class Transaction {
    private final int clientUID;
    private final int productUID;
    private final int numberOfProduct;
    private final Date transactionDate;

    public Transaction(final int clientUID,
                       final int productUID,
                       final int numberOfProduct,
                       final Date transactionDate) {
        this.clientUID = clientUID;
        this.productUID = productUID;
        this.numberOfProduct = numberOfProduct;
        this.transactionDate = transactionDate;
    }
}
