package store.business.util.writer;

import org.w3c.dom.Element;
import store.business.util.logger.level.Level;
import store.business.util.transaction.Transaction;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>The transaction writer object</h1>
 * <p>
 *     The transaction writer is an object that
 *     write a transaction into a XML file.
 *     It inherits of the {@code public abstract class Write<E>} class
 * </p>
 * <img src="../../../../uml/TransactionWriterDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Writer<Transaction>
 * @see Transaction
 * @see Path
 */
public class TransactionWriter extends Writer<Transaction> {

    /**
     * {@value "files/transactions.xml"}
     */
    private static final Path TRANSACTION_PATH = Paths.get("files/transactions.xml");

    /**
     * Write the transaction into the XML file
     * @param transaction The transaction to write
     */
    public TransactionWriter(final Transaction transaction) {
        super(TRANSACTION_PATH);
        writeElements(transaction);
        this.logger.log("New Transaction Written", Level.INFO);
    }

    /**
     * Write elements into the XML file
     * @param t The transaction to write
     * @see Override
     * @see Transaction
     * @see Element
     * @see DOMSource
     * @see TransformerFactory
     * @see Transformer
     * @see StreamResult
     * @exception TransformerException Transformer failure
     * @see TransformerException
     */
    @Override
    protected void writeElements(Transaction t) {
        Element newTransaction = this.doc.createElement("transaction");

        Element clientUID = this.doc.createElement("clientUID");
        clientUID.appendChild(this.doc.createTextNode(Long.toString(t.getClientUID())));
        newTransaction.appendChild(clientUID);

        Element productUID = this.doc.createElement("productUID");
        productUID.appendChild(this.doc.createTextNode(Long.toString(t.getProductUID())));
        newTransaction.appendChild(productUID);

        Element amount = this.doc.createElement("amount");
        amount.appendChild(this.doc.createTextNode(Integer.toString(t.getNumberOfProduct())));
        newTransaction.appendChild(amount);

        Element date = this.doc.createElement("date");
        date.appendChild(this.doc.createTextNode(t.getTransactionDate()));
        newTransaction.appendChild(date);

        this.root.appendChild(newTransaction);

        DOMSource source = new DOMSource(this.doc);

        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer();
            StreamResult result = new StreamResult(TRANSACTION_PATH.toString());
            transformer.transform(source, result);
        } catch (TransformerException e) {
            this.logger.log(e.getMessage(), Level.ERROR);
        }
    }
}