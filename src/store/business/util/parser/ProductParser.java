package store.business.util.parser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import store.business.util.product.Book;
import store.business.util.product.DVD;
import store.business.util.product.Product;
import store.business.util.product.VideoGame;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;

public class ProductParser extends Parser<Product> {
    private static final Path PRODUCTS_PATH = Paths.get("files/produits.xml").toAbsolutePath();

    public ProductParser() {
        super(PRODUCTS_PATH);
        addEElements();
        this.logger.log("Product Parsed");
    }

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
