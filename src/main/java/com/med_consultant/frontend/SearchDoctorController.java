package com.med_consultant.frontend;

import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SearchDoctorController {
    @FXML
    private TextField surname;
    @FXML
    private TextField name;
    @FXML
    private TextField patronymic;

    @FXML
    public void clickSearch(){
        Main.searchDoctor(surname.getText(), name.getText(), patronymic.getText());
    }
}
