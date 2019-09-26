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

public class TransactionWriter extends Writer<Transaction> {
    private static final Path TRANSACTION_PATH = Paths.get("files/transactions.xml");
    public TransactionWriter(final Transaction transaction) {
        super(TRANSACTION_PATH);
        writeElements(transaction);
        this.logger.log("New Transaction Written", Level.INFO);
    }

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
