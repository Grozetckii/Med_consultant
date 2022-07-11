package com.med_consultant;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Application extends javafx.application.Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("main.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("header.css")).toExternalForm());
        stage.setTitle("Медконсультант");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();
    }

    public static void add() throws IOException {
        Stage add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("add.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        add.setTitle("Добавление");
        add.setResizable(false);
        add.setScene(scene);
        add.show();
    }

    public static void remove() throws IOException {
        Stage remove = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("remove.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        remove.setTitle("Удаление");
        remove.setScene(scene);
        remove.show();
    }

    public static void search() throws IOException {
        Stage search = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("search.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        search.setTitle("Поиск");
        search.setScene(scene);
        search.show();
    }

    public static void debug() throws IOException {
        Stage debug = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("debug.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        debug.setTitle("Отладка");
        debug.setScene(scene);
        debug.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
