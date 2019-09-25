package store.business.util.writer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class TransactionWriter extends Writer {
    private static final Path TRANSACTION_PATH = Paths.get("files/transactions.xml");

    public TransactionWriter() {
        super(TRANSACTION_PATH);
    }

    @Override
    protected void writeElements() {

    }
}
