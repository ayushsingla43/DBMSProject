package com.AADHA.Starters.DBMSProject.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class workindao {
    @Autowired
    private final JdbcTemplate jdbc;

    public workindao(JdbcTemplate j){
        this.jdbc=j;
    }
    public List<Map<String,Object>>curr(String dept){
        String query="select emp_id,name,phone_1,joining_date,leaving_date,UID from staff as stf natural join works_in as wn where wn.leaving_date='' and wn.dept_name='"+dept+"'";
        List<Map<String,Object>>res=jdbc.queryForList(query);
        return res;
    }
    public List<Map<String,Object>>allemp(String dept){
        String query="select emp_id,name,phone_1,joining_date,leaving_date,UID from staff as stf natural join works_in as wn where wn.dept_name='"+dept+"'";
        List<Map<String,Object>>res=jdbc.queryForList(query);
        return res;
    }

    public List<Map<String,Object>> alldept(Integer emp_id){
        String query="select dept_name from works_in where emp_id=?";
        List<Map<String,Object>>res=jdbc.queryForList(query,emp_id);
        return res;
    }

    public void removestaff(String emp_id,String dept_name){
        String date=String.valueOf(java.time.LocalDate.now());
        jdbc.update("update works_in set leaving_date='"+date+"' where dept_name='"+dept_name+"' and emp_id="+emp_id);
    }
}
