package com.AADHA.Starters.DBMSProject.dao;

import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class staffdao {
    @Autowired
    private final JdbcTemplate jdbc;

    public staffdao(JdbcTemplate j){
        this.jdbc=j;
    }

    public List<Map<String,Object>> listquery(String emp_id, String name, String session, String dept, String class_, String section, String limit){
        String query="select distinct emp_id,name,phone_1,email,salary from staff";
        Boolean wr=false;

        if (dept!="") query+=" natural join work_in";
        if(session!="" || class_!="" || section!="") query+=" natural join courses";

        if (emp_id!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" emp_id="+emp_id;
        }
        if (name!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" name="+name;
        }
        if (session!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" session_no="+session;
        }
        if (dept!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" dept_name="+dept;
        }
        if (class_!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" class_no="+class_;
        }
        if (section!=""){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" section_no="+section;
        }
        query+=" limit "+limit;
        List<Map<String,Object>> res=jdbc.queryForList(query);
        return res;
    }

    public staff info(int emp_id){
        String query = "select * from staff where emp_id=?";
        return jdbc.queryForObject(query, MappingRow.rmstaff,emp_id);
    }

    public staff getStaffByUID(String uid){
        String sql = "select * from `staff` where UID=?";
        try{
            return jdbc.queryForObject(sql, MappingRow.rmstaff, uid);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }
}
