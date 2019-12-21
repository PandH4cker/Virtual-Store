package store.business.gui.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;
import store.business.util.logger.level.Level;
import store.business.util.parser.ClientParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * <h1>The view for creating a new client</h1>
 * <p>
 *     The {@code public class NewClientView} class is a view
 *     for creating a new client.
 *     It sets a locker in order to we can't start a second
 *     instance of this view at the same time.
 * </p>
 * <img src="../../../../uml/NewClientViewDiagram.jpg" />
 *
 * @author Dray Raphael
 * @version 1.0.0
 * @since 1.0.0
 * @see Logger
 * @see File
 * @see ClientParser
 */
public class NewClientView {
    private final Logger logger = LoggerFactory.getLogger("NewClientView");
    private static final File locker = new File("locker.lock");;
    private double xFrame;
    private double yFrame;

    private static ClientParser clientParser;

    /**
     * Initialize the ClientParser from the parameter
     * @param cp The ClientParser
     * @see ClientParser
     */
    public NewClientView(final ClientParser cp) {
        clientParser = cp;
    }

    /**
     * Setup the start of the newClientView.
     * Creating a locker and show the view.
     * This method is not an Override.
     * @param stage {@inheritDoc}
     * @throws Exception In case of exception, it will be thrown
     * @see Stage
     * @see Files
     * @see Paths
     * @see Logger
     * @see Level
     * @see Parent
     * @see FXMLLoader
     * @see Scene
     * @see StageStyle
     */
    public void start(Stage stage) throws Exception {
        if(Files.exists(Paths.get("locker.lock"))) throw new FileAlreadyExistsException("An instance is already opened");
        if (locker.createNewFile()) this.logger.log("Locker Created", Level.INFO);

        Parent root = FXMLLoader.load(Paths.get("files/resources/newClient.fxml").toUri().toURL());
        stage.setTitle("Nouveau Client");
        stage.setScene(new Scene(root));
        stage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(this::catchSceneXY);

        root.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX() - this.xFrame);
            stage.setY(e.getScreenY() - this.yFrame);
        });

        stage.show();
    }

    /**
     * Catch the x and y value of the Scene
     * @param e The mouse event
     * @see MouseEvent
     */
    private void catchSceneXY(MouseEvent e) {
        this.xFrame = e.getSceneX();
        this.yFrame = e.getSceneY();
    }

    /**
     * Getter of the ClientParser
     * @return The ClientParser
     * @see ClientParser
     */
    public static ClientParser getClientParser() {
        return clientParser;
    }

    /**
     * Delete the locker in order to close the view
     * @throws IOException If the file doesn't exist
     * @see Files
     * @see Paths
     */
    public static void instanceClosed() throws IOException {
        Files.delete(Paths.get("locker.lock"));
    }
}