package store.business.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class VirtualStoreController implements Initializable {
    @FXML
    private ComboBox<String> comboBoxCategory;
    @FXML
    private Button searchByCategoryButton;
    @FXML
    private TextArea allowedProducts;
    @FXML
    private TextArea currentProductDescription;
    @FXML
    private ImageView currentProductImage;
    @FXML
    private TextField clientNameTextField;
    @FXML
    private Button searchByClientButton;
    @FXML
    private TextArea currentClientDescription;
    @FXML
    private Label currentClientNameLabel;
    @FXML
    private ComboBox<Integer> comboBoxAmount;
    @FXML
    private Label currentProductLabel;
    @FXML
    private Button buyButton;
    @FXML
    private Label closerLabel;

    public VirtualStoreController() {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.comboBoxCategory.getItems().addAll("Jeux Vidéo", "Livre", "DVD");
        this.comboBoxAmount.getItems().addAll(1, 2, 3, 4, 5);
    }

    private void clearFields() {
        this.clientNameTextField.clear();
        this.currentClientDescription.clear();
        this.currentProductDescription.clear();
    }

    @FXML
    public void handleCloseAction(MouseEvent event) {
        if(event.getSource() == this.closerLabel)
            System.exit(0);
    }


}
