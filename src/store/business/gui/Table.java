package store.business.gui;

import store.business.util.product.Book;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.*;

public final class Table {
    private final JFrame storeFrame;
    private final SelectCategoryPanel selectCategoryPanel;
    private final CurrentProductPanel currentProductPanel;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 600);

    private static final Table INSTANCE = new Table();

    private Table() {
        this.storeFrame = new JFrame("Virtual Store");
        this.storeFrame.setLayout(new GridLayout(4, 1));

        this.selectCategoryPanel = new SelectCategoryPanel();
        this.storeFrame.add(this.selectCategoryPanel);

        this.currentProductPanel = new CurrentProductPanel(new Book("L'autoroute du millionaire : La voie express vers la richesse",
                                                                    "25", "2849334650", "100", "files/images/LautorouteMillionaire.jpg",
                                                                    "MJ DeMarco", "Français", "352"));
        this.storeFrame.add(this.currentProductPanel);

        setDefaultLookAndFeelDecorated(true);
        this.storeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.storeFrame.setSize(OUTER_FRAME_DIMENSION);
        this.storeFrame.pack();
        this.storeFrame.setLocationRelativeTo(null);
        this.storeFrame.setVisible(true);
    }

    public static Table get() {
        return INSTANCE;
    }

    private JFrame getStoreFrame() {
        return this.storeFrame;
    }

    private static void center(final JFrame frame) {
        final Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        final int w = frame.getSize().width;
        final int h = frame.getSize().height;
        final int x = (dim.width - w) / 2;
        final int y = (dim.height - h) / 2;
        frame.setLocation(x, y);
    }

}
