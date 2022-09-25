package com.med_consultant.frontend;

import com.med_consultant.backend.Main;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Add implements Initializable {
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
    public void clickAdd() throws IOException {
        String input = numHospital.getText() + " " + surname.getText() + " " + name.getText() + " " +
                patronymic.getText() + " " + numCabinet.getText();
        Main.add(input);

    };

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
