package com.AADHA.Starters.DBMSProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.alumnidao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;

@Controller
public class delete {
    
    @Autowired
    JdbcTemplate j;

    @RequestMapping(value="/staff/removeStudent/{UID}")
    public ModelAndView getMethodName(@PathVariable("UID") String UID) {
        System.out.println(UID);
        studentdao sdao = new studentdao(j);
        coursesdao cdao = new coursesdao(j);
        String session_no = cdao.currentsession();
        student stu = sdao.getStudentByUID(UID);
        sdao.deleteStudent(UID);
        alumnidao adao = new alumnidao(j);
        adao.addAlumini(stu.getSRN(), stu.getClass_no(), Integer.parseInt(session_no), stu.getName(), stu.getAdmission_date(),
        stu.getEmail(), stu.getPhone_1(), stu.getPhone_2(), stu.getPhoto(), stu.getGender(), stu.getAadhar_no());
        ModelAndView mv = new ModelAndView("studentList.html");
        return mv;
    }
}
