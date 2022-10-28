package com.med_consultant.frontend;

import com.med_consultant.backend.Doctors;
import com.med_consultant.backend.Hospitals;
import com.med_consultant.backend.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    @FXML
    private TextField numHospital;
    @FXML
    private TextField speciality;

    @FXML
    private TableView<Hospitals> table;
    @FXML
    private TableView<Doctors> table2;
    @FXML
    private TableColumn<Hospitals, String> surname;
    @FXML
    private TableColumn<Hospitals, String> name;
    @FXML
    private TableColumn<Hospitals, String> patronymic;
    @FXML
    private TableColumn<Doctors, Integer> experience;
    @FXML
    private TableColumn<Hospitals, String> time;

    @FXML
    public void search() throws IOException {
        ArrayList<Hospitals> res = Main.search(Integer.parseInt(numHospital.getText()), speciality.getText());
        ObservableList<Hospitals> hospitals = FXCollections.observableArrayList(res);
        table.setItems(hospitals);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        experience.setCellValueFactory(new PropertyValueFactory<>("experience"));
        time.setCellValueFactory(new PropertyValueFactory<>("numCabinet"));

    }
}

