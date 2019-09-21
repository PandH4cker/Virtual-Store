package store.business.gui;

import store.business.util.store.Store;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

class SelectCategoryPanel extends JPanel {
    private final Store store = new Store("Virtual Store D&DS",
                                new ImageIcon(Paths.get("files/images/heroStore.png").toAbsolutePath().toString()));
    private final JLabel heroStoreImage;
    private final JLabel heroStoreName;
    private final JLabel categoryList;
    private final JButton searchButton;
    private final JComboBox<String> list;

    private static final Dimension TOP_PANEL_DIMENSION = new Dimension(800, 150);

    public SelectCategoryPanel() {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        this.heroStoreImage = new JLabel(store.getLogo(), SwingConstants.CENTER);
        this.heroStoreName = new JLabel(store.getName(), SwingConstants.CENTER);

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        add(this.heroStoreName, c);

        c.gridy = 1;
        c.anchor = GridBagConstraints.LAST_LINE_START;
        this.heroStoreImage.setPreferredSize(new Dimension(200, 60));
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.33;
        add(this.heroStoreImage, c);

        this.categoryList = new JLabel("Catégories produits", SwingConstants.CENTER);
        c.gridx = 1;
        c.gridy = 0;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.5;
        add(this.categoryList, c);

        this.list = new JComboBox<>();
        this.list.setPreferredSize(new Dimension(270, 25));
        c.gridy = 1;
        c.anchor = GridBagConstraints.LINE_END;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.33;
        add(this.list, c);

        this.searchButton = new JButton("Chercher");
        this.searchButton.setPreferredSize(new Dimension(125, 40));
        c.gridx = 2;
        c.anchor = GridBagConstraints.CENTER;
        c.fill = GridBagConstraints.NONE;
        c.weightx = 0.33;
        add(this.searchButton, c);

        setPreferredSize(TOP_PANEL_DIMENSION);
        setVisible(true);
    }
}

// TODO Finish This Class and other panels