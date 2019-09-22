package store.business.gui.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.nio.file.Paths;

public class App extends Application {
    private double xFrame;
    private double yFrame;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Paths.get("files/resources/virtualStore.fxml").toUri().toURL());
        primaryStage.setTitle("Virtual Store");
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(e -> {
            this.xFrame = e.getSceneX();
            this.yFrame = e.getSceneY();
        });

        root.setOnMouseDragged(e -> {
            primaryStage.setX(e.getScreenX() - this.xFrame);
            primaryStage.setY(e.getScreenY() - this.yFrame);
        });

        primaryStage.show();
    }
}
