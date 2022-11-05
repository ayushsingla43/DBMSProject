package com.AADHA.Starters.DBMSProject.dao;

import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

@Repository
public class staffdao {
    @Autowired
    private final JdbcTemplate jdbc;

    public staffdao(JdbcTemplate j){
        this.jdbc=j;
    }

    public List<Map<String,Object>> listquery(String emp_id, String name, String session, String dept, String class_, String section, String limit){
        String query="select distinct emp_id,name,phone_1,email,salary,UID from staff";
        Boolean wr=false;

        if (!dept.equals("")) query+=" natural join works_in";
        if(!session.equals("") || !class_.equals("") || !section.equals("")) query+=" natural join courses";

        if (!emp_id.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" emp_id="+emp_id;
        }
        if (!name.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" name='"+name+"'";
        }
        if (!session.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" session_no="+session;
        }
        if (!dept.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" dept_name='"+dept+"'";
        }
        if (!class_.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" class_no="+class_;
        }
        if (!section.equals("")){
            if(wr==false){
                query+=" where";
                wr=true;
            }
            else query+=" and";
            query+=" section_no="+section;
        }
        query+=" limit "+limit;
        List<Map<String,Object>> res=jdbc.queryForList(query);
        return res;
    }

    public staff info(int emp_id){
        String query = "select * from staff where emp_id=?";
        return jdbc.queryForObject(query, MappingRow.rmstaff,emp_id);
    }

    public staff getStaffByUID(String uid){
        String sql = "select * from `staff` where UID=?";
        try{
            return jdbc.queryForObject(sql, MappingRow.rmstaff, uid);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public staff getStaffByAttribute(String attribute, String value){
        String sql = "select * from staff where " + attribute+" = ?";
        try{
            return jdbc.queryForObject(sql, MappingRow.rmstaff, value);
        }
        catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    public void insertTeacher(String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo, String stf_pss){
        String sql = "insert into staff(name,gender,phone_1,phone_2, DOB, email, blood_grp,exp_years, salary, PIN,street,city, Aadhar_no, PAN_no,state, photo, password) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        jdbc.update(sql ,stf_name, stf_gender, stf_phone1, stf_phone2, stf_dob, stf_email, stf_bg, stf_exp, stf_salary, stf_pin, stf_street, stf_city, stf_aadhar, stf_pan, stf_state, stf_photo, stf_pss);
        staff stf = getStaffByAttribute("Aadhar_no", stf_aadhar);
        sql = "update staff set UID=? where Aadhar_no=?";
        jdbc.update(sql, "stf"+Integer.toString(stf.getEmp_id()), stf_aadhar);
    }

    public void editTeacher(String UID,String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo){
        String sql = "update staff set name=?,gender=?,phone_1=?,phone_2=?, DOB=?, email=?, blood_grp=?,exp_years=?, salary=?, PIN=?,street=?,city=?, Aadhar_no=?, PAN_no=?,state=?, photo=? where UID=?";
        jdbc.update(sql ,stf_name, stf_gender, stf_phone1, stf_phone2, stf_dob, stf_email, stf_bg, stf_exp, stf_salary, stf_pin, stf_street, stf_city, stf_aadhar, stf_pan, stf_state, stf_photo,UID);
    }
}
