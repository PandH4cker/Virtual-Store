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

    public NewClientController() {
        this.clientParser = NewClientView.getClientParser();
    }

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

    public void handlePanelNameLabel(MouseEvent event) {
        if(event.getSource() == this.panelNameLabel) this.panelNameLabel.requestFocus();
    }

    public void handleCloserLabel(MouseEvent event) {
        closeView(event);
    }

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

    private void closeView(MouseEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
        try { NewClientView.instanceClosed(); }
        catch (IOException e) { this.logger.log(e.getMessage(), Level.ERROR); }
    }

    private boolean canBeEnable() {
        return this.isThereText(this.nameTextField) &&
               this.isThereText(this.surnameTextField) &&
               this.isThereText(this.addressTextField) &&
               this.isThereText(this.postalCodeTextField);
    }

    private boolean isThereText(TextField tf) {
        return tf.getText().trim().length() != 0;
    }

    private void unfocusedAll() {
        this.nameTextField.setFocusTraversable(false);
        this.surnameTextField.setFocusTraversable(false);
        this.addressTextField.setFocusTraversable(false);
        this.postalCodeTextField.setFocusTraversable(false);
    }

}
