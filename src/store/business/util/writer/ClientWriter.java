package store.business.util.writer;

import org.w3c.dom.Element;
import store.business.util.client.Client;
import store.business.util.logger.level.Level;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * <h1>The client writer object</h1>
 * <p>
 *     The client writer is an object that
 *     write a client into a XML file.
 *     It inherits of the {@code public abstract class Writer<E>} class
 * </p>
 * <img src="../../../../uml/ClientWriterDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Writer<Client>
 * @see Client
 * @see Path
 */
public class ClientWriter extends Writer<Client> {

    /**
     * {@value "files/clients.xml"}
     */
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();

    /**
     * Write the client into the XML file
     * @param client The client to write
     */
    public ClientWriter(final Client client) {
        super(CLIENTS_PATH);
        writeElements(client);
        this.logger.log("New Client Written", Level.INFO);
    }

    /**
     * Write elements into the XML File
     * @param c The client to write
     * @see Override
     * @see Client
     * @see Element
     * @see DOMSource
     * @see TransformerFactory
     * @see Transformer
     * @see StreamResult
     * @exception TransformerException Transformer failure
     * @see TransformerException
     */
    @Override
    protected void writeElements(Client c) {
        Element newClient = this.doc.createElement("client");

        Element name = this.doc.createElement("name");
        name.appendChild(this.doc.createTextNode(c.getName()));
        newClient.appendChild(name);

        Element surname = this.doc.createElement("surname");
        surname.appendChild(this.doc.createTextNode(c.getSurname()));
        newClient.appendChild(surname);

        Element address = this.doc.createElement("address");
        address.appendChild(this.doc.createTextNode(c.getAddress()));
        newClient.appendChild(address);

        Element postalCode = this.doc.createElement("postalCode");
        postalCode.appendChild(this.doc.createTextNode(c.getPostalCode()));
        newClient.appendChild(postalCode);

        Element UID = this.doc.createElement("UID");
        UID.appendChild(this.doc.createTextNode(Long.toString(c.getUniqueID())));
        newClient.appendChild(UID);

        this.root.appendChild(newClient);

        DOMSource source = new DOMSource(this.doc);

        TransformerFactory factory = TransformerFactory.newInstance();
        try {
            Transformer transformer = factory.newTransformer();
            StreamResult result = new StreamResult(CLIENTS_PATH.toString());
            transformer.transform(source, result);
        } catch (TransformerException e) {
            this.logger.log(e.getMessage(), Level.ERROR);
        }
    }
}