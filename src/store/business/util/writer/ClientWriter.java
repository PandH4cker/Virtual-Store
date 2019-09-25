package store.business.util.writer;

import org.w3c.dom.Element;
import store.business.util.client.Client;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClientWriter extends Writer<Client> {
    private static final Path CLIENTS_PATH = Paths.get("files/clients.xml").toAbsolutePath();
    public ClientWriter(final Client client) {
        super(CLIENTS_PATH);
        writeElements(client);
    }
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
        postalCode.appendChild(this.doc.createTextNode(Integer.toString(c.getPostalCode())));
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
            e.printStackTrace();
        }
    }
}
