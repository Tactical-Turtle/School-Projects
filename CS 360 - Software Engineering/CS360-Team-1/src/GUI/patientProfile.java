package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Menu;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sun.java2d.pipe.SpanShapeRenderer;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;

public class patientProfile implements Initializable {

    private String firstName, middleName, lastName, cellNumber, address1,address2,cityStateZip,
            SSN, sex, DOB, work, email, emName, allergies, emergencyRelation, emergencyCell,
    emwork, firstVisit = null, lastVisit = null, roomNumber;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField lastnamefield;

    @FXML
    private TextField middleField;

    @FXML
    private TextField firstnameField;

    @FXML
    private TextField ssnField;

    @FXML
    private TextField dateOfBirthField;

    @FXML
    private TextField address1Field;

    @FXML
    private TextField address2Field;

    @FXML
    private TextField cszField;

    @FXML
    private TextField doctorField;

    @FXML
    private TextField cellField;

    @FXML
    private TextArea notesFIeld;

    @FXML
    private ChoiceBox<String> sexBox;

    @FXML
    private TextField workField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField emnameField;

    @FXML
    private TextArea allergiesField;

    @FXML
    private TextField emrelationField;

    @FXML
    private TextField emcellField;

    @FXML
    private TextField emworkField;


    @FXML
    private DatePicker firstvisitDate;

    @FXML
    private DatePicker lastvisitDate;

    @FXML
    private TextField roomField;

    @FXML
    private Button deleteButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button prescriptionButton;

    @FXML
    private Button backButton;

    @FXML
    void handledeleteEvent(ActionEvent event) throws IOException {

        //After deleting the patient it goes back to the homepage
            Pane homepage =  FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(homepage);
    }

    @FXML

    void handleBackEvent(ActionEvent event) throws IOException {

        Pane page = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(page);
    }

    @FXML
    void handlesaveEvent(ActionEvent event) {
        // this is the function that will insert the data into the patient table

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
           //Connection conn = DriverManager.getConnection("jdbc:mysql://<inputDB-URL>", "root", "<inputpw>");

            ResultSet rs = null;
            PreparedStatement stmt = conn.prepareStatement("Insert into Patient_T (PatientID,doctorID, nurseID, PatientFirstName, PatientMiddleName, PatientLastName," +
                    "PatientPhoneNumber, PatientAddress1, PatientAddress2, " +
                    "PatientCity, PatientState, PatientEmail, PatientDateOfBirth," +
                    "PatientSex, PatientSSN, PatientCellNumber, PatientWorkNumber, PatientZipCode," +
                    "PatientType, PatientRoomNumber, PatientFirstVisit," +
                    "PatientLastVisit, PatientNextAppointment, PatientAllergies)" +
                    "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
            Date birthday = null;

            if(DOB != null)  birthday = sdf.parse(DOB);
            Random rn = new Random();
            getTextFieldValues(); // set the different values needed to insert a patient into the patients table
            stmt.setInt(1,rn.nextInt(5000)+0); // patient ID
            stmt.setInt(2, 12345); //doctor ID
            stmt.setInt(3, 45678); // nurse ID
            stmt.setString(4, firstName); // patient firstName
            stmt.setString(5,middleName); // patient middleName
            stmt.setString(6, lastName); // patient lastName
            stmt.setString(7,cellNumber); // patient cellphone
            stmt.setString(8,address1); // patient address 1
            stmt.setString(9,address2); // patient address 2
            stmt.setString(10,null);  // patient city
            stmt.setString(11,null); // patient state
            stmt.setString(12,email); //patient email
            stmt.setDate(13, null); // patient birth date
            stmt.setString(14,sex); // patient sex
            stmt.setString(15, SSN); // patient ssn
            stmt.setString(16, null); // patient cell number
            stmt.setString(17,null); // patient work number
            stmt.setString(18,null); // patient zipcode
            stmt.setString(19, null); // patient type
            stmt.setInt(20, Integer.parseInt(roomNumber)); // patient room number
            stmt.setString(21, firstVisit); // patient first visit
            stmt.setString(22,lastVisit); // patient last visit
            stmt.setString(23,null); // patient next appt
            stmt.setString(24, allergies); // Allergies

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Added Patient to Database");
            Pane page = FXMLLoader.load(getClass().getResource("homepage.fxml"));
            anchorPane.getChildren().setAll(page);
            // might have to generate a patient ID and manually add doctor and nurse IDs to the patient
        }catch(Exception a){
            JOptionPane.showMessageDialog(null, "Insert failed");
            a.printStackTrace();
        }

    }



    @FXML
    void handlewriteEvent(ActionEvent event) throws IOException {
        Pane homepage =  FXMLLoader.load(getClass().getResource("prescriptionInfo.fxml"));
        anchorPane.getChildren().setAll(homepage);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {




    }

    public void getTextFieldValues(){
        firstName = firstnameField.getText();
        middleName = middleField.getText();
        lastName = lastnamefield.getText();
        cellNumber = cellField.getText();
        address1 = address1Field.getText();
        address2 = address2Field.getText();
        cityStateZip = cszField.getText(); // need to break this up into the components by delimiting on the comma
        SSN = ssnField.getText();
        sex = sexBox.getValue();
        DOB = dateOfBirthField.getText();
        work = workField.getText();
        email = emailField.getText();
        emName = emnameField.getText();
        allergies = allergiesField.getText();
        emergencyRelation = emrelationField.getText();
        emergencyCell = emcellField.getText();
        emwork = emworkField.getText();
   //     firstVisit = firstvisitDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
    //    lastVisit = lastvisitDate.getValue().format(DateTimeFormatter.ofPattern("yyyy-mm-dd"));
        roomNumber = roomField.getText();
    }


}
