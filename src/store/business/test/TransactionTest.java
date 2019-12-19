package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import store.business.util.transaction.Transaction;
import store.business.util.transaction.exception.MalformedTransactionParameterException;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TransactionTest {
    private Transaction transaction = null;

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");


    @DisplayName("Constructor of Transaction Tester")
    @ParameterizedTest
    @MethodSource("getDataForConstructor")
    void transactionConstructorTest(final long clientUID,
                                    final long productUID,
                                    final int numberOfProduct,
                                    final Date transactionDate) {
        try { this.transaction = new Transaction(clientUID, productUID, numberOfProduct, transactionDate); }
        catch (MalformedTransactionParameterException e) { e.printStackTrace(); }

        assertAll("Transaction non conforme",
                () -> assertEquals(clientUID, this.transaction.getClientUID()),
                () -> assertEquals(productUID, this.transaction.getProductUID()),
                () -> assertEquals(numberOfProduct, this.transaction.getNumberOfProduct()),
                () -> assertEquals(DATE_FORMAT.format(transactionDate), this.transaction.getTransactionDate())
        );

        assertEquals(this.transaction.getTransactionDate()
                +"\n UID Product: " +this.transaction.getProductUID()
                +"\n UID Client: " +this.transaction.getClientUID()
                +"\n Amount: " +this.transaction.getNumberOfProduct(), this.transaction.toString());

        //TODO Finish the test class
    }

    static List<Object[]> getDataForConstructor() {
        return Arrays.asList(
                new Object[][]{
                        {
                            134811913L,
                            1184658967007L,
                            5,
                            new Date()
                        }
                });
    }
}
