package store.business.gui.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.nio.file.Paths;

/**
 * <h1>The main JavaFX App</h1>
 * <p>
 *     The {@code public class App} class implements the
 *     {@code public interface Application} interface in order to
 *     configure the start of the JavaFX application.
 * </p>
 * <img src="../../../../uml/App.png" />
 * @author Dray Raphael
 * @version 1.0.0
 * @since 1.0.0
 * @see Application
 * @see Parent
 */
public class VirtualStoreView extends Application {
    private double xFrame;
    private double yFrame;

    /**
     * Launch the application
     * @param args Arguments passed to the application, no one required.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Setup the start of our application
     * @param primaryStage {@inheritDoc}
     * @throws Exception In case of Exception, it will be thrown
     * @see Override
     * @see Stage
     * @see Parent
     * @see FXMLLoader
     * @see Paths
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Paths.get("files/resources/virtualStore.fxml").toUri().toURL());
        primaryStage.setTitle("Virtual Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(this::catchSceneXY);

        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - this.xFrame);
            primaryStage.setY(e.getScreenY() - this.yFrame);
        });

        primaryStage.show();
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
}