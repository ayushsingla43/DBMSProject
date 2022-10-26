package com.AADHA.Starters.DBMSProject.model;

public class alumni {
    private int SRN,phone_1,phone_2,last_class,leave_year;
    private String name,admission_date,email,photo;

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public int getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(int phone_1) {
        this.phone_1 = phone_1;
    }

    public int getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(int phone_2) {
        this.phone_2 = phone_2;
    }

    public int getLast_class() {
        return last_class;
    }

    public void setLast_class(int last_class) {
        this.last_class = last_class;
    }

    public int getLeave_year() {
        return leave_year;
    }

    public void setLeave_year(int leave_year) {
        this.leave_year = leave_year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdmission_date() {
        return admission_date;
    }

    public void setAdmission_date(String admission_date) {
        this.admission_date = admission_date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
