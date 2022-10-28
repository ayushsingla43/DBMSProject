package com.AADHA.Starters.DBMSProject.model;

public class courses {
    private int class_no,section_no,emp_id;
    private String dept_name;

    public int getClass_no() {
        return class_no;
    }

    public void setClass_no(int class_no) {
        this.class_no = class_no;
    }

    public int getSection_no(int section_no) {
        return this.section_no;
    }

    public void setSection_no(int section_no) {
        this.section_no = section_no;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getDept_name() {
        return dept_name;
    }

    public void setDept_name(String dept_name) {
        this.dept_name = dept_name;
    }
}
