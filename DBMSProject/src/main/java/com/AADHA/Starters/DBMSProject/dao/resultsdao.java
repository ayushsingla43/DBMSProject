package com.AADHA.Starters.DBMSProject.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class resultsdao {
    @Autowired
    private final JdbcTemplate jdbc;

    public resultsdao(JdbcTemplate j){
        this.jdbc=j;
    }

    public List<Integer> allSession(int SRN){
        String sql = "select session_no from prev_class where SRN=?";
        List<Map<String,Object>> res = jdbc.queryForList(sql,SRN);
        List<Integer> res2=new ArrayList<Integer>();
        for(Map<String,Object> mp:res) res2.add((Integer)mp.get("session_no"));
        return res2;
    }

    public Map<String,Object> getClass(int SRN,int session){
        String sql = "select class_no,section_no from prev_class where SRN=? and session_no=?";
        List<Map<String,Object>> res = jdbc.queryForList(sql,SRN,session);
        Map<String,Object> res2 = new HashMap<String, Object>();
        for(Map<String,Object> mp:res) res2=mp;
        return res2;
    }

    public List<Map<String,Object>> getMarks(int SRN,int session){
        String sql = "select course,marks from results where SRN=? and session_no=?";
        List<Map<String,Object>> res = jdbc.queryForList(sql,SRN,session);
        return res;
    }
}
