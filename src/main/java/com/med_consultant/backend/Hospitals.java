package com.med_consultant.backend;

public class Hospitals {
    private int numLine;
    private int numHospital;
    private String surname;
    private String name;
    private String patronymic;
    private int numCabinet;
    public int getNumLine() {
        return numLine;
    }

    public void setNumLine(int numLine) {
        this.numLine = numLine;
    }

    public int getNumHospital() {
        return numHospital;
    }

    public void setNumHospital(int numHospital) {
        this.numHospital = numHospital;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getNumCabinet() {
        return numCabinet;
    }

    public void setNumCabinet(int numCabinet) {
        this.numCabinet = numCabinet;
    }
}
