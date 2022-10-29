package com.AADHA.Starters.DBMSProject.model;

public class results {
    private int SRN,session_no,marks;
    private String course;

    public int getSRN() {
        return SRN;
    }

    public void setSRN(int SRN) {
        this.SRN = SRN;
    }

    public int getSession_no() {
        return this.session_no;
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

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}
