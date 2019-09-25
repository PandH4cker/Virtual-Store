package store.business.util.writer;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientWriter extends Writer {
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();
    public ClientWriter() {
        super(CLIENTS_PATH);
    }
    @Override
    protected void writeElements() {

    }
}
