package store.business.util.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import store.business.util.client.Client;
import store.business.util.client.exception.MalformedClientParameterException;
import store.business.util.logger.level.Level;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

/**
 * <h1>The client parser</h1>
 * <p>
 *     Parse the clients into a list of client
 *     It inherits of the {@code public abstract class Parser<E>} generic class
 * </p>
 * <img src="../../../../uml/ClientParser.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Parser<Client>
 * @see Client
 * @see Path
 */
public class ClientParser extends Parser<Client> {
    /**
     * {@value "files/clients.xml"}
     */
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();

    /**
     * Inherits the constructor of the {@code public abstract class Parser<E>} class
     * Add elements to the list
     */
    public ClientParser() throws MalformedClientParameterException {
        super(CLIENTS_PATH);
        addEElements();
        this.logger.log("Clients Parsed", Level.INFO);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEElements() throws MalformedClientParameterException {
        final NodeList nodeRoot = this.getRootElement().getChildNodes();
        final int nodeRootLength = nodeRoot.getLength();
        final String[] clientElements = {"name", "surname", "address", "postalCode", "UID"};

        for(int i = 0; i < nodeRootLength; ++i) {
            if(nodeRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element client = (Element) nodeRoot.item(i);
                final LinkedList<String> elementsString = new LinkedList<>();
                for(String s : clientElements) elementsString.add(client.getElementsByTagName(s).item(0).getTextContent());
                this.getEList().add(new Client(elementsString.pop(), elementsString.pop(),
                                               elementsString.pop(), elementsString.pop(),
                                               Long.parseLong(elementsString.pop())));
            }
        }
    }
}