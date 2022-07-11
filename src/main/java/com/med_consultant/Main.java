package com.med_consultant;
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

    public static void main(String args, String input) throws IOException {
        _init_();
        switch (args) {
            case "add" -> add(input);
            case "remove" -> remove(input);
            case "search" -> search(input);
            case "print" -> print();
            default -> System.out.println("Unknown args");
        }

        /*Scanner scan = new Scanner(System.in);
        System.out.println("Введите ключи поиска(номер больницы и специальность)");
        int numHospital = scan.nextInt();
        String speciality = scan.nextLine();

        ArrayList<Hospitals> result = search(numHospital, speciality);

        for (Hospitals hospital: result) {
            System.out.println(hospital.surname + " " + hospital.name + " " + hospital.patronymic + " стаж: "
                    + doctorArr.get(hospital.numLine - 2).experience + " лет" + " кабинет №" + hospital.numCabinet);
        }*/
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
    private static void readAll() throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileDoctors = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\doctors.txt";
        String fileHospitals = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\hospitals.txt";
        Path doctors = Paths.get(fileDoctors);
        Path hospitals = Paths.get(fileHospitals);
        Scanner scanDoctors = new Scanner(doctors, encoding);
        Scanner scanHospitals = new Scanner(hospitals, encoding);

        int len = scanHospitals.nextInt();
        scanDoctors.nextInt();

        for(int i = 0; i < len; i++){
            Doctors doc = new Doctors();
            Hospitals hospital = new Hospitals();

            hospital.setNumLine(i + 2);
            hospital.setNumHospital(scanHospitals.nextInt());
            hospital.setSurname(scanHospitals.next());
            doc.setSurname(scanDoctors.next());
            hospital.setName(scanHospitals.next());
            doc.setName(scanDoctors.next());
            hospital.setPatronymic(scanHospitals.next());
            doc.setPatronymic(scanDoctors.next());
            hospital.setNumCabinet(scanHospitals.nextInt());
            if(hospital.getNumLine() < 401)scanHospitals.nextLine();
            doc.setExperience(scanDoctors.nextInt());
            doc.setSpeciality(scanDoctors.nextLine());

            hospitalArr.add(hospital);
            doctorArr.add(doc);
        }
    }
    private static void buildHashTable() {
        //HashSet<String> copy;
        for (Doctors doctor : doctorArr) {
            String fio = doctor.getSurname() + doctor.getName() + doctor.getPatronymic();
            int hash = hashTable.getConvolutionHash(fio);
            hashTable.add(hash, doctor);
        }
    }
    private static void buildTree() {
        for(Hospitals hospital: hospitalArr){
            tree.insert(hospital.getNumHospital(), hospital);
        }
    }
    private static void add(String input) {
        Scanner scanDoctors = new Scanner(input);
        Scanner scanHospitals = new Scanner(input);
        Doctors doc = new Doctors();
        Hospitals hospital = new Hospitals();

        //hospital.numLine = i + 2;
        hospital.setNumHospital(scanHospitals.nextInt());
        hospital.setSurname(scanHospitals.next());
        doc.setSurname(hospital.getSurname());
        hospital.setName(scanHospitals.next());
        doc.setName(hospital.getName());
        hospital.setPatronymic(scanHospitals.next());
        doc.setPatronymic(hospital.getPatronymic());
        hospital.setNumCabinet(scanHospitals.nextInt());
        doc.setExperience(scanDoctors.nextInt());
        doc.setSpeciality(scanDoctors.nextLine());

        hospitalArr.add(hospital);
        addTree(hospitalArr.get(hospitalArr.size() - 1));
        doctorArr.add(doc);
        addHashTable(doctorArr.get(doctorArr.size() - 1));
        addToFileHospitals(hospital);
        addToFileDoctors(doc);
    }
    private static void addTree(Hospitals hospital){
        tree.insert(hospital.getNumHospital(), hospital);
    }
    private static void addHashTable(Doctors doctor){
        String fio = doctor.getSurname() + doctor.getName() + doctor.getPatronymic();
        int hash = hashTable.getConvolutionHash(fio);
        hashTable.add(hash, doctor);
    }
    private static void addToFileHospitals(Hospitals hospital){

    }
    private static void addToFileDoctors(Doctors doctor){

    }
    private static void remove(String args) throws IOException {
        Scanner scan = new Scanner(args);
        Doctors doctor = new Doctors();
        Hospitals hospital = new Hospitals();
        hospital.setNumCabinet(scan.nextInt());
        doctor.setSurname(scan.next());
        hospital.setSurname(doctor.getSurname());
        doctor.setName(scan.next());
        hospital.setName(doctor.getName());
        doctor.setPatronymic(scan.next());
        hospital.setPatronymic(doctor.getPatronymic());
        hospital.setNumCabinet(scan.nextInt());
        doctor.setExperience(scan.nextInt());
        doctor.setSpeciality(scan.nextLine());
        removeTree(hospital.getNumHospital());
        removeHashTable(doctor);
        removeFromFileHospitals(hospital);
        removeFromFileDoctors(doctor);
    }
    private static void removeTree(int numHospital){
        tree.deleteNode(numHospital);
    }
    private static void removeHashTable(Doctors doctor){
        String fio = doctor.getSurname() + doctor.getName() + doctor.getPatronymic();
        int hash = hashTable.getConvolutionHash(fio);
        hashTable.del(hash, fio);
    }
    private static void removeFromFileHospitals(Hospitals hospital) throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileHospitals = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\hospitals.txt";
        Path hospitals = Paths.get(fileHospitals);
        Scanner scanHospital = new Scanner(hospitals, encoding);
        //File tempHospital = new File("res\\tempHospitals.txt");
        //Scanner hospitalsWrite = new Scanner(tempHospital, encoding);
        int len = scanHospital.nextInt();
        while(hospital.getNumLine() < len){
            scanHospital.nextLine();
        }
        scanHospital.remove();

    }
    private static void removeFromFileDoctors(Doctors doctor) throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileDoctors = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\doctors.txt";
        Path doctors = Paths.get(fileDoctors);
        Scanner scanDoctor = new Scanner(doctors, encoding);
        //File tempDoctors = new File("res\\tempDoctors.txt");
        //Scanner doctorsWrite = new Scanner(tempDoctors, encoding);
        int len = scanDoctor.nextInt();
        while(doctor.getNumLine() < len){
            scanDoctor.nextLine();
        }
        scanDoctor.remove();
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
            String fio = hospital.getSurname() + hospital.getName() + hospital.getPatronymic();
            int hash = hashTable.getConvolutionHash(fio);
            int id = hashTable.collision(hash, fio);
            boolean flag = true;
            if(id != -1){
                for(int j = id; j < hashTable.getHashTableArr().get(hash).size(); j++){
                    Doctors doc = hashTable.getHashTableArr().get(hash).get(j);
                    if(doc.getSpeciality().equals(speciality)){
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