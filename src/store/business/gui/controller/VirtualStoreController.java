package store.business.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import store.business.util.product.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
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

    private final ProductParser productParser;

    public VirtualStoreController() {
        this.productParser = new ProductParser();
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


    @FXML
    public void handleSearchByCategoryAction(MouseEvent event) {
        if(event.getSource() == this.searchByCategoryButton) {
             String selectedCategory = this.comboBoxCategory.getSelectionModel().getSelectedItem();
             if(selectedCategory != null) {
                 if(this.allowedProducts.getText().trim().length() != 0)
                     this.allowedProducts.clear();

                 switch (selectedCategory) {
                     case "Jeux Vidéo":
                         for(Product p : this.productParser.getProductsList())
                             if(p instanceof VideoGame)
                                 this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                     case "Livre":
                         for(Product p : this.productParser.getProductsList())
                             if(p instanceof Book)
                                 this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                     case "DVD":
                         for(Product p : this.productParser.getProductsList())
                             if(p instanceof DVD)
                                 this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                 }
             }
        }
    }

    @FXML
    public void handleSelectedTextAction(ContextMenuEvent event) {
        if(event.getSource() == this.allowedProducts) {
            String selectedText = this.allowedProducts.getSelectedText();
            System.out.println(selectedText);
            for(Product p : this.productParser.getProductsList()) System.out.println(p.getName());
            if(selectedText != null && selectedText.trim().length() != 0) {
                Product product = null;
                for(Product p : this.productParser.getProductsList())
                    if(p.getName().trim().equals(selectedText.trim())) {
                        product = p;
                        break;
                    }
                if(product == null)
                    throw new RuntimeException("Unknown product");
                else {
                    if(this.currentProductDescription.getText().trim().length() != 0)
                        this.currentProductDescription.clear();
                    this.currentProductDescription.appendText(product.toString());
                    System.out.println(product.getImagePath());
                    try {
                        this.currentProductImage.setImage(
                                new Image(
                                        new FileInputStream(Paths
                                                .get(product.getImagePath())
                                                .toAbsolutePath()
                                                .toString())));
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    this.currentProductLabel.setText(product.getName());
                }
            }
        }
    }
}
