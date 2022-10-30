package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
// import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Map;

@Controller
public class studentList {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/student/list")
    public ModelAndView student_list(){
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();

        ModelAndView mv = new ModelAndView();
        mv.setViewName("studentList.html");
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        return mv;
    }

    @PostMapping("/student/list")
    // @ResponseBody
    public ModelAndView filter(String St_SRN,String St_name,String class_,String section,String Emp_id,String limit){
        // System.out.println(St_SRN);
        // System.out.println(St_name);
        // System.out.println(class_);
        // System.out.println(section);
        // System.out.println(Emp_id);
        // System.out.println(limit);
        studentdao stud=new studentdao(j);
        List<Map<String,Object>> res = stud.viewquery(St_SRN,St_name,class_,section,Emp_id,limit);
        for (Map<String,Object> x : res){
            System.out.println(String.valueOf(x.get("SRN"))+' '+String.valueOf(x.get("name"))+' '+String.valueOf(x.get("phone_1"))+' '+String.valueOf(x.get("class_no"))+' '+String.valueOf(x.get("section_no")));
        }
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();

        ModelAndView mv = new ModelAndView("studentList");
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("students", res);
        return mv;
    }

    @GetMapping("/addstudent")
    @ResponseBody
    public String addstudent(){
        System.out.println("to be done");
        // ModelAndView mv=new ModelAndView();
        // mv.setViewName("logIn.html");
        return "to be done";
    }

}
