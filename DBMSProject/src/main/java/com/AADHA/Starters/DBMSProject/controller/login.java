package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;
import com.AADHA.Starters.DBMSProject.util.MappingRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class login {
    @Autowired
    JdbcTemplate j;
    @GetMapping("/")
    public String logIn(){
        return "logIn";
    }

    @GetMapping("/attemptLogIn")
    public ModelAndView tryLogIn(String UID, String password, String type) {
        ModelAndView mv = new ModelAndView();
        if (type == "stu") {
            String query = "select * from student where UID=? and password=?";
            student stu = j.queryForObject(query, MappingRow.rmstudent, UID, password);
            if (stu == null) mv.setViewName("logIn");
            else {
                mv.setViewName("home");
                mv.addObject("type",4);
                mv.addObject("SRN", stu.getSRN());
            }
        }
        else {
            String query = "select * from staff where UID=? and password=?";
            staff stf = j.queryForObject(query, MappingRow.rmstaff, UID, password);
            if (stf == null) mv.setViewName("logIn");
            else {
                mv.setViewName("home");
                query = "select dept_name from works_in where emp_id=? and leaving_date=null";
//                List<String> depts = j.query(query,,stf.getEmp_id());
                mv.addObject("type",4);
                mv.addObject("SRN", stf.getEmp_id());
            }
        }
        return mv;
    }
}
