package GUI;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

public class appointmentList implements Initializable {

    @FXML
    private TableColumn<?, String> col_date;

    @FXML
    private TableColumn<?, String> col_time;

    @FXML
    private TableColumn<?, String> col_patient;

    @FXML
    private TableColumn<?, String> col_details;

    @FXML
    private ComboBox<String> apptBox;

    @FXML
    private TextField filterField;

    @FXML
    private Pane pane;

    @FXML
    private TableView<Employee> tableview;

    ObservableList<Employee> dataList = FXCollections.observableArrayList();

    void search_Appt(){
        col_date.setCellValueFactory(new PropertyValueFactory<>(" "));
        col_patient.setCellValueFactory(new PropertyValueFactory<>(" "));
        col_time.setCellValueFactory(new PropertyValueFactory<>(" "));
        col_details.setCellValueFactory(new PropertyValueFactory<>(" "));

        FilteredList<Employee> filteredData = new FilteredList<>(dataList, b -> true);
        filterField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(employee -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (employee.getLastName().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    return true; // Filter matches username
                } else if (employee.getFirstName().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }else if (employee.getAccType().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true; // Filter matches password
                }
                else if (employee.getUserName().indexOf(lowerCaseFilter)!=-1)
                    return true;// Filter matches email

                else
                    return false; // Does not match.
            });
        });
        SortedList<Employee> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);


        apptBox.setItems(FXCollections.observableArrayList("Doctor","Nurse"));
        FilteredList<Employee> filteredData2 = new FilteredList<>(dataList);
        apptBox.valueProperty().addListener(new ChangeListener<String>(){

            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue){
                filteredData2.setPredicate(newValue == null ? null : (Employee e) -> newValue.equals(e.getAccType()));
            }

        });
        SortedList<Employee>  sortedData2 = new SortedList<>(filteredData2);
        tableview.setItems(sortedData2);



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
