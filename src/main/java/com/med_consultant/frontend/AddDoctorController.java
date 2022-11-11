package com.med_consultant.frontend;

import com.med_consultant.Application;
import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        if((!judgeContainsStrName(surname.getText())) || (!judgeContainsStrName(name.getText())) || (!judgeContainsStrName(patronymic.getText()))
                || (!judgeContainsStrName(speciality.getText()))) {
            error.setText("Строковые данные введены не корректно!");
            return;
        } else if (!judgeContainsIntName(experience.getText())) {
            error.setText("Числовые данные введены не корректно!");
            return;
        }
        String input = surname.getText() + " " + name.getText() + " " + patronymic.getText() + " " +
                experience.getText() + " " + speciality.getText();
        if(surname.getText().equals("") || name.getText().equals("") || patronymic.getText().equals("")){
            error.setText("Введите данные для поиска!");
        }else{

            try{
                if(Main.addDoctor(input) == -1){
                    error.setText("Такой врач уже есть!");
                }else if(judgeContainsStrName(input)){
                    error.setText("Данные введены не корректно!");
                }else{
                    error.setText("");
                    Application.addDoctor.close();
                }
            }catch (Exception ex){
                error.setText("Данные введены не корректно!");
            }
        }
    }

    public boolean judgeContainsStrName(String str) {
        String regex="[А-ЯA-Z]{1,1}[а-яА-Яa-zA-Z]+$";
        Matcher match = Pattern.compile(regex).matcher(str);
        if (str.length() > 30)
            return false;
        return match.matches();
    }

    public boolean judgeContainsIntName(String str) {
        String regex="[0-9]+$";
        Matcher match = Pattern.compile(regex).matcher(str);
        if (str.length() > 100)
            return false;
        return match.matches();
    }
}
