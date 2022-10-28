package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.class_imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class student_list {
    @Autowired
    JdbcTemplate j;
    @GetMapping("/studentList")
    public ModelAndView student_list(){
        class_imp cls = new class_imp(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("student_list.html");
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        return mv;
    }

    @GetMapping("/filter")
//    @ResponseBody
    public String filter(String SRN,String name,String class_,String section,String Emp_id,String limit){
          //abhijeet kar ise
        System.out.println("bdouedio");
        return "done";
    }
}
