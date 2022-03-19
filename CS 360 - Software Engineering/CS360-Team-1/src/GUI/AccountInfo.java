package GUI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;

import javax.swing.*;
import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AccountInfo implements Initializable {
    ObservableList<String> list= FXCollections.observableArrayList();

    @FXML
    private ListView<String> listView;

    @FXML
    private Button backButton;

    @FXML
    private AnchorPane anchorPane;


    @FXML
    void handleBackEvent(ActionEvent event) throws IOException {

       AnchorPane pane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(pane);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb){

        loadData();
    }

    private void loadData(){
        list.addAll("Debbie Butcher","Ariel Pink","Kevin Shields","Earl Stone","Rachel Goswell","Melinda Lawson","Alessandra Neville","Mohamed Rudd","Kara Atkins");

        listView.setItems(list);

    }

}
