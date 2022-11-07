package com.med_consultant.frontend;

import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class RemoveDoctorController {
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;
    @FXML
    private Label error;


    @FXML
    public void clickRemove() {
        String input = surname.getText() + " " + name.getText() + " " + patronymic.getText();
        if(surname.getText().equals("") || name.getText().equals("") || patronymic.getText().equals("")){
            error.setText("Введите данные для поиска!");
        }else{
            try{
                int res = Main.removeDoctor(input);
                if(res == -1){
                    error.setText("Такого врача нет!");
                }else{
                    error.setText("Успешно удалено.");
                }
            }catch (Exception ex){
                error.setText("Данные введены не корректно!");
            }

        }
    }
}
