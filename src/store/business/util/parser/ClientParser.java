package store.business.util.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import store.business.util.client.Client;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ClientParser extends Parser<Client> {
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();

    public ClientParser() {
        super(CLIENTS_PATH);
        addEElements();
        this.logger.log("Clients Parsed");
    }

    @Override
    protected void addEElements() {
        final NodeList nodeRoot = this.getRootElement().getChildNodes();
        final int nodeRootLength = nodeRoot.getLength();
        final String[] clientElements = {"name", "surname", "address", "postalCode", "UID"};

        for(int i = 0; i < nodeRootLength; ++i) {
            if(nodeRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element client = (Element) nodeRoot.item(i);
                final LinkedList<String> elementsString = new LinkedList<>();
                for(String s : clientElements) elementsString.add(client.getElementsByTagName(s).item(0).getTextContent());
                this.getEList().add(new Client(elementsString.pop(), elementsString.pop(),
                                               elementsString.pop(), Integer.parseInt(elementsString.pop()),
                                               Long.parseLong(elementsString.pop())));
            }
        }
    }
}
