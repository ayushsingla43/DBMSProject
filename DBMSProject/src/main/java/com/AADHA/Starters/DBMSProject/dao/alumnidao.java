package com.AADHA.Starters.DBMSProject.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class alumnidao {
    JdbcTemplate j;

    public alumnidao(JdbcTemplate j) {
        this.j = j;
    }
    
    public void addAlumini(int SRN, int last_class, int leave_year, String name, String admission_date, String email, String phone_1, String phone_2, String photo, String gender, String aadhar){
        String sql = "insert into alumni(SRN, last_class, leave_year, name, admission_date, email, phone_1"+",phone_2,photo,gender,Aadhar_no) values(?,?,?,?,?,?,?,?,?,?,?)";
        this.j.update(sql, SRN, last_class, leave_year, name, admission_date, email, phone_1, phone_2, photo, gender, aadhar);
    }
}
