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

    public static void addDoctor() throws IOException {
        Stage add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addDoctor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        add.setTitle("Добавить врача");
        add.setResizable(false);
        add.setScene(scene);
        add.show();
    }

    public static void removeDoctor() throws IOException {
        Stage remove = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("removeDoctor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        remove.setTitle("Удалить врача");
        remove.setScene(scene);
        remove.show();
    }

    public static void searchDoctor() throws IOException {
        Stage searchDoctors = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("searchDoctor.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        searchDoctors.setTitle("Найти врача");
        searchDoctors.setScene(scene);
        searchDoctors.show();
    }

    public static void report() throws IOException {
        Stage search = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("report.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        search.setTitle("Отчёт");
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

    public static void addHospital() throws IOException {
        Stage add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("addHospital.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        add.setTitle("Добавить врача в больницу");
        add.setResizable(false);
        add.setScene(scene);
        add.show();
    }

    public static void removeHospital() throws IOException {
        Stage add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("removeHospital.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        add.setTitle("Удалить врача из больницы");
        add.setResizable(false);
        add.setScene(scene);
        add.show();
    }

    public static void searchHospital() throws IOException {
        Stage add = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("searchHospital.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        add.setTitle("Поиск врачей по больнице");
        add.setResizable(false);
        add.setScene(scene);
        add.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
