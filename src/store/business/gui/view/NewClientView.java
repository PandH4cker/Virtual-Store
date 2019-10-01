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

public class NewClientView {
    private final Logger logger = LoggerFactory.getLogger("NewClientView");
    private static final File locker = new File("locker.lock");;
    private double xFrame;
    private double yFrame;

    private static ClientParser clientParser;

    public NewClientView(final ClientParser cp) {
        clientParser = cp;
    }

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

    private void catchSceneXY(MouseEvent e) {
        this.xFrame = e.getSceneX();
        this.yFrame = e.getSceneY();
    }

    public static ClientParser getClientParser() {
        return clientParser;
    }

    public static void instanceClosed() throws IOException {
        Files.delete(Paths.get("locker.lock"));
    }
}
