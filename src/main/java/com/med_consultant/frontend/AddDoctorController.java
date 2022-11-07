package com.med_consultant.frontend;

import com.med_consultant.Application;
import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddDoctorController {
    @FXML
    private Label error;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField experience;
    @FXML
    private  TextField speciality;

    @FXML
    public void clickAdd() {
        String input = surname.getText() + " " + name.getText() + " " + patronymic.getText() + " " +
                experience.getText() + " " + speciality.getText();
        if(surname.getText().equals("") || name.getText().equals("") || patronymic.getText().equals("")){
            error.setText("Введите данные для поиска!");
        }else{
            try{
                if(Main.addDoctor(input) == -1){
                    error.setText("Такой врач уже есть!");
                }else{
                    error.setText("");
                    Application.addDoctor.close();
                }
            }catch (Exception ex){
                error.setText("Данные введены не корректно!");
            }
        }
    }
}
