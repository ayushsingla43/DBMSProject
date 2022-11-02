package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.resultsdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;

@Controller
public class results {

    @Autowired
    JdbcTemplate j;
    
    @Autowired
    resultsdao resdao;


    @RequestMapping("/student/results/{UID}")
    public ModelAndView resultStudent(@PathVariable("UID") String UID){
        ModelAndView mv = new ModelAndView();
        studentdao stud = new studentdao(j);
        student stu = stud.getStudentByUID(UID);
        System.out.println(stu.toString());
        List<Integer> session_no = resdao.allSession(stu.getSRN());
        System.out.println("hello");
        mv.setViewName("studentResults.html");
        mv.addObject("stu", stu);
        mv.addObject("class_",new HashMap<String,String>(){{
            put("class_no","");
            put("section_no","");
        }});
        mv.addObject("session_nos", session_no);
        return mv;
    }

    @PostMapping("/student/results/{UID}")
    public ModelAndView resultStudent(String session_no,@PathVariable("UID") String UID){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentResults.html");
        studentdao stud = new studentdao(j);
        student stu = stud.getStudentByUID(UID);
        List<Integer> session_nos = resdao.allSession(stu.getSRN());
        System.out.println(stu.toString());
        List<Map<String,Object>> mark = resdao.getMarks(stu.getSRN(),Integer.parseInt(session_no));
        Integer Total_marks=0;
        Double counter=0.0;
        for(Map<String,Object> mp:mark){
            Total_marks+=(Integer)mp.get("marks");
            counter+=1.0;
        }
        mv.addObject("stu", stu);
        mv.addObject("class_", resdao.getClass(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("marks", resdao.getMarks(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("percentage", Total_marks/counter);
        mv.addObject("session_nos", session_nos);
        return mv;
    }
}
