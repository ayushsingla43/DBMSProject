package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;
import com.fasterxml.jackson.annotation.JacksonInject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class studentProfile {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/student/profile")
    public ModelAndView viewMyProfile(HttpSession session){
        ModelAndView mv = new ModelAndView("/studentProfile.html");
        mv.addObject("stu", (student)session.getAttribute("student"));
        return mv;
    }
}
