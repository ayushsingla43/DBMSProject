package com.AADHA.Starters.DBMSProject.dao;

import java.util.List;
import java.util.Map;

import com.AADHA.Starters.DBMSProject.model.student;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class studentdao {
    @Autowired
    private final JdbcTemplate jdbc;

    public studentdao(JdbcTemplate j){
        this.jdbc=j;
    }

    public List<Map<String,Object>> viewquery(String SRN,String name,String class_,String section,String Emp_id,String limit){
        String query ="select SRN,name,phone_1,class_no,section_no from student";
        Boolean wr=false;
        if (SRN!=""){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" SRN="+SRN;
        }
        if (name!=""){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" name='"+name+"'";
        }
        if (class_!=""){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" class_no="+class_;
        }
        if (section!=""){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" section_no="+section;
        }
        if(Emp_id!=""){

            coursesdao courses=new coursesdao(jdbc);
            List<Map<String,Object>> allclasses=courses.classesbyemp(Emp_id);
            Integer len=allclasses.size();
            if (len!=0){
                if (wr==false){
                    query+=" where";
                    wr=true;
                }
                else{
                    query+=" and";
                }
                query+=" (";
                for(Map<String,Object> cls : allclasses){
                    query+=" (class_no="+String.valueOf(cls.get("class_no"))+" and section_no="+String.valueOf(cls.get("section_no"))+")";
                    if (len!=1){
                        query+=" or";
                        len--;
                    }
                }
                query+=")";
            }
        }

        query+=" limit "+limit;
        List<Map<String,Object>> filteredstudents=jdbc.queryForList(query);

        return filteredstudents;
    }

    public student info(int SRN){
        String query = "select * from student where SRN=?";
        return jdbc.queryForObject(query, MappingRow.rmstudent,SRN);
    }

    public student findByUID(String uid){
        String sql = "select * from  student where UID = ?";
        try{
            System.out.println("Working");
            student st =  jdbc.queryForObject(sql, MappingRow.rmstudent, uid);
            System.out.println(st.toString());
            return st;
        }
        catch(EmptyResultDataAccessException e)
        {
            System.out.println("Error");
            return null;
        }
    }
}
