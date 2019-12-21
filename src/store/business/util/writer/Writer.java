package store.business.util.writer;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;

/**
 * <h1>The writer object</h1>
 * <p>
 *     The writer is an object that has:
 *     <li>A document</li>
 *     <li>An root element</li>
 * </p>
 * <img src="../../../../uml/WriterDiagram.jpg" />
 *
 * @param <E> E The generic type of the writer object
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see E
 * @see Logger
 * @see Document
 * @see Element
 * @see Path
 */
public abstract class Writer<E> {
    protected final Logger logger = LoggerFactory.getLogger("Writer");
    protected final Document doc;
    protected final Element root;
    private final Path E_PATH;

    /**
     * Create a document and get the root element from a XML file
     * @param E_PATH The XML file path
     * @see Path
     */
    protected Writer(final Path E_PATH) {
        this.E_PATH = E_PATH;
        this.doc = createDocument();
        this.root = getRootElement();
    }

    /**
     * Create a document from a XML file
     * @return Document - The document
     * @see Document
     * @see DocumentBuilderFactory
     * @exception ParserConfigurationException Bad Parsing Configuration
     * @see ParserConfigurationException
     * @exception SAXException SAX failure
     * @see SAXException
     * @exception IOException IO failure
     * @see IOException
     * @exception RuntimeException Cannot read the document
     * @see RuntimeException
     */
    private Document createDocument() {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(E_PATH.toFile());
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            this.logger.log(e.getMessage(), Level.ERROR);
        }
        this.logger.log("Cannot read the document: " + E_PATH, Level.WARNING);
        throw new RuntimeException("Cannot read the document: " + E_PATH);
    }

    /**
     * Getter of the root element
     * @return Element - The root element
     * @see Element
     */
    protected Element getRootElement() {
        return this.doc.getDocumentElement();
    }

    /**
     * Abstract method to write elements into the XML file
     * Need to be implemented
     * @param e The object to write
     * @see E
     */
    protected abstract void writeElements(E e);
}