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
    private TableColumn<Hospitals, String> surname;
    @FXML
    private TableColumn<Hospitals, String> name;
    @FXML
    private TableColumn<Hospitals, String> patronymic;
    @FXML
    private TableColumn<Hospitals, Integer> numCabinet;
    @FXML
    private Label error;

    @FXML
    public void search() throws IOException {
        if(numHospital.getText().equals("") || speciality.getText().equals("")){
            error.setText("Введите данных для формирования отчёта!");
        }else{
            try{
                ArrayList<Hospitals> res = Main.search(Integer.parseInt(numHospital.getText()), speciality.getText());
                if(res.size() == 0){
                    error.setText("Врачи не найдены!");
                }else{
                    ObservableList<Hospitals> hospitals = FXCollections.observableArrayList(res);
                    table.setItems(hospitals);
                    error.setText("");
                }
            }catch (Exception ex){
                error.setText("Данные не корректны!");
            }
        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surname.setCellValueFactory(new PropertyValueFactory<>("surname"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymic.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        numCabinet.setCellValueFactory(new PropertyValueFactory<>("numCabinet"));
    }
}

