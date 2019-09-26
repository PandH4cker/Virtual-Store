package store.business.util.store;

import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;

import javax.swing.*;

public class Store {
    private final Logger logger = LoggerFactory.getLogger("Store");
    private final String name;
    private final ImageIcon logo;

    public Store(final String name, final ImageIcon logo) {
        this.name = name;
        this.logo = logo;
        this.logger.log("Store Created", Level.INFO);
    }

    public ImageIcon getLogo() {
        return this.logo;
    }

    public String getName() {
        return this.name;
    }
}
