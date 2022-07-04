package com.example.med_consultant;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    //public static ArrayList<Doctors> arr;
    public static ArrayList<Hospitals> hospitalArr;
    public static ArrayList<Doctors> doctorArr;
    public static HashTable hashTable;
    public static RedBlackTree tree;

    public static void main(String[] args) throws IOException {
        _init_();
        Application.launch();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключи поиска(номер больницы и специальность)");
        int numHospital = scan.nextInt();
        String speciality = scan.nextLine();

        ArrayList<Hospitals> result = search(numHospital, speciality);

        for (Hospitals hospital: result) {
            System.out.println(hospital.surname + " " + hospital.name + " " + hospital.patronymic + " номер кабинета: " + hospital.numCabinet);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Медконсультант");
        stage.setHeight(600);
        stage.setWidth(700);
        stage.setResizable(false);
        Label label = new Label("Hello Медконсультант");
        label.setAlignment(Pos.CENTER);
        Scene scene = new Scene(label);
        stage.setScene(scene);
        Image image = new Image("C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\example\\med_consultant\\res\\icon.png");
        stage.getIcons().add(image);
        stage.show();
    }
    public static void _init_() throws IOException {
        hospitalArr = new ArrayList<>();
        doctorArr = new ArrayList<>();
        hashTable = new HashTable();
        tree = new RedBlackTree();

        readAll();
        buildTree();
        buildHashTable();
    }
    static void readAll() throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileDoctors = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\example\\med_consultant\\res\\doctors.txt";
        String fileHospitals = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\example\\med_consultant\\res\\hospitals.txt";
        Path doctors = Paths.get(fileDoctors);
        Path hospitals = Paths.get(fileHospitals);
        Scanner scanDoctors = new Scanner(doctors, encoding);
        Scanner scanHospitals = new Scanner(hospitals, encoding);

        int len = scanHospitals.nextInt();
        scanDoctors.nextInt();

        for(int i = 0; i < len; i++){
            Doctors doc = new Doctors();
            Hospitals hospital = new Hospitals();

            hospital.numLine = i + 2;
            hospital.numHospital = scanHospitals.nextInt();
            hospital.surname = scanHospitals.next();
            doc.surname = scanDoctors.next();
            hospital.name = scanHospitals.next();
            doc.name = scanDoctors.next();
            hospital.patronymic = scanHospitals.next();
            doc.patronymic = scanDoctors.next();
            hospital.numCabinet = scanHospitals.nextInt();
            if(hospital.numLine < 401)scanHospitals.nextLine();
            doc.experience = scanDoctors.nextInt();
            doc.speciality = scanDoctors.nextLine();

            hospitalArr.add(hospital);
            doctorArr.add(doc);
        }
    }
    static void buildHashTable() {
        //HashSet<String> copy;
        for (Doctors doctor : doctorArr) {
            String fio = doctor.surname + doctor.name + doctor.patronymic;
            int hash = hashTable.getConvolutionHash(fio);
            hashTable.add(hash, doctor);
        }
    }
    static void buildTree() {
        for(Hospitals hospital: hospitalArr){
            tree.insert(hospital.numHospital, hospital);
        }
    }
    static ArrayList<Hospitals> search(int numHospital, String speciality){
        ArrayList<Hospitals> res = tree.searchTree(numHospital).arr;
        for(int i = 0; i < res.size(); i++){
            Hospitals hospital = res.get(i);
            String fio = hospital.surname + hospital.name + hospital.patronymic;
            int hash = hashTable.getConvolutionHash(fio);
            int id = hashTable.collision(hash, fio);
            boolean flag = true;
            if(id != -1){
                for(int j = id; j < hashTable.getHashTableArr().get(hash).size(); j++){
                    Doctors doc = hashTable.getHashTableArr().get(hash).get(j);
                    if(doc.speciality.equals(speciality)){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    res.remove(i);
                    i--;
                }
            }else{
                res.remove(i);
                i--;
            }
        }
        return res;
    }
}
