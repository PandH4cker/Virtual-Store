package store.business.gui.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import store.business.gui.view.NewClientView;
import store.business.util.client.Client;
import store.business.util.logger.Logger;
import store.business.util.logger.LoggerFactory;
import store.business.util.logger.level.Level;
import store.business.util.parser.ClientParser;
import store.business.util.writer.ClientWriter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * <h1>The Controller for creating a new client</h1>
 * <p>
 *     The {@code public class NewClientController} class implements the
 *     {@code public interface Initializable} interface in order to unfocus all the components
 *     of the view and to add a listener on each Input Text to disable/enable the submit button
 * </p>
 *
 * @author Dray Raphael
 * @version 1.0.0
 * @since 1.0.0
 * @see Initializable
 * @see FXML
 * @see Logger
 * @see ClientParser
 */
public class NewClientController implements Initializable {
    @FXML
    private TextField nameTextField;
    @FXML
    private TextField surnameTextField;
    @FXML
    private TextField addressTextField;
    @FXML
    private TextField postalCodeTextField;
    @FXML
    private Button saveButton;
    @FXML
    private Label panelNameLabel;

    private final Logger logger = LoggerFactory.getLogger("NewClientController");
    private ClientParser clientParser;

    /**
     * This constructor initialize the clientParser by the information given by the view
     * @see ClientParser
     * @see NewClientView
     */
    public NewClientController() {
        this.clientParser = NewClientView.getClientParser();
    }

    /**
     * {@inheritDoc}
     * @param location {@inheritDoc}
     * @param resources {@inheritDoc}
     * @see Override
     * @see URL
     * @see ResourceBundle
     * @see TextField
     * @see Logger
     * @see Level
     * @see Button
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.unfocusedAll();

        this.nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.logger.log("Change Text Event up", Level.INFO);
            if(this.canBeEnable()) {
                this.saveButton.setDisable(false);
                this.logger.log("Button Enable", Level.INFO);
            }
            else {
                this.saveButton.setDisable(true);
                this.logger.log("Button Disable", Level.INFO);
            }
        });

        this.surnameTextField.textProperty().addListener(((observable, oldValue, newValue) -> {
            this.logger.log("Change Text Event up", Level.INFO);
            if(this.canBeEnable()) {
                this.saveButton.setDisable(false);
                this.logger.log("Button Enable", Level.INFO);
            }
            else {
                this.saveButton.setDisable(true);
                this.logger.log("Button Disable", Level.INFO);
            }
        }));

        this.addressTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.logger.log(" Change Text Event up", Level.INFO);
            if(this.canBeEnable()) {
                this.saveButton.setDisable(false);
                this.logger.log("Button Enable", Level.INFO);
            }
            else {
                this.saveButton.setDisable(true);
                this.logger.log("Button Disable", Level.INFO);
            }
        });

        this.postalCodeTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            this.logger.log("Change Text Event up", Level.INFO);
            if(this.canBeEnable()) {
                this.saveButton.setDisable(false);
                this.logger.log("Button Enable", Level.INFO);
            }
            else {
                this.saveButton.setDisable(true);
                this.logger.log("Button Disable", Level.INFO);
            }
        });

        this.logger.log("New Client Controller Initialized", Level.INFO);
    }

    /**
     * Handle the name label to be focused
     * @param event {@inheritDoc}
     * @see Label
     * @see MouseEvent
     */
    public void handlePanelNameLabel(MouseEvent event) {
        if(event.getSource() == this.panelNameLabel) this.panelNameLabel.requestFocus();
    }

    /**
     * Handle the closer label to close only this view from where come the event
     * @param event {@inheritDoc}
     * @see MouseEvent
     */
    public void handleCloserLabel(MouseEvent event) {
        closeView(event);
    }

    /**
     * Handle the save button to save the informations of the new client
     * @param event {@inheritDoc}
     * @see MouseEvent
     * @see Button
     * @see ClientParser
     * @see TextField
     */
    public void handleSaveButton(MouseEvent event) {
        if(event.getSource() == this.saveButton) {
            this.clientParser = new ClientParser();
            final Client client = new Client(this.nameTextField.getText().trim(),
                    this.surnameTextField.getText().trim(),
                    this.addressTextField.getText().trim(),
                    Integer.parseInt(this.postalCodeTextField.getText().trim()));
            if(!this.clientParser.getEList().contains(client)) new ClientWriter(client);
            closeView(event);
        }
    }

    /**
     * Close view from where come an event
     * @param event {@inheritDoc}
     * @see MouseEvent
     * @see Node
     * @see Logger
     * @see Level
     */
    private void closeView(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        try { NewClientView.instanceClosed(); }
        catch (IOException e) { this.logger.log(e.getMessage(), Level.ERROR); }
    }

    /**
     * Check if the save button can be enable
     * @return {@code true} or {@code false} if there is not text in one Text Field
     * @see TextField
     */
    private boolean canBeEnable() {
        return this.isThereText(this.nameTextField) &&
               this.isThereText(this.surnameTextField) &&
               this.isThereText(this.addressTextField) &&
               this.isThereText(this.postalCodeTextField);
    }

    /**
     * Check if there is text in a Text Field
     * @param tf The TextField to check
     * @return {@code true} or {@code false} if there is not text in the Text Field
     * @see TextField
     */
    private boolean isThereText(TextField tf) {
        return tf.getText().trim().length() != 0;
    }

    /**
     * Unfocus every Text Field
     * @see TextField
     */
    private void unfocusedAll() {
        this.nameTextField.setFocusTraversable(false);
        this.surnameTextField.setFocusTraversable(false);
        this.addressTextField.setFocusTraversable(false);
        this.postalCodeTextField.setFocusTraversable(false);
    }

}