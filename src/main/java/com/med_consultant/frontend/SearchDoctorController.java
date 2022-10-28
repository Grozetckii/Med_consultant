package com.med_consultant.frontend;

import com.med_consultant.backend.Doctors;
import com.med_consultant.backend.Main;
import javafx.beans.Observable;
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

public class SearchDoctorController implements Initializable {
    @FXML
    private TextField speciality;

    @FXML
    private Label error;

    @FXML
    private TableView<Doctors> tableDoctors;

    @FXML
    private TableColumn<Doctors, String> surnameColumn;

    @FXML
    private TableColumn<Doctors, String> nameColumn;

    @FXML
    private TableColumn<Doctors, String> patronymicColumn;

    @FXML
    private TableColumn<Doctors, String> experienceColumn;

    @FXML
    public void clickSearch(){
        ArrayList<Doctors> res = Main.searchDoctor(speciality.getText());
        if(res.size() == 0){
            error.setText("Доктор не найден");
        }else{
            ObservableList<Doctors> doctors = FXCollections.observableArrayList(res);
            tableDoctors.setItems(doctors);
            error.setText("");
        }
        /*try{

        }catch (Exception ex){

        }*/
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("surname"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        patronymicColumn.setCellValueFactory(new PropertyValueFactory<>("patronymic"));
        experienceColumn.setCellValueFactory(new PropertyValueFactory<>("experience"));
    }
}
