package store.business.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import store.business.util.client.Client;
import store.business.util.parser.ClientParser;
import store.business.util.parser.ProductParser;
import store.business.util.product.*;
import store.business.util.transaction.Transaction;
import store.business.util.writer.ClientWriter;
import store.business.util.writer.TransactionWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
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
    private final ClientParser clientParser;
    private String selectedProduct;
    private String selectedClient;
    private int selectedAmount;
    private Client client;
    private Product product;

    public VirtualStoreController() {
        this.productParser = new ProductParser();
        this.clientParser = new ClientParser();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.comboBoxCategory.getItems().add("Tous");
        for(String s : this.productParser.getAttributes())
            this.comboBoxCategory.getItems().add(s);
        this.comboBoxAmount.getItems().addAll(1, 2, 3, 4, 5);
    }

    private void clearFields() {
        this.clientNameTextField.clear();
        this.currentClientDescription.clear();
        this.currentProductDescription.clear();
        this.currentClientNameLabel.setText("Nom du client courant");
        this.currentProductLabel.setText("Nom du produit courant");
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
                     case "Tous":
                         for(Product p : this.productParser.getEList())
                             this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                     case "Jeux Vidéo":
                         for(Product p : this.productParser.getEList())
                             if(p instanceof VideoGame)
                                 this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                     case "Livre":
                         for(Product p : this.productParser.getEList())
                             if(p instanceof Book)
                                 this.allowedProducts.appendText(p.getName() + '\n');
                         break;
                     case "DVD":
                         for(Product p : this.productParser.getEList())
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
             this.selectedProduct = this.allowedProducts.getSelectedText();
            if(this.selectedProduct != null && this.selectedProduct.trim().length() != 0) {
                Product product = null;
                for(Product p : this.productParser.getEList())
                    if(p.getName().trim().equals(this.selectedProduct.trim())) {
                        product = p;
                        break;
                    }
                if(product == null) throw new RuntimeException("Unknown product");
                else {
                    this.product = product;
                    if(this.currentProductDescription.getText().trim().length() != 0)
                        this.currentProductDescription.clear();
                    this.currentProductDescription.appendText(product.toString());
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

    @FXML
    public void handleSearchByClientAction(MouseEvent event) {
        if(event.getSource() == this.searchByClientButton) {
             this.selectedClient = this.clientNameTextField.getText().trim();
            if(this.selectedClient.length() != 0) {
                String[] splittedClientName = this.selectedClient.split(" ");
                Client client = null;
                for(Client c : this.clientParser.getEList()) {
                    if((c.getName().trim().equals(splittedClientName[0]) && c.getSurname().trim().equals(splittedClientName[1]))
                        || (c.getName().trim().equals(splittedClientName[1]) && c.getSurname().trim().equals(splittedClientName[0]))) {
                        client = c;
                        break;
                    }
                }
                if(client == null) throw new RuntimeException("Unknown Client");
                else {
                    this.client = client;
                    if(this.currentClientDescription.getText().trim().length() != 0)
                        this.currentClientDescription.clear();
                    this.currentClientDescription.appendText(client.toString());
                    this.currentClientNameLabel.setText(client.getName() + " " + client.getSurname());
                }
            }
        }
    }

    @FXML
    public void handleBuyButton(MouseEvent event) {
        if(event.getSource() == this.buyButton) {
            this.selectedAmount = this.comboBoxAmount.getSelectionModel().getSelectedItem();
            if(this.selectedAmount > 0)
                if(this.currentClientDescription.getText().trim().length() != 0 &&
                   this.currentProductDescription.getText().trim().length() != 0) {
                    new TransactionWriter(
                            new Transaction(this.client.getUniqueID(),
                                            this.product.getUniqueID(),
                                            this.selectedAmount,
                                            new Date()));
                    clearFields();
                }
        }
    }
}
