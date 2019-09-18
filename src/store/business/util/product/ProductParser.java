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
import java.util.LinkedList;

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

    public ArrayList<Product> getProductsList() {
        return this.productsList;
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
                final LinkedList<String> elementsString = new LinkedList<>();
                for(String s : productElements) elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                switch (product.getAttribute("category")) {
                    case "Jeux Vidéo":
                        for (String s : videoGameElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.productsList.add(new VideoGame(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(), elementsString.pop()));
                        break;
                    case "Livre":
                        for (String s : bookElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.productsList.add(new Book(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop()));
                        break;
                    case "DVD":
                        for(String s : dvdElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.productsList.add(new DVD(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop()));
                        break;
                }
            }
        }
    }
}
