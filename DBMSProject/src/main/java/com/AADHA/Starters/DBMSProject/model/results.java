package com.AADHA.Starters.DBMSProject.model;

public class results {
    private int SRN,class_no,section_no,session_no,marks;
    private String dept_name;

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public int getClass_no() {
        return class_no;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public int getSection_no() {
        return section_no;
    }

    public void setSection_no(int section_no) {
        this.section_no = section_no;
    }

    public int getSession_no() {
        return session_no;
    }

    public void setSession_no(int session_no) {
        this.session_no = session_no;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
