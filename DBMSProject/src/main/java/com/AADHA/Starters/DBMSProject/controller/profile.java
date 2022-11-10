package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.dao.workindao;
import com.AADHA.Starters.DBMSProject.model.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class profile {

    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/profile/{UID}")
    public ModelAndView profStaff(@PathVariable("UID") String str){
        ModelAndView mv = new ModelAndView("staffProfile.html");
        staffdao stfd = new staffdao(j);
        workindao wrkd = new workindao(j);
        staff stf = (staff)stfd.getStaffByUID(str);
        mv.addObject("stf",stf);
        mv.addObject("department", wrkd.alldept(stf.getEmp_id()));
        return mv;
    }

    @GetMapping("/student/profile/{UID}")
    public ModelAndView profStudent(@PathVariable("UID") String str, HttpSession session){
        if((int)session.getAttribute("authority")==1){
            if(!str.equals((String)session.getAttribute("UID"))) return new ModelAndView("error/405.html");
        }
        ModelAndView mv = new ModelAndView("studentProfile.html");
        studentdao stud = new studentdao(j);
        mv.addObject("stu", stud.getStudentByUID(str));
        return mv;
    }
}
