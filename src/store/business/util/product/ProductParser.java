package store.business.util.product;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class ProductParser {
    private static final Path PRODUCTS_PATH = Paths.get("files/produits.xml").toAbsolutePath();

    private final ArrayList<Product> productsList;
    private final Document doc;
    private final Element root;

    public ProductParser() {
        this.productsList = new ArrayList<>();
        this.doc = createDocument();
        this.root = getRootElement();
        addProducts();
    }

    private Document createDocument() {
        try {
            final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            return factory.newDocumentBuilder().parse(PRODUCTS_PATH.toFile());
        } catch (final ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }

        throw new RuntimeException("Cannot read the document: " + PRODUCTS_PATH);
    }

    private Element getRootElement() {
        return this.doc.getDocumentElement();
    }

    private void addProducts() {
        final NodeList nodeRoot = this.root.getChildNodes();
        final int nodeRootLenght = nodeRoot.getLength();
        final String[] productElements = {"name", "price", "UID", "left", "image"};
        final String[] videoGameElements = {"genre", "platform"};
        final String[] dvdElements = {"actors", "genre", "duration"};
        final String[] bookElements = {"author", "language", "numberOfPages"};

        for(int i = 0; i < nodeRootLenght; ++i) {
            if(nodeRoot.item(i).getNodeType() == Node.ELEMENT_NODE) {
                final Element product = (Element) nodeRoot.item(i);
                final Element category = (Element) product.getElementsByTagName("category").item(0);
                System.out.println(category.getTextContent());
                //TODO FINISH THIS !
            }
        }
    }
}
