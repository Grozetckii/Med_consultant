package com.example.med_consultant;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    //public static final Label NOTHING = ;
    public static final Label Response = new Label("aboba");
    @FXML
    private final Label welcomeText;

    public HelloController(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Ничего не нашлось(((");
    }
}