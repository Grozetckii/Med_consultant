package com.med_consultant.frontend;

import com.med_consultant.Application;
import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoveHospitalController {
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private TextField numHospital;
    @FXML
    private Label error;
    @FXML
    public void clickRemove(){
        String input = surname.getText() + " " + name.getText() + " " + patronymic.getText() + " " + numHospital.getText();
        if(!(numHospital.getText().equals("") || surname.getText().equals("") || name.getText().equals("") ||
                patronymic.getText().equals(""))){
            error.setText("Введите данные для удаления!");
        }else{
            try{
                int res = Main.removeHospital(input);
                if(res == -2){
                    error.setText("Такой больницы нет!");
                }else if(res == -1){
                    error.setText("Такого врача в этой больнице нет!");
                }else{
                    error.setText("Успешно удалено.");
                    Application.removeHospital.close();
                }
            }catch(Exception ex){
                error.setText("Данные введены не корректно!");
            }
        }
    }
}
