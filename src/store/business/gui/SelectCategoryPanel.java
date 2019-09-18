package store.business.gui;

import store.business.util.store.Store;

import javax.swing.*;
import java.awt.*;
import java.nio.file.Paths;

class SelectCategoryPanel extends JPanel {
    private final Store store = new Store("Virtual Store D&DS",
                                new ImageIcon(Paths.get("files/images/heroStore.png").toAbsolutePath().toString()));
    private final JLabel heroStore;
   // private final JLabel categoryList;
   // private final JButton searchButton;
   // private final JComboBox<String> list;

    private static final Dimension TOP_PANEL_DIMENSION = new Dimension(800, 150);

    public SelectCategoryPanel() {
        super(new GridLayout(1, 3));
        this.heroStore = new JLabel(store.getName(), store.getLogo(), JLabel.CENTER);
        this.heroStore.setHorizontalAlignment(JLabel.CENTER);
        setPreferredSize(TOP_PANEL_DIMENSION);
        setVisible(true);
    }
}

// TODO Finish This Class and other panels