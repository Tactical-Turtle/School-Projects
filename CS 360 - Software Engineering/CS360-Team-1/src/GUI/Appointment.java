package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;



public class Appointment implements Initializable {
    @FXML
    private TextField firstnameField;

    @FXML
    private TextField lastnameField;

    @FXML
    private TextField doctorField;

    @FXML
    private TextField dateField;

    @FXML
    private TextField timeField;

    @FXML
    private TextArea reasonField;

    @FXML
    private CheckBox emailBox;

    @FXML
    private Button apptButton;

    @FXML
    private Button cancelButton;

    @FXML
    private Pane pane;



    @FXML
    void handleCancelEvent(ActionEvent event) throws IOException {

         Pane pane2=  FXMLLoader.load(getClass().getResource("homepage.fxml"));
        pane.getChildren().setAll(pane2);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }



}
