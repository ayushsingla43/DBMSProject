package com.AADHA.Starters.DBMSProject.dao;

import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class coursesdao {
    private final JdbcTemplate jdbc;

    public coursesdao(JdbcTemplate j){
        this.jdbc=j;
    }

    String current_session="2022";
    List<Map<String,Object>> classesbyemp(String emp_id){
        String query="select distinct class_no,section_no from courses where emp_id="+emp_id;
        List<Map<String,Object>>res =jdbc.queryForList(query);
        return res;
    }
}
