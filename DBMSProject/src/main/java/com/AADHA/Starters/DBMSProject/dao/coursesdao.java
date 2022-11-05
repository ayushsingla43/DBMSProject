package com.AADHA.Starters.DBMSProject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class coursesdao {
    @Autowired
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

    public List<String> sessions(){
        String query="select distinct session_no from courses";
        List<Map<String,Object>>qqr=jdbc.queryForList(query);
        List<String> res= new ArrayList<String>();
        for(Map<String,Object> x : qqr){
            res.add(String.valueOf(x.get("session_no")));
        }
        return res;
    }

    public List<String> Courses(){
        String query = "select distinct dept_name from courses";
        List<Map<String,Object>> qr=jdbc.queryForList(query);
        List<String> res=new ArrayList<String>();
        for(Map<String,Object> x: qr){
            res.add(String.valueOf(x.get("dept_name")));
        }
        return res;
    }

    public String currentsession(){
        Map<String,Object> ans=jdbc.queryForMap("select max(session_no) as ssn from courses");
        String res=String.valueOf(ans.get("ssn"));
        return res;
    }

    public List<Map<String,Object>> assignquery(String emp_id,String class_,String section,String dept,String limit){
        String query="select stf.emp_id as emp_id,stf.name as name, crs.class_no as class_no,crs.section_no as section_no, crs.dept_name as dept_name from courses as crs natural join staff as stf";

        query+=" where crs.session_no="+currentsession();

        if(!emp_id.equals("")){
                query+=" and";
            query+=" stf.emp_id="+emp_id;
        }
        if(!class_.equals("")){
                query+=" and";
            query+=" crs.class_no="+class_;
        }
        if(!section.equals("")){
                query+=" and";
            query+=" crs.section_no="+section;
        }
        if(!emp_id.equals("")){
                query+=" and";
            query+=" crs.dept_name='"+dept+"'";
        }
        query+=" order by class_no,section_no limit "+limit;
        List<Map<String,Object>> res = jdbc.queryForList(query); 
        return res;
    }

    public void updateassign(String class_no,String Section_no,String dept_name,String new_emp){
        String query="update courses set emp_id="+new_emp+" where class_no="+class_no+" and section_no="+Section_no+" and dept_name='"+dept_name+"' and session_no="+currentsession();
        jdbc.execute(query);
    }
}
