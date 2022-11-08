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
        String query ="select SRN,name,phone_1,class_no,section_no,UID from student";
        Boolean wr=false;
        if (!SRN.equals("")){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" SRN="+SRN;
        }
        if (!name.equals("")){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" name='"+name+"'";
        }
        if (!class_.equals("")){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" class_no="+class_;
        }
        if (!section.equals("")){
            if (wr==false){
                query+=" where";
                wr=true;
            }
            else{
                query+=" and";
            }
            query+=" section_no="+section;
        }
        if(!Emp_id.equals("")){

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

    public student getStudentByUID(String uid){
        String sql = "select * from  student where UID = ?";
        try{
            return jdbc.queryForObject(sql, MappingRow.rmstudent, uid);
        }
        catch(EmptyResultDataAccessException e)
        {
            return null;
        }
    }
    public student getStudentByAttribute(String attribute, String value){
        String sql = "select * from student where " + attribute+" = ?";
        try{
            return jdbc.queryForObject(sql, MappingRow.rmstudent, value);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public void insertStudent(String std_name, String std_gender, String std_date, Integer std_pin, String std_mother, String std_guardian, Integer std_class, Integer std_section, String std_dob, String std_email, String std_bg, String std_phone1, String std_phone2, String std_street, String std_city, String std_state, String std_aadhar, String std_photo, String password){
        String sql = "insert into student(name, gender, phone_1, phone_2, DOB, admission_date, email, blood_grp, guardian, mother, PIN, street, city,Aadhar_no, state, photo, password, class_no, " + "section_no) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(sql, std_name, std_gender , std_phone1, std_phone2, std_dob, std_date, std_email, std_bg, std_guardian, std_mother, std_pin, std_street, std_city, std_aadhar, std_state, std_photo,password, std_class, std_section);
        student std = getStudentByAttribute("Aadhar_no", std_aadhar);
        sql = "update student set UID=? where Aadhar_no=?";
        jdbc.update(sql, "std"+Integer.toString(std.getSRN()), std_aadhar);
    }

    public void editStudent(String UID,String std_name, String std_gender, String std_date, Integer std_pin, String std_mother, String std_guardian, Integer std_class, Integer std_section, String std_dob, String std_email, String std_bg, String std_phone1, String std_phone2, String std_street, String std_city, String std_state, String std_aadhar, String std_photo){
        String sql = "update student set name=?,gender=?, phone_1=?, phone_2=?, DOB=?, admission_date=?, email=?, blood_grp=?, guardian=?, mother=?, PIN=?, street=?, city=?,Aadhar_no=?, state=?, photo=?, class_no=?, " + "section_no=? where UID=?";
        jdbc.update(sql, std_name, std_gender , std_phone1, std_phone2, std_dob, std_date, std_email, std_bg, std_guardian, std_mother, std_pin, std_street, std_city, std_aadhar, std_state, std_photo, std_class, std_section,UID);
    }

    public void deleteStudent(String UID){
        String sql = "delete from student where UID=?";
        jdbc.update(sql, UID);
    }

    public List<Map<String,Object>> allstud(String session){
        return jdbc.queryForList("select * from student");
    }

    public void update_class(String SRN,String class_no){
        jdbc.update("update student set class_no=? where SRN=?",class_no,SRN);
    }
    
}
