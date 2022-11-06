package com.AADHA.Starters.DBMSProject.dao;

import com.AADHA.Starters.DBMSProject.model.alumni;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

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

    public List<Map<String,Object>> getMyAlumni(){
        String sql = "select SRN from show_alumni limit 10";
        return this.j.queryForList(sql);
    }

    public alumni getBySRN(int SRN){
        String sql = "select * from alumni where SRN=?";
        try {
            return this.j.queryForObject(sql, MappingRow.rmalumni, SRN);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public void updateShowAlumni(Integer del_SRN, int add_SRN){
        if(del_SRN!=null){
        String sql = "delete from show_alumni where SRN=?";
        this.j.update(sql, del_SRN);}
        String sql = "insert into show_alumni values(?)";
        this.j.update(sql,add_SRN);
    }
}
