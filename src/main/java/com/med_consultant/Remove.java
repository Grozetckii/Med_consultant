package com.med_consultant;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Remove implements Initializable {
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
    private TextField experience;
    @FXML
    private TextField speciality;
    @FXML
    private Button removeButton;

    @FXML
    public void clickButton() throws IOException {
        String input = numHospital.getText() + surname.getText() + name.getText() + patronymic.getText()
                + numCabinet.getText() + experience.getText() + speciality.getText();
        Main.main("remove", input);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
