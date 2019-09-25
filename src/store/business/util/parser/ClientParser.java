package store.business.util.parser;

import store.business.util.client.Client;
import store.business.util.parser.Parser;

import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientParser extends Parser<Client> {
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();

    public ClientParser() {
        super(CLIENTS_PATH);
        addEElements();
    }

    @Override
    protected void addEElements() {

    }
}
