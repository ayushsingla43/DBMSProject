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

    public String currsession(){
        Map<String,Object>res =jdbc.queryForMap("select max(session_no) as ssn from results");
        return String.valueOf(res.get("ssn"));
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
        String query="select stu.SRN as SRN,stu.name as name,pc.class_no as class,pc.section_no as section,rst.course as course,rst.marks as marks,crs.session_no as session_no from courses as crs natural join prev_class as pc natural join results as rst, student as stu where crs.emp_id="+emp_id+" and crs.session_no="+session+" and crs.dept_name='"+ course +"' and pc.SRN=stu.SRN and rst.course=crs.dept_name";
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
        System.out.println(query);
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

    public List<Map<String,Object>> emptyresult(){
        List<Map<String,Object>> res=jdbc.queryForList("select std.SRN as SRN,std.name as name,std.class_no as class,std.section_no as section,rst.marks as marks,rst.session_no as session_no,rst.course as course from results as rst , student as std where std.SRN=rst.SRN and marks is null and session_no="+currsession()+" limit 50");
        return res;
    }

    public void addprev_class(String SRN,String session_no,String class_no,String section_no){
        jdbc.update("insert into prev_class values(?,?,?,?)",SRN,session_no,class_no,section_no);

    }

    public void addresult(String SRN,String Session_no,String course){
        jdbc.update("insert into results(SRN,session_no,course) values (?,?,?)", SRN,Session_no,course);
    }

    public boolean pass_fail(String SRN,String session_no){
        List<Map<String,Object>> res=jdbc.queryForList("select * from results where SRN="+SRN+" and session_no="+session_no);
        for(Map<String,Object> x: res){
            if(Integer.valueOf(String.valueOf(x.get("marks")))<20){
                return false;
            }
        }
        return true;
    }
}
