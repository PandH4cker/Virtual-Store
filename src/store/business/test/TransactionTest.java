package store.business.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.function.Executable;
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

        populateTests(clientUID, productUID, numberOfProduct, transactionDate);
    }

    private void populateTests(long clientUID, long productUID, int numberOfProduct, Date transactionDate) {
        assertAll("Transaction non conforme", executeConformityTests(clientUID, productUID, numberOfProduct, transactionDate));

        assertEquals(this.transaction.getTransactionDate()
                +"\n UID Product: " +this.transaction.getProductUID()
                +"\n UID Client: " +this.transaction.getClientUID()
                +"\n Amount: " +this.transaction.getNumberOfProduct(), this.transaction.toString());

        assertAll("Données Transaction non conformes",
                TransactionTest::executeClientUIDTests,
                TransactionTest::executeProductUIDTests,
                TransactionTest::executeNumberOfProductTests,
                TransactionTest::executeDateTests
        );
    }

    private Executable[] executeConformityTests(long clientUID, long productUID, int numberOfProduct, Date transactionDate) {
        return new Executable[]{() -> assertEquals(clientUID, this.transaction.getClientUID()),
                () -> assertEquals(productUID, this.transaction.getProductUID()),
                () -> assertEquals(numberOfProduct, this.transaction.getNumberOfProduct()),
                () -> assertEquals(DATE_FORMAT.format(transactionDate), this.transaction.getTransactionDate())};
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

    private static void executeClientUIDTests() {
        assertAll("UID Client non conforme",
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(-1, 1184658967007L, 5, new Date())
                )
        );
    }

    private static void executeProductUIDTests() {
        assertAll("UID Produit non conforme",
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, -1, 5, new Date())
                )
        );
    }

    private static void executeNumberOfProductTests() {
        assertAll("Nombre de produit non conforme",
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, 0, new Date())
                ),
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, -1, new Date())
                ),
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, 101, new Date())
                )
        );
    }

    private static void executeDateTests() {
        assertAll("Date non conforme",
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, 100, null)
                ),
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, 100, DATE_FORMAT.parse("1899-12-31 23:59:59"))
                ),
                () -> assertThrows(MalformedTransactionParameterException.class,
                        () -> new Transaction(134811913L, 1184658967007L, 100, DATE_FORMAT.parse("2100-01-01 00:00:00"))
                )
        );
    }

}