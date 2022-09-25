package com.med_consultant.frontend;

import com.med_consultant.backend.Doctors;
import com.med_consultant.backend.Hospitals;
import com.med_consultant.backend.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class SearchHospitalsController implements Initializable {
    @FXML
    private TextField numHospital;

    @FXML
    private Label error;

    @FXML
    private TableView<Hospitals> tableHospital;

    @FXML
    private TableColumn<Hospitals, String> surnameColumn;

    @FXML
    private TableColumn<Hospitals, String> nameColumn;

    @FXML
    private TableColumn<Hospitals, String> patronymicColumn;

    @FXML
    private TableColumn<Hospitals, Integer> numCabinetColumn;

    @FXML
    public void clickSearchHospital(){
        ArrayList<Hospitals> data = Main.searchHospital(Integer.parseInt(numHospital.getText()));
        try{
            ObservableList<Hospitals> hospital = FXCollections.observableArrayList(data);
            tableHospital.setItems(hospital);
            error.setText("");
        }catch (Exception ex){
            error.setText("Больница не найдена!");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        numCabinetColumn.setCellValueFactory(new PropertyValueFactory<>("numCabinet"));
    }
}
