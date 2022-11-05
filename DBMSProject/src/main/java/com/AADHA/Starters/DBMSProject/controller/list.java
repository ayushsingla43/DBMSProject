package com.AADHA.Starters.DBMSProject.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.coursesdao;
import com.AADHA.Starters.DBMSProject.dao.deptdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;

@Controller
public class list {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/list")
    public ModelAndView teachersearch(){
        ModelAndView mv=new ModelAndView("staffList.html");
        classdao cls = new classdao(j);
        coursesdao crs =new coursesdao(j);
        deptdao dep = new deptdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String>ssns=crs.sessions();
        List<String>depts=dep.getAllDepts();
        mv.addObject("ssns",ssns); 
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("depts",depts);
        return mv;
    }

    @PostMapping("/staff/list")
    public ModelAndView teacherlist(String emp_id,String name,String session,String dept,String class_,String section,String limit){
        ModelAndView mv=new ModelAndView("staffList.html");
        classdao cls = new classdao(j);
        coursesdao crs =new coursesdao(j);
        deptdao dep = new deptdao(j);
        staffdao staf=new staffdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String>ssns=crs.sessions();
        List<String>depts=dep.getAllDepts();
        List<Map<String,Object>> staffs= staf.listquery(emp_id,name,session,dept,class_,section,limit);
        mv.addObject("ssns",ssns); 
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("depts",depts);
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
        ModelAndView mv = new ModelAndView("studentList.html");
        studentdao stud=new studentdao(j);
        classdao cls = new classdao(j);
        List<Map<String,Object>> stu = stud.viewquery(St_SRN,St_name,class_,section,Emp_id,limit);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("students", stu);
        return mv;
    }

    @GetMapping("/staff/assign")
    public ModelAndView assign(){
        System.out.println("hello");
        ModelAndView mv=new ModelAndView("staffAssign.html");
        classdao cls = new classdao(j);
        coursesdao crs=new coursesdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> department=crs.Courses();
        mv.addObject("section", section_no);
        mv.addObject("class", class_no);
        mv.addObject("department",department);
        return mv;
    }

    @PostMapping("/staff/assign")
    public ModelAndView assign(String emp_id,String class_,String section,String dept,String limit){
        ModelAndView mv=new ModelAndView("staffAssign.html");
        classdao cls = new classdao(j);
        coursesdao crs=new coursesdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> department=crs.Courses();
        List<Map<String,Object>> assign=crs.assignquery(emp_id, class_, section, dept, limit);
        mv.addObject("assign", assign);
        mv.addObject("section", section_no);
        mv.addObject("class", class_no);
        mv.addObject("department",department);
        return mv;
    }
}
