package store.business.util.store;

import javax.swing.*;

public class Store {
    private final String name;
    private final ImageIcon logo;

    public Store(final String name, final ImageIcon logo) {
        this.name = name;
        this.logo = logo;
    }

    public ImageIcon getLogo() {
        return this.logo;
    }

    public String getName() {
        return this.name;
    }
}
