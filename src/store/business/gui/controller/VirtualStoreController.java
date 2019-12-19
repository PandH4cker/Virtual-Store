package store.business.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import store.business.gui.view.NewClientView;
import store.business.util.client.Client;
import store.business.util.client.exception.MalformedClientParameterException;
import store.business.util.logger.level.Level;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;
import store.business.util.parser.ClientParser;
import store.business.util.parser.ProductParser;
import store.business.util.product.*;
import store.business.util.product.exception.NoMoreOfThisProductException;
import store.business.util.transaction.Transaction;
import store.business.util.transaction.exception.MalformedTransactionParameterException;
import store.business.util.writer.TransactionWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * <h1>The main controller of the FXML App</h1>
 * <p>
 *     The {@code public class VirtualStoreController} class implements the
 *     {@code public interface Initializable} interface in order to
 *     setup our {@code ComboBox<?>}.
 *     It handle Button, Text Area and Text Input mouse events / context menu events
 * </p>
 * <img src="../../../../uml/VirtualStoreController.png" />
 *
 * @author Dray Raphael
 * @version 1.0.0
 * @since 1.0.0
 * @see Initializable
 * @see FXML
 * @see Logger
 * @see ProductParser
 * @see ClientParser
 * @see store.business.util.parser.Parser
 * @see Client
 * @see Product
 * @see Transaction
 * @see TransactionWriter
 * @see store.business.util.writer.ClientWriter
 * @see Logger
 */
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

    private final Logger logger = LoggerFactory.getLogger("VirtualStoreController");
    private final ProductParser productParser;
    private ClientParser clientParser;
    private String selectedProduct;
    private String selectedClient;
    private int selectedAmount;
    private Client client;
    private Product product;
    private NewClientView newClientView;

    /**
     * This constructor initialize the parsers
     * @see ProductParser
     * @see ClientParser
     * @see store.business.util.parser.Parser
     */
    public VirtualStoreController() {
        this.productParser = new ProductParser();
        this.clientParser = new ClientParser();
        this.newClientView = new NewClientView(this.clientParser);
    }

    /**
     * {@inheritDoc}
     * @param location {@inheritDoc}
     * @param resources {@inheritDoc}
     * @see Override
     * @see URL
     * @see ResourceBundle
     * @see ComboBox<String>
     * @see ProductParser
     * @see ComboBox<Integer>
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.comboBoxCategory.getItems().add("Tous");
        for(String s : this.productParser.getAttributes())
            this.comboBoxCategory.getItems().add(s);
        this.comboBoxAmount.getItems().addAll(1, 2, 4, 8, 10, 20, 40, 50, 100);
        this.logger.log("Virtual Store Controller Initialized", Level.INFO);
    }

    /**
     * Called to clear fields when a product is bought
     * @see TextField
     * @see TextArea
     * @see Label
     */
    private void clearFields() {
        this.clientNameTextField.clear();
        this.currentClientDescription.clear();
        this.currentProductDescription.clear();
        this.currentClientNameLabel.setText("Nom du client courant");
        this.currentProductLabel.setText("Nom du produit courant");
    }

    /**
     * Handle close action
     * @param event {@inheritDoc}
     * @see System
     * @see FXML
     * @see MouseEvent
     */
    @FXML
    public void handleCloseAction(MouseEvent event) {
        this.logger.log("Close Action Event", Level.INFO);
        if(event.getSource() == this.closerLabel)
            System.exit(0);
    }

    /**
     * Handle the search by category
     * @param event {@inheritDoc}
     * @see FXML
     * @see MouseEvent
     * @see Button
     * @see ComboBox<String>
     * @see TextArea
     */
    @FXML
    public void handleSearchByCategoryAction(MouseEvent event) {
        this.logger.log("Search By Category Event", Level.INFO);
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

    /**
     * Handle the context menu of the selected text
     * @param event {@inheritDoc}
     * @see ContextMenuEvent
     * @see FXML
     * @see TextArea
     * @see Product
     * @see ProductParser
     * @exception RuntimeException Unknown product
     * @see RuntimeException
     * @see ImageView
     * @see Image
     * @see FileInputStream
     * @see Paths
     * @exception FileNotFoundException File not found
     * @see FileNotFoundException
     * @see Label
     */
    @FXML
    public void handleSelectedTextAction(ContextMenuEvent event) {
        this.logger.log("Selected Text Event", Level.INFO);
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
                        this.logger.log(e.getMessage(), Level.ERROR);
                    }
                    this.currentProductLabel.setText(product.getName());
                }
            }
        }
    }

    /**
     * Handle the search by client
     * @param event {@inheritDoc}
     * @see FXML
     * @see MouseEvent
     * @see Button
     * @see TextField
     * @see Client
     * @see ClientParser
     * @exception RuntimeException Unknown Client
     * @see RuntimeException
     * @see TextArea
     * @see Label
     * @see NewClientView
     */
    @FXML
    public void handleSearchByClientAction(MouseEvent event) {
        this.logger.log("Search By Client Event", Level.INFO);
        if(event.getSource() == this.searchByClientButton) {
            this.clientParser = new ClientParser();
            this.selectedClient = this.clientNameTextField.getText().trim();
            if(this.selectedClient.length() != 0) {
                if (this.selectedClient.matches("[a-zA-Z]+\\s[a-zA-Z]+\\s[a-zA-Z]+"))
                    this.selectedClient = this.selectedClient.replaceFirst("\\s", "_");
                String[] splittedClientName = this.selectedClient.split(" ");
                splittedClientName[0] = splittedClientName[0].replace("_", " ");
                Client client = null;
                if(splittedClientName.length > 1)
                    for(Client c : this.clientParser.getEList()) {
                        if ((c.getName().trim().equals(splittedClientName[0]) && c.getSurname().trim().equals(splittedClientName[1]))
                                || (c.getName().trim().equals(splittedClientName[1]) && c.getSurname().trim().equals(splittedClientName[0]))) {
                            client = c;
                            break;
                        }
                    }
                if(client == null) {
                    if(this.clientNameTextField.getText().trim().length() != 0)
                        this.clientNameTextField.clear();
                    try { this.newClientView.start(new Stage()); }
                    catch (Exception e) { this.logger.log(e.getMessage(), Level.ERROR); }
                }
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

    /**
     * Handle the buy button
     * @param event {@inheritDoc}
     * @see FXML
     * @see MouseEvent
     * @see Button
     * @see ComboBox<Integer>
     * @see TextArea
     * @see Product
     * @see TransactionWriter
     * @see Transaction
     * @see Client
     * @see Date
     * @exception NoMoreOfThisProductException No More Of This Product
     * @see NoMoreOfThisProductException
     */
    @FXML
    public void handleBuyButton(MouseEvent event) {
        this.logger.log("Buy Button Event", Level.INFO);
        if(event.getSource() == this.buyButton) {
            this.selectedAmount = this.comboBoxAmount.getSelectionModel().getSelectedItem();
            if(this.selectedAmount > 0)
                if(this.currentClientDescription.getText().trim().length() != 0 &&
                   this.currentProductDescription.getText().trim().length() != 0) {
                    try {
                        this.product.tookProduct(this.selectedAmount);
                        new TransactionWriter(
                                new Transaction(this.client.getUniqueID(),
                                                this.product.getUniqueID(),
                                                this.selectedAmount,
                                                new Date()));
                    } catch (NoMoreOfThisProductException | MalformedTransactionParameterException e) {
                        this.logger.log(e.getMessage(), Level.WARNING);
                    }
                    clearFields();
                }
        }
    }
}