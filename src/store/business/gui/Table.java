package store.business.gui;

import javax.swing.*;
import java.awt.*;

import static javax.swing.JFrame.*;

public final class Table {
    private final JFrame storeFrame;

    private static final Dimension OUTER_FRAME_DIMENSION = new Dimension(800, 600);

    private static final Table INSTANCE = new Table();

    private Table() {
        this.storeFrame = new JFrame("Virtual Store");

        setDefaultLookAndFeelDecorated(true);
        this.storeFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.storeFrame.setSize(OUTER_FRAME_DIMENSION);
        center(this.storeFrame);
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
