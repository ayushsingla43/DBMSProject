package com.AADHA.Starters.DBMSProject.model;

public class alumni {
    private int SRN,last_class,leave_year;
    private String name,admission_date,email,phone_1,phone_2,photo,gender,Aadhar_no;

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public String getPhone_1() {
        return phone_1;
    }

    public void setPhone_1(String phone_1) {
        this.phone_1 = phone_1;
    }

    public String getPhone_2() {
        return phone_2;
    }

    public void setPhone_2(String phone_2) {
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAadhar_no() {
        return Aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        Aadhar_no = aadhar_no;
    }
}
