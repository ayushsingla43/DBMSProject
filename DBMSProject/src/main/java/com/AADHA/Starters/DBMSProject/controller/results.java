package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.digester.SetNextRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.resultsdao;
import com.AADHA.Starters.DBMSProject.model.student;

@Controller
public class results {
    private static final int HashMap = 0;

    @Autowired
    JdbcTemplate j;
    
    @Autowired
    resultsdao resdao;


    @RequestMapping("/student/results")
    public ModelAndView resultStudent(HttpSession session){
        ModelAndView mv = new ModelAndView();
        student stu = (student)session.getAttribute("student");
        System.out.println(stu.toString());
        List<Integer> session_no = resdao.allSession(stu.getSRN());
        mv.setViewName("studentResults.html");
        mv.addObject("stu", stu);
        mv.addObject("class_",new HashMap<String,String>(){{
            put("class_no","");
            put("section_no","");
        }});
        mv.addObject("session_nos", session_no);
        return mv;
    }

    @PostMapping("/student/results")
    public ModelAndView resultStudent(HttpSession session,String session_no){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentResults.html");
        student stu = (student)session.getAttribute("student");
        System.out.println(stu.toString());
        List<Map<String,Object>> mark = resdao.getMarks(stu.getSRN(),Integer.parseInt(session_no));
        Integer Total_marks=0;
        Double counter=0.0;
        // System.out.println(stu.toString());
        // for(Map<String,Object> mp:mark) for(Object tmk: mp.values()){
        //     System.out.println(tmk);
        // }
        for(Map<String,Object> mp:mark){
            System.out.println(stu.toString());
            Total_marks+=(Integer)mp.get("marks");
            counter+=1.0;
        }
        System.out.println(stu.toString());
        mv.addObject("stu", stu);
        mv.addObject("class_", resdao.getClass(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("marks", resdao.getMarks(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("percentage", Total_marks/counter);
        return mv;
    }
}
