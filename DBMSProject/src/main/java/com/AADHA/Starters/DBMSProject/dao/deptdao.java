package com.AADHA.Starters.DBMSProject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

// import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.AADHA.Starters.DBMSProject.model.department;
import com.AADHA.Starters.DBMSProject.util.MappingRow;

@Repository
public class deptdao {
    @Autowired
    private JdbcTemplate jdbc;

    public deptdao(JdbcTemplate j){
        this.jdbc=j;
    }

    public department findbyDeptname(String deptname){
        return jdbc.queryForObject("select * from department where dept_name='"+deptname+"'",MappingRow.rmdepartment);
    }

    public List<String> getAllDepts(){
        String query = "select dept_name from department";
        List<Map<String,Object>> res = jdbc.queryForList(query);
        List<String> depts = new ArrayList<String>();
        for(Map<String, Object> x:res){
            for (Map.Entry<String,Object> y : x.entrySet()) depts.add(String.valueOf( y.getValue()));
        }
        return depts;
    }
}
