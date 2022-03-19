package GUI;

import com.mysql.cj.protocol.Resultset;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class patientList implements Initializable {

    @FXML
    private TextField filterField;
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TableView<patient> tableview;

    @FXML
    private TableColumn<patient, String> col_last;

    @FXML
    private TableColumn<Person,String> col_first;

    @FXML
    private TableColumn<patient,String> col_room;

    @FXML
    private TableColumn<patient,String> col_doc;
    @FXML
    private Button backButton;


    ObservableList<patient> dataList = FXCollections.observableArrayList();


    @FXML
    void handleReturnEvent(ActionEvent event) throws IOException {
        Pane returnPane = FXMLLoader.load(getClass().getResource("homepage.fxml"));
        anchorPane.getChildren().setAll(returnPane);

    }




    @FXML
    void search_user(){
        col_last.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        col_first.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        col_doc.setCellValueFactory(new PropertyValueFactory<>("primaryDoc"));
        col_room.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));


        try{
            //create connection to database and then try to pull first and last names
            // as well as room number and the doctor from the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Connection conn = DriverManager.getConnection("jdbc:mysql://<inputDB-URL>", "root", "<inputpw>");
            PreparedStatement stmt = conn.prepareStatement("select PatientFirstName, PatientLastName, PatientRoomNumber, DoctorID from Patient_t");
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
               String firstname = rs.getString("PatientFirstName");
               String lastName = rs.getString("PatientLastName");
               int roomNumber = rs.getInt("PatientRoomNumber");
               int doctorID = rs.getInt("DoctorID");
               PreparedStatement stmt2 = conn.prepareStatement("select firstName from Doctor where DoctorID = ?");
               stmt2.setInt(1,doctorID);
               ResultSet rs2 = stmt2.executeQuery();
               String doctorName = null;

               if(rs2.next()) doctorName = rs2.getString("firstName");
               patient p = new patient();
               p.setFirstName(firstname);
               p.setLastName(lastName);
               p.setPrimaryDoc(doctorName);
               p.setRoomNumber(roomNumber);
               dataList.add(p);
            }
        }catch(Exception e){e.printStackTrace();}

       tableview.setItems(dataList);



        FilteredList<patient> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(patient -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (patient.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (patient.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (String.valueOf(patient.getRoomNumber()).toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (patient.getPrimaryDoc().indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<patient> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_user();
    }
}
