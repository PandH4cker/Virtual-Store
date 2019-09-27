package store.business.util.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import store.business.util.logger.level.Level;
import store.business.util.product.Book;
import store.business.util.product.DVD;
import store.business.util.product.Product;
import store.business.util.product.VideoGame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.LinkedList;

/**
 * <h1>The product parser</h1>
 * <p>
 *     Parse the products into a list of product
 *     It inherits of the {@code public abstract class Parser<E>} generic class
 * </p>
 * <img src="../../../../uml/ProductParser.png" />
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Parser<Product>
 * @see Product
 * @see Path
 */
public class ProductParser extends Parser<Product> {
    /**
     * {@value "files/produits.xml"}
     */
    private static final Path PRODUCTS_PATH = Paths.get("files/produits.xml").toAbsolutePath();
    private LinkedList<String> attributes;

    /**
     * Inherits the constructor of the {@code public abstract class Parser<E>} class
     * Add elements to the list
     * Sort by price
     * @see Collections
     */
    public ProductParser() {
        super(PRODUCTS_PATH);
        this.attributes = new LinkedList<>();
        addEElements();
        Collections.sort(this.eList);
        this.logger.log("Product Parsed", Level.INFO);
    }

    /**
     * Getter of the attributes
     * @return LinkedList<String> The attributes present in the XML file
     */
    public LinkedList<String> getAttributes() {
        return this.attributes;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void addEElements() {
        final NodeList nodeRoot = this.getRootElement().getChildNodes();
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
                if(!this.attributes.contains(product.getAttribute("category")))
                    this.attributes.add(product.getAttribute("category"));
                switch (product.getAttribute("category")) {
                    case "Jeux Vidéo":
                        for (String s : videoGameElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.getEList().add(new VideoGame(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(), elementsString.pop()));
                        break;
                    case "Livre":
                        for (String s : bookElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.getEList().add(new Book(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop()));
                        break;
                    case "DVD":
                        for(String s : dvdElements)
                            elementsString.add(product.getElementsByTagName(s).item(0).getTextContent());
                        this.getEList().add(new DVD(elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop(), elementsString.pop(),
                                              elementsString.pop(), elementsString.pop()));
                        break;
                }
            }
        }
    }
}
