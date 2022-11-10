package com.med_consultant.frontend;

import com.med_consultant.Application;
import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class AddHospitalController {
    @FXML
    private Label error;
    @FXML
    private TextField numHospital;
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField numCabinet;


    @FXML
    public void clickAdd() {
        String input = numHospital.getText() + " " + surname.getText() + " " + name.getText() + " " +
                patronymic.getText() + " " + numCabinet.getText();
        if((numHospital.getText().equals("") || surname.getText().equals("") || name.getText().equals("") ||
                patronymic.getText().equals("") || numCabinet.getText().equals(""))){
            error.setText("Введите данные для добавления!");
        }else{
            try{
                int res = Main.addHospital(input);
                if(res == -1){
                    error.setText("Такой врач в этой больнице уже есть!");
                }else if(res == -2){
                    error.setText("Такого врача нет. Сначало добавьте его в справочник врачей.");
                }else{
                    error.setText("");
                    Application.addHospital.close();
                }
            }catch (Exception ex){
                error.setText("Данные введены не корректно!");
            }
        }
    }
}
