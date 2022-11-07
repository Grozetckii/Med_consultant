package com.med_consultant.backend;


import java.util.ArrayList;
import java.util.Objects;

public class HashTable {
    private final ArrayList<ArrayList<Doctors>> hashTableArr;
    //private HashSet<String> setKey;
    private final int h;
    
    
    public HashTable()
    {
        h = 1000;
        hashTableArr = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            hashTableArr.add(new ArrayList<>());
        }
    }

    public int getH() {
        return h;
    }

    public ArrayList<ArrayList<Doctors>> getHashTableArr() {
        return hashTableArr;
    }

    public int getConvolutionHash(String key)
    {
        StringBuilder mas = new StringBuilder();
        for (int i = 0; i < key.length(); i++) {	//преобразуем строку в число
            int temp = key.charAt(i);
            mas.append(temp);
        }
        int hash = 0;
        int count = mas.length();
        int cur;

        for (int i = 0; i < count; i += 3) {
            if (i + 3 < mas.length()) {
                cur = 0;
                for (int j = i; j < i + 2; j++) {
                    cur += mas.toString().charAt(j) - '0';
                    cur *= 10;
                }
                cur += mas.toString().charAt(i + 2) - '0';
                hash += cur;
            }
            else {
                int temp = 0;
                while (i < mas.length() - 1) {
                    temp += mas.toString().charAt(i) - '0';
                    temp *= 10;
                    i++;
                }
                temp += mas.toString().charAt(i) - '0';
                hash += temp;
                break;
            }
        }
        hash %= h;
        return hash;
    }

    public void add(int hash, Doctors doctor)
    {
        /*if(hash > h) {
            hashTableArr = addSize();
        }*/
        hashTableArr.get(hash).add(doctor);
    }
    /*private ArrayList<ArrayList<Doctors>> addSize(){
        h *= 1.5;
        ArrayList<ArrayList<Doctors>> temp = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            temp.add(new ArrayList<>());
            for (int j = 0; j < hashTableArr.get(i).size(); j++) {
                String fio = hashTableArr.get(i).get(j).surname + hashTableArr.get(i).get(j).name
                        + hashTableArr.get(i).get(j).patronymic;
                int hash = getConvolutionHash(fio);
                temp.get(hash).add(hashTableArr.get(i).get(j));
            }

        }
        return temp;
    }*/

    public int collision(int hash, String key)
    {
        for (int i = 0; i < hashTableArr.get(hash).size(); i++) {
            Doctors doc = hashTableArr.get(hash).get(i);
            if (Objects.equals(doc.getSurname() + doc.getName() + doc.getPatronymic(),key)) {
                return i;
            }
        }
        return -1;
    }

    public int del(int hash, String key)
    {
        if (hashTableArr.get(hash).size() > 0) {
            int id = collision(hash, key);
            if (id == -1) {
                return -1;
            }
            else {
                hashTableArr.get(hash).remove(id);
                return 0;
            }
        }
        return -1;
    }

    public Doctors getStruct(int hash, String key)
    {
        int id = collision(hash, key);
        if (id == -1) {
            Doctors doctor = new Doctors();
            doctor.setNumLine(-1);
            return doctor;
        }
        else {
            return hashTableArr.get(hash).get(id);
        }

    }

    public void print()
    {
        System.out.println("  -------------------------------------------------------------------------------------------------");
        System.out.println(" |    hash    |                                  value                                ");
        System.out.println("  -------------------------------------------------------------------------------------------------");
        for (int i = 0; i < h; i++) {
            System.out.println(" |     " + i + "      |");
            for (int j = 0; j < hashTableArr.get(i).size(); j++) {
                System.out.print(" |            |");
                printDoctors(hashTableArr.get(i).get(j));
            }
            System.out.println("  -------------------------------------------------------------------------------------------------");
        }

    }

    public void printDoctors(Doctors doctors)
    {
        if (doctors.getNumLine() == -1) {
            System.out.println("Значения с таким ключем не найдено");
        }
        else {
            System.out.println(" Сторка №" + doctors.getNumLine() + " " + doctors.getSpeciality() + " " + doctors.getSurname() + " " + doctors.getName() + " "
                    + doctors.getPatronymic());
        }
    }
}
