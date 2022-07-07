package com.example.med_consultant;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<Hospitals> hospitalArr;
    public static ArrayList<Doctors> doctorArr;
    public static HashTable hashTable;
    public static RedBlackTree tree;

    public static void main(String[] args) throws IOException {
        _init_();
        /*switch (args) {
            case "add" -> add(args);
            case "remove" -> remove(args);
            case "search" -> search(args);
            case "print" -> print();
            default -> System.out.println("Unknown args");
        }*/
        //Application.launch();
        Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключи поиска(номер больницы и специальность)");
        int numHospital = scan.nextInt();
        String speciality = scan.nextLine();

        ArrayList<Hospitals> result = search(numHospital, speciality);

        for (Hospitals hospital: result) {
            System.out.println(hospital.surname + " " + hospital.name + " " + hospital.patronymic + " стаж: "
                    + doctorArr.get(hospital.numLine - 2).experience + " лет" + " кабинет №" + hospital.numCabinet);
        }
        /*System.out.println("Добавление(введите через пробел: номер больницы, ФИО, номер кабинета, стаж, специальность):");
        String input = scan.nextLine();
        add(input);

        System.out.println("Удаление(введите через пробел: номер больницы, ФИО, номер кабинета, стаж, специальность):");
        input = scan.nextLine();
        remove(input);*/
    }

    private static void _init_() throws IOException {
        hospitalArr = new ArrayList<>();
        doctorArr = new ArrayList<>();
        hashTable = new HashTable();
        tree = new RedBlackTree();

        readAll();
        buildTree();
        buildHashTable();
    }
    private static void readAll() throws IOException {
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
    private static void buildHashTable() {
        //HashSet<String> copy;
        for (Doctors doctor : doctorArr) {
            String fio = doctor.surname + doctor.name + doctor.patronymic;
            int hash = hashTable.getConvolutionHash(fio);
            hashTable.add(hash, doctor);
        }
    }
    private static void buildTree() {
        for(Hospitals hospital: hospitalArr){
            tree.insert(hospital.numHospital, hospital);
        }
    }
    private static void add(String input) throws IOException {
        Scanner scanDoctors = new Scanner(input);
        Scanner scanHospitals = new Scanner(input);
        Doctors doc = new Doctors();
        Hospitals hospital = new Hospitals();

        //hospital.numLine = i + 2;
        hospital.numHospital = scanHospitals.nextInt();
        doc.surname = hospital.surname = scanHospitals.next();
        doc.name = hospital.name = scanHospitals.next();
        doc.patronymic = hospital.patronymic = scanHospitals.next();
        hospital.numCabinet = scanHospitals.nextInt();
        doc.experience = scanDoctors.nextInt();
        doc.speciality = scanDoctors.nextLine();

        hospitalArr.add(hospital);
        addTree(hospitalArr.get(hospitalArr.size() - 1));
        doctorArr.add(doc);
        addHashTable(doctorArr.get(doctorArr.size() - 1));
        addToFileHospitals(hospital);
        addToFileDoctors(doc);
    }
    private static void addTree(Hospitals hospital){
        tree.insert(hospital.numHospital, hospital);
    }
    private static void addHashTable(Doctors doctor){
        String fio = doctor.surname + doctor.name + doctor.patronymic;
        int hash = hashTable.getConvolutionHash(fio);
        hashTable.add(hash, doctor);
    }
    private static void addToFileHospitals(Hospitals hospital){

    }
    private static void addToFileDoctors(Doctors doctor){

    }
    private static void remove(String args){
        Scanner scan = new Scanner(args);
        Doctors doctor = new Doctors();
        Hospitals hospital = new Hospitals();
        hospital.numCabinet = scan.nextInt();
        hospital.surname = doctor.surname = scan.next();
        hospital.name = doctor.name = scan.next();
        hospital.patronymic = doctor.patronymic = scan.next();
        hospital.numCabinet = scan.nextInt();
        doctor.experience = scan.nextInt();
        doctor.speciality = scan.nextLine();
        removeTree(hospital.numHospital);
        removeHashTable(doctor);
        removeFromFileHospitals(hospital);
        removeFromFileDoctors(doctor);
    }
    private static void removeTree(int numHospital){
        tree.deleteNode(numHospital);
    }
    private static void removeHashTable(Doctors doctor){
        String fio = doctor.surname + doctor.name + doctor.patronymic;
        int hash = hashTable.getConvolutionHash(fio);
        hashTable.del(hash, fio);
    }
    private static void removeFromFileHospitals(Hospitals hospital){

    }
    private static void removeFromFileDoctors(Doctors doctor){

    }
    private static ArrayList<Hospitals> search(String args){
        int numHospital;
        String speciality;
        Scanner scan = new Scanner(args);
        numHospital = scan.nextInt();
        scan.next();
        scan.next();
        scan.next();
        speciality = scan.nextLine();
        return search(numHospital, speciality);
    }
    private static ArrayList<Hospitals> search(int numHospital, String speciality){
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
    private static void print(){

    }
}