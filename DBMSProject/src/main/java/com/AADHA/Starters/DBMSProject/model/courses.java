package com.AADHA.Starters.DBMSProject.model;

public class courses {
    private int class_no,section_no;
    private String emp_id;

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

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
}
