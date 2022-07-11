package com.med_consultant;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private TableView<Doctors> tableDoctors;
    @FXML
    private TableColumn<Doctors, String> surnameColumn;
    @FXML
    private TableColumn<Doctors, String> nameColumn;
    @FXML
    private TableColumn<Doctors, String> patronymicColumn;
    @FXML
    private TableColumn<Doctors, Integer> experienceColumn;
    @FXML
    private TableColumn<Doctors, String> specialityColumn;
    @FXML
    private TableView<Hospitals> tableHospitals;
    @FXML
    private TableColumn<Hospitals, Integer> numHospitalColumn;
    @FXML
    private TableColumn<Hospitals, String> surnameColumn2;
    @FXML
    private TableColumn<Hospitals, String> nameColumn2;
    @FXML
    private TableColumn<Hospitals, String> patronymicColumn2;
    @FXML
    private TableColumn<Hospitals, Integer> numCabinetColumn;

    @FXML
    public void add() throws IOException {Application.add();}
    @FXML
    public void remove() throws IOException {Application.remove();}
    @FXML
    public void search() throws IOException {Application.search();}
    @FXML void debug() throws IOException {Application.debug();}

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Main._init_();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
        specialityColumn.setCellValueFactory(new PropertyValueFactory<>("speciality"));

        ObservableList<Doctors> doctors = FXCollections.observableArrayList(Main.doctorArr);

        tableDoctors.setItems(doctors);


        numHospitalColumn.setCellValueFactory(new PropertyValueFactory<>("numHospital"));
        surnameColumn2.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicColumn2.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        numCabinetColumn.setCellValueFactory(new PropertyValueFactory<>("numCabinet"));

        ObservableList<Hospitals> hospitals = FXCollections.observableArrayList(Main.hospitalArr);

        tableHospitals.setItems(hospitals);
    }
}