package store.business.util.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class Parser<E> {
    protected final ArrayList<E> eList;
    protected final Document doc;
    protected final Element root;
    private final Path E_PATH;

    protected Parser(final Path E_PATH) {
        this.E_PATH = E_PATH;
        this.eList = new ArrayList<>();
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

    public ArrayList<E> getEList() {
        return this.eList;
    }

    protected abstract void addEElements();
}
