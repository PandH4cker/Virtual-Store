package store.business.util.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import store.business.util.client.exception.MalformedClientParameterException;
import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

/**
 * <h1>The parser abstract class</h1>
 * <p>
 *     Prepare the XML document and
 *     get the root of it from a file path
 * </p>
 * <img src="../../../../uml/Parser.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see E
 * @see Logger
 * @see ArrayList<E>
 * @see Document
 * @see Element
 * @see Path
 * @param <E> The type for the {@code ArrayList<E>}
 */
public abstract class Parser<E> {
    protected final Logger logger = LoggerFactory.getLogger("Parser");
    protected final ArrayList<E> eList;
    protected final Document doc;
    protected final Element root;
    private final Path E_PATH;

    /**
     * Initialize the file path, the list, the document and the root of the XML file
     * @param E_PATH The file path
     * @see Path
     */
    protected Parser(final Path E_PATH) {
        this.E_PATH = E_PATH;
        this.eList = new ArrayList<>();
        this.doc = createDocument();
        this.root = getRootElement();
    }

    /**
     * Create a document from a file path in order to parse it
     * @return Document The document from the file path
     * @see Document
     * @see DocumentBuilderFactory
     * @see ParserConfigurationException
     * @exception ParserConfigurationException Bad configuration for the parser
     * @see SAXException
     * @exception SAXException SAX parser failure
     * @see IOException
     * @exception IOException File exception
     * @see RuntimeException
     * @exception RuntimeException Cannot read the document
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
     * Getter of the root of the XML file
     * @return Element The root of the XML file
     * @see Element
     */
    protected Element getRootElement() {
        return this.doc.getDocumentElement();
    }

    /**
     * Getter of the list of E
     * @return ArrayList<E> The list of E
     * @see E
     */
    public ArrayList<E> getEList() {
        return this.eList;
    }

    /**
     * Abstract method need to be implemented.
     * Add the E elements to the list of E
     * @see E
     */
    protected abstract void addEElements() throws MalformedClientParameterException;
}