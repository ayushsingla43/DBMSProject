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

    public List<Integer> totalSession(){
        String sql = "select distinct session_no from prev_class";
        List<Map<String,Object>> res = jdbc.queryForList(sql);
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
        return jdbc.queryForList(sql,SRN,session);
    }

    public List<String> getAllCourses(){
        String sql="select distinct course from results";
        List<Map<String,Object>> res =  jdbc.queryForList(sql);
        List<String> courses = new ArrayList<String>();
        for(Map<String, Object> x:res){
            for (Map.Entry<String,Object> y : x.entrySet()) courses.add(String.valueOf( y.getValue()));
        }
        return courses;
    }

    public List<Map<String,Object>> getPrevResult(String emp_id,String SRN,String class_,String section,String course, String session,String limit){
        String query="select stu.SRN as SRN,stu.name as name,pc.class_no as class,pc.section_no as section,rst.marks as marks from courses as crs natural join prev_class as pc natural join results as rst, student as stu where crs.emp_id="+emp_id+" and crs.session_no="+session+" and crs.dept_name='"+ course +"' and pc.SRN=stu.SRN and rst.course=crs.dept_name";
        if (!SRN.equals("")){
            query+=" and stu.SRN="+SRN;
        }
        if (!class_.equals("")){
            query+=" and pc.class_no="+class_;
        }
        if (!section.equals("")){
            query+=" and pc.section_no="+section;
        }
        
        String query2=query.replace("student", "alumni");
        query="("+query+" union all "+query2+" )";
        
        query+=" order by class,section limit "+limit;
        List<Map<String,Object>> res=jdbc.queryForList(query);

        return res;
    }

    public void updateResult(String sRN, String Session_no, String Marks, String course){
        int SRN = Integer.parseInt(sRN);
        int session_no = Integer.parseInt(Session_no);
        int marks = Integer.parseInt(Marks);
        String sql = "update results set marks=? where SRN=? and session_no=? and course=?";
        this.jdbc.update(sql, marks, SRN, session_no, course);
    }
}
