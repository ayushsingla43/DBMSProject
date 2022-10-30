package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class studentProfile {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/student/profile")
    public ModelAndView profile(){
        studentdao stud = new studentdao(j);
        student stu = stud.info(1010);
        System.out.println(stu.getName());
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentProfile.html");
        mv.addObject("stu",stu);
        return mv;
    }
}
