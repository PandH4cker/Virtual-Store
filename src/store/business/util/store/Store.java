package store.business.util.store;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import javax.swing.*;

/**
 * <h1>The store object</h1>
 * <p>
 *     A store is an object that has:
 *     <li>A name</li>
 *     <li>A logo</li>
 * </p>
 * <img src="../../../../uml/StoreDiagram.jpg" />
 *
 * @author Raphael Dray
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 * @see ImageIcon
 */
public class Store {
    private final Logger logger = LoggerFactory.getLogger("Store");
    private final String name;
    private final ImageIcon logo;

    /**
     * Initializes the name and logo attributes
     * @param name The name of the store
     * @param logo The logo of the store
     * @see ImageIcon
     */
    public Store(final String name, final ImageIcon logo) {
        this.name = name;
        this.logo = logo;
        this.logger.log("Store Created", Level.INFO);
    }

    /**
     * Getter of the logo
     * @return ImageIcon - The logo of the store
     */
    public ImageIcon getLogo() {
        return this.logo;
    }

    /**
     * Getter of the name
     * @return String - The name of the store
     */
    public String getName() {
        return this.name;
    }
}