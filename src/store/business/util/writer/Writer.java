package store.business.util.writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

public abstract class Writer<E> {
    protected final Logger logger = LoggerFactory.getLogger("Writer");
    protected final Document doc;
    protected final Element root;
    private final Path E_PATH;

    protected Writer(final Path E_PATH) {
        this.E_PATH = E_PATH;
        this.doc = createDocument();
        this.root = getRootElement();
    }

    private Document createDocument() {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(E_PATH.toFile());
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Cannot read the document: " + E_PATH);
    }

    protected Element getRootElement() {
        return this.doc.getDocumentElement();
    }

    protected abstract void writeElements(E e);
}
