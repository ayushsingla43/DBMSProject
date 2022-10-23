package com.AADHA.Starters.DBMSProject;

public class Student {

    private String St_Name;
    private int SRN;
    private int St_age;

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public String getSt_Name() {
        return St_Name;
    }

    public void setSt_Name(String st_Name) {
        St_Name = st_Name;
    }

    public int getSt_age() {
        return St_age;
    }

    public void setSt_age(int st_age) {
        St_age = st_age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "St_Name='" + St_Name + '\'' +
                ", SRN=" + SRN +
                ", St_age=" + St_age +
                '}';
    }
}
