package com.AADHA.Starters.DBMSProject.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class classdao {
    private final JdbcTemplate jdbc;

    public classdao(JdbcTemplate j) {
        this.jdbc = j;
    }

    public List<String> Classes(){
        String query = "select distinct class_no from class";
        List<Map<String,Object>> res = jdbc.queryForList(query);
        List<String> class_no = new ArrayList<String>();
        for(Map<String, Object> x:res){
            for (Map.Entry<String,Object> y : x.entrySet()) class_no.add(String.valueOf( y.getValue()));
        }
        return class_no;
    }

    public List<String> Sections(){
        String query = "select distinct section_no from class";
        List<Map<String,Object>> res = jdbc.queryForList(query);
        List<String> section_no = new ArrayList<String>();
        for(Map<String, Object> x:res){
            for (Map.Entry<String,Object> y : x.entrySet()) section_no.add(String.valueOf( y.getValue()));
        }
        return section_no;
    }
}
