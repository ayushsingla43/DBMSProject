package com.AADHA.Starters.DBMSProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;

@Controller
public class class__ {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/class/{class_no}-{section_no}")
    public ModelAndView classprofile(@PathVariable("class_no") String class_no,@PathVariable("section_no") String section_no){
        ModelAndView mv=new ModelAndView("class.html");
        classdao class_=new classdao(j);
        coursesdao crs=new coursesdao(j);
        mv.addObject("class",class_.classProfile(class_no, section_no));
        mv.addObject("subjects",crs.assignquery("", class_no, section_no, "", "1000"));
        return mv;
    }
}
