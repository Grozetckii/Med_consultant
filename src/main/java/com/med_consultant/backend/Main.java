package com.med_consultant.backend;

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

    public static void _init_() throws IOException {
        hospitalArr = new ArrayList<>();
        doctorArr = new ArrayList<>();
        hashTable = new HashTable();
        tree = new RedBlackTree();

        readDoctors();
        readHospitals();
        buildTree();
        buildHashTable();
    }

    private static void readDoctors() throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileDoctors = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\doctors.txt";
        Path doctors = Paths.get(fileDoctors);
        Scanner scanDoctors = new Scanner(doctors, encoding);
        int len = scanDoctors.nextInt();

        for(int i = 0; i < len; i++){
            Doctors doc = new Doctors();
            doc.setSurname(scanDoctors.next());
            doc.setName(scanDoctors.next());
            doc.setPatronymic(scanDoctors.next());
            doc.setExperience(scanDoctors.nextInt());
            doc.setSpeciality(scanDoctors.next());
            scanDoctors.nextLine();
            doctorArr.add(doc);
        }
    }
    private static void readHospitals() throws IOException {
        String encoding = System.getProperty("console.encoding", "utf-8");
        String fileHospitals = "C:\\Users\\Urapochka\\IdeaProjects\\Med_consultant\\src\\main\\java\\com\\med_consultant\\res\\hospitals.txt";
        Path hospitals = Paths.get(fileHospitals);
        Scanner scanHospitals = new Scanner(hospitals, encoding);
        int len = scanHospitals.nextInt();

        for(int i = 1; i < len;){
            Hospitals hospital = new Hospitals();
            hospital.setNumLine(++i);
            hospital.setNumHospital(scanHospitals.nextInt());
            hospital.setSurname(scanHospitals.next());
            hospital.setName(scanHospitals.next());
            hospital.setPatronymic(scanHospitals.next());
            hospital.setNumCabinet(scanHospitals.nextInt());
            if(i < len)scanHospitals.nextLine();
            hospitalArr.add(hospital);
        }
    }
    private static void buildHashTable() {
        for (Doctors doctor : doctorArr) {
            addHashTable(doctor);
        }
    }

    private static void buildTree() {
        for(Hospitals hospital: hospitalArr){
            addTree(hospital);
        }
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
    public static void remove(String args) throws IOException {
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
    private static int removeHashTable(Doctors doctor){
        String fio = doctor.getSurname() + doctor.getName() + doctor.getPatronymic();
        int hash = hashTable.getConvolutionHash(fio);
        return hashTable.del(hash, fio);
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
    /*private static ArrayList<Hospitals> search(String args){
        int numHospital;
        String speciality;
        Scanner scan = new Scanner(args);
        numHospital = scan.nextInt();
        scan.next();
        scan.next();
        scan.next();
        speciality = scan.nextLine();
        return search(numHospital, speciality);
    }*/
    public static ArrayList<Hospitals> search(int numHospital, String speciality){
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

    public static int addDoctor(String input){
        Scanner scanDoctors = new Scanner(input);
        Doctors doc = new Doctors();
        doc.setSurname(scanDoctors.next());
        doc.setName(scanDoctors.next());
        doc.setPatronymic(scanDoctors.next());
        doc.setExperience(scanDoctors.nextInt());
        doc.setSpeciality(scanDoctors.next());
        String fio = doc.getSurname() + doc.getName() + doc.getPatronymic();
        int hash = hashTable.getConvolutionHash(fio);
        if(hashTable.collision(hash, fio) == -1){
            doctorArr.add(doc);
            addHashTable(doctorArr.get(doctorArr.size() - 1));
        }else{
            return -1;
        }
        return 0;
    }

    public static int removeDoctor(String input){
        Scanner scanDoctors = new Scanner(input);
        Doctors doc = new Doctors();
        doc.setSurname(scanDoctors.next());
        doc.setName(scanDoctors.next());
        doc.setPatronymic(scanDoctors.next());
        String fio = doc.getSurname() + doc.getName() + doc.getPatronymic();
        tree.LNR_Remove(fio);
        for(int i = 0; i < hospitalArr.size(); i++){
            if((hospitalArr.get(i).getSurname() + hospitalArr.get(i).getName() +
                    hospitalArr.get(i).getPatronymic()).equals(fio)){
                hospitalArr.remove(i);
                i--;
            }
        }

        for(int i = 0; i < doctorArr.size(); i++){
            if((doctorArr.get(i).getSurname() + doctorArr.get(i).getName() +
                    doctorArr.get(i).getPatronymic()).equals(fio)){
                doctorArr.remove(i);
                break;
            }
        }
        if(removeHashTable(doc) == -1){
            return -1;
        }
        return 0;
    }

    public static ArrayList<Doctors> searchDoctor(String speciality){
        ArrayList<Doctors> res = new ArrayList<>();
        for(var x : doctorArr){
            if(x.getSpeciality().equals(speciality)){
                res.add(x);
            }
        }
        return res;
    }

    public static int addHospital(String input){
        Scanner scanHospitals = new Scanner(input);
        Hospitals hospital = new Hospitals();
        hospital.setNumHospital(scanHospitals.nextInt());
        hospital.setSurname(scanHospitals.next());
        hospital.setName(scanHospitals.next());
        hospital.setPatronymic(scanHospitals.next());
        hospital.setNumCabinet(scanHospitals.nextInt());
        String fio = hospital.getSurname() + hospital.getName() + hospital.getPatronymic();
        ArrayList<Hospitals> temp = tree.searchTree(hospital.getNumHospital()).arr;
        for(var x: temp){
            if((x.getSurname() + x.getName() + x.getPatronymic()).equals(fio)){
                return -1;
            }
        }
        int hash = hashTable.getConvolutionHash(fio);
        if(hashTable.collision(hash, fio) == -1){
            return -2;
        }else{
            tree.insert(hospital.getNumHospital(), hospital);
            hospitalArr.add(hospital);
        }
        return 0;
    }

    public static int removeHospital(String input){
        Scanner scanHospitals = new Scanner(input);
        Hospitals hospital = new Hospitals();
        hospital.setSurname(scanHospitals.next());
        hospital.setName(scanHospitals.next());
        hospital.setPatronymic(scanHospitals.next());
        hospital.setNumHospital(scanHospitals.nextInt());
        String fio = hospital.getSurname() + hospital.getName() + hospital.getPatronymic();
        ArrayList<Hospitals> temp = tree.searchTree(hospital.getNumHospital()).arr;
        if(temp == null){
            return -2;
        }else{
            int i = 0;
            for(var x: temp){
                if((x.getSurname() + x.getName() + x.getPatronymic()).equals(fio)){
                    tree.searchTree(hospital.getNumHospital()).arr.remove(i);
                    for(int j = 0; j < hospitalArr.size(); j++){
                        if((hospitalArr.get(j).getSurname() + hospitalArr.get(j).getName() +
                                hospitalArr.get(j).getPatronymic()).equals(fio)){
                            hospitalArr.remove(j);
                            break;
                        }
                    }
                    return 0;
                }
                i++;
            }
            return -1;
        }
    }
    public static ArrayList<Hospitals> searchHospital(int numHospital){
        return tree.searchTree(numHospital).arr;
    }
}