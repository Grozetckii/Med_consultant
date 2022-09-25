package com.med_consultant.frontend;

import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddDoctorController {
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
    private Button add;

    @FXML
    public void clickAdd() {
        String input = numHospital.getText() + " " + surname.getText() + " " + name.getText() + " " +
                patronymic.getText() + " " + numCabinet.getText();
        Main.add(input);
    }
}
