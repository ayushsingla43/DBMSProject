package com.AADHA.Starters.DBMSProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;

@Controller
public class list {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/list")
    public ModelAndView teachersearch(){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("staffList.html");
        coursesdao cls =new coursesdao(j);
        List<String>ssns=cls.sessions();
        mv.addObject("ssns",ssns); 
        return mv;
    }

    @PostMapping("/staff/list")
    public ModelAndView teacherlist(String emp_id,String name,String session,String dept,String class_,String section,String limit){
        ModelAndView mv=new ModelAndView();
        mv.setViewName("staffList.html");
        coursesdao cls =new coursesdao(j);
        List<String>ssns=cls.sessions();
        mv.addObject("ssns",ssns);
        staffdao staf=new staffdao(j);
        List<Map<String,Object>> staffs= staf.listquery(emp_id,name,session,dept,class_,section,limit);
        for(Map<String,Object> stf: staffs){
            System.out.print(stf.get("emp_id"));
            System.out.print(stf.get("name"));
            System.out.print(stf.get("phone_1"));
            System.out.print(stf.get("salary"));
            System.out.print(stf.get("email"));
            System.out.println();
        }
        mv.addObject("staffs",staffs);
        return mv;
    }

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
    public ModelAndView filter(String St_SRN,String St_name,String class_,String section,String Emp_id,String limit){
        // System.out.println(St_SRN);System.out.println(St_name);System.out.println(class_);System.out.println(section);System.out.println(Emp_id);System.out.println(limit);
        studentdao stud=new studentdao(j);
        List<Map<String,Object>> res = stud.viewquery(St_SRN,St_name,class_,section,Emp_id,limit);
        // for (Map<String,Object> x : res){
        //     System.out.println(String.valueOf(x.get("SRN"))+' '+String.valueOf(x.get("name"))+' '+String.valueOf(x.get("phone_1"))+' '+String.valueOf(x.get("class_no"))+' '+String.valueOf(x.get("section_no")));
        // }
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();

        ModelAndView mv = new ModelAndView("studentList.html");
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
