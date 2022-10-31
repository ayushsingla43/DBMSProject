package com.AADHA.Starters.DBMSProject.model;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("session")
public class student {
    private int SRN,PIN,class_no,section_no;
    private String name,DOB,admission_date,email,blood_grp,guardian,mother,phone_1,phone_2;
    private String street,city,state,Aadhar_no,photo,UID,password,gender;

    @Override
    public String toString() {
        return "student{" +
                "SRN=" + SRN +
                ", PIN=" + PIN +
                ", class_no=" + class_no +
                ", section_no=" + section_no +
                ", name='" + name + '\'' +
                ", DOB='" + DOB + '\'' +
                ", admission_date='" + admission_date + '\'' +
                ", email='" + email + '\'' +
                ", blood_grp='" + blood_grp + '\'' +
                ", guardian='" + guardian + '\'' +
                ", mother='" + mother + '\'' +
                ", phone_1='" + phone_1 + '\'' +
                ", phone_2='" + phone_2 + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", Aadhar_no='" + Aadhar_no + '\'' +
                ", photo='" + photo + '\'' +
                ", UID='" + UID + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public int getPIN() {
        return PIN;
    }

    public void setPIN(int PIN) {
        this.PIN = PIN;
    }

    public int getClass_no() {
        return class_no;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public int getSection_no(   ) {
        return this.section_no;
    }

    public void setSection_no(int section_no) {
        this.section_no = section_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
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

    public String getBlood_grp() {
        return blood_grp;
    }

    public void setBlood_grp(String blood_grp) {
        this.blood_grp = blood_grp;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
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

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAadhar_no() {
        return Aadhar_no;
    }

    public void setAadhar_no(String aadhar_no) {
        Aadhar_no = aadhar_no;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUID() {
        return UID;
    }

    public void setUID(String UID) {
        this.UID = UID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
