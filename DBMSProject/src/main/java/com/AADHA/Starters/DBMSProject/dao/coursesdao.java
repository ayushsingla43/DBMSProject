package com.AADHA.Starters.DBMSProject.dao;

import java.util.ArrayList;
import java.util.HashMap;
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
        String query="select stf.emp_id as emp_id,stf.name as name, crs.class_no as class_no,crs.section_no as section_no, crs.dept_name as dept_name,stf.UID as UID from courses as crs natural join staff as stf";

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
        if(!dept.equals("")){
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

    public boolean checktech(String emp_id,String dept_name){
        String query="select * from courses where session_no="+currentsession()+" and dept_name='"+dept_name+"' and emp_id="+emp_id;
        System.out.println(query);
        List<Map<String,Object>> res=jdbc.queryForList(query);
        return res.size()>0;
    }

    public Map<String,List<String>> allclasscourses(){
        List<Map<String,Object>> res=jdbc.queryForList("select * from courses where session_no="+currentsession());
        Map<String,List<String>> ans=new HashMap<String,List<String>>();
        String temp="";
        for(Map<String,Object> x: res){
            temp=String.valueOf(x.get("class_no"))+'-'+String.valueOf(x.get("section_no"));
            System.out.println(temp);
            List<String> t2=ans.get(temp);
            if (t2==null){
                t2=new ArrayList<String>();
                t2.add(String.valueOf(x.get("dept_name")));
                ans.put(temp,t2);
            }
            else{
                t2.add(String.valueOf(x.get("dept_name")));
            }
            // if (ans.get(String.valueOf(x.get("class_no"))+'-'+String.valueOf(x.get("section_no")))!=null){
            //     ans.put(String.valueOf(x.get("class_no"))+'-'+String.valueOf(x.get("section_no")), new ArrayList<String>());
            // }
            // ans.get(String.valueOf(x.get("class_no"))+'-'+String.valueOf(x.get("section_no"))).add(String.valueOf(x.get("dept_name")));
        }
        return ans;
    }

    public List<Map<String,Object>> allcs(String session){
        return jdbc.queryForList("select * from courses where session_no="+session);

    }

    public void addcourse(String class_no,String section_no,String emp_id,String session_no,String dept_name){
        jdbc.update("insert into courses values("+class_no+","+section_no+","+emp_id+","+session_no+",'"+dept_name+"')");
    }

}
