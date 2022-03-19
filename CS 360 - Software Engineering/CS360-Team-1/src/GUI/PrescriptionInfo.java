package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class PrescriptionInfo implements Initializable {
    private emailRX sendEmail;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField dateField;
    @FXML
    private TextArea prescriptionArea;
    @FXML
    private TextArea instructionsArea;
    @FXML
    private TextArea notesArea;
    @FXML
    private Button cancelButton;

    @FXML
    private Button prescpriptionButton;

    @FXML
    private CheckBox emailcheckBox;

    @FXML
    private AnchorPane anchorPane;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }

    @FXML
    void handleCancelEvent(ActionEvent event) throws IOException {

        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(pane);

    }

    @FXML
    void handlePrescriptionEvent(ActionEvent event) throws IOException {
        sendEmail = new emailRX();
       sendEmail.emailRx(firstNameField.getText(),emailField.getText(),prescriptionArea.getText(),instructionsArea.getText(),notesArea.getText());
        AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(pane);
    }

}
