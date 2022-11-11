package com.med_consultant.backend;


import java.util.ArrayList;
import java.util.Objects;

public class HashTable {
    private final ArrayList<LinkedList> hashTableArr;
    private final int h;
    
    
    public HashTable()
    {
        h = 100;
        hashTableArr = new ArrayList<>();
        for (int i = 0; i < h; i++) {
            hashTableArr.add(new LinkedList());
        }
    }

    public int getH() {
        return h;
    }

    public ArrayList<LinkedList> getHashTableArr() {
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
        hashTableArr.get(hash).addFirst(doctor);
    }


    public int collision(int hash, String key)
    {
        for (int i = 0; i < hashTableArr.get(hash).size(); i++) {
            Doctors doc = hashTableArr.get(hash).getDoctor(i);
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
            return hashTableArr.get(hash).getDoctor(id);
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
                printDoctors(hashTableArr.get(i).getDoctor(j));
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
