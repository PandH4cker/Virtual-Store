package store.business.gui;

import store.business.util.product.Product;

import javax.swing.*;
import java.awt.*;

class CurrentProductPanel extends JPanel {
    private final JTextArea productArea;
    private final JTextArea currentDescProduct;
    private final JLabel currentProductImage;

    private static final Dimension MID_PANEL_DIMENSION = new Dimension(800, 150);

    public CurrentProductPanel(final Product p) {
        super(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        this.productArea = new JTextArea(1, 1);
        this.productArea.setEditable(false);
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");
//        this.productArea.append("test\n");

        this.currentDescProduct = new JTextArea(p.toString());
        this.currentDescProduct.setEditable(false);

        this.currentProductImage = new JLabel(p.getImage());

        c.gridx = 0;
        c.gridy = 0;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0/3.0;
        c.weighty = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        add(new JScrollPane(this.productArea), c);

        c.gridx = 2;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.FIRST_LINE_START;
        c.weightx = 1.0/3.0;
        add(this.currentDescProduct, c);

        c.gridx = 3;
        c.gridy = 0;
        c.fill = GridBagConstraints.VERTICAL;
        c.anchor = GridBagConstraints.FIRST_LINE_END;
        c.weightx = 1.0/3.0;
        add(this.currentProductImage, c);

        setPreferredSize(MID_PANEL_DIMENSION);
        setVisible(true);
    }
}
