package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
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
import com.AADHA.Starters.DBMSProject.dao.workindao;

import javax.servlet.http.HttpSession;

@Controller
public class list {
    @Autowired
    JdbcTemplate j;

    @GetMapping("/staff/list")
    public ModelAndView teachersearch(HttpSession session){
        if((int)(session.getAttribute("authority"))<3) return new ModelAndView("error/405.html");
        ModelAndView mv=new ModelAndView("staffList.html");
        deptdao dep = new deptdao(j);
        List<String>depts=dep.getAllDepts();
        mv.addObject("depts",depts);
        return mv;
    }

    @PostMapping("/staff/list")
    public ModelAndView teacherlist(String emp_id,String name,String dept,String curr,String limit,String message){
        ModelAndView mv=new ModelAndView("staffList.html");
        deptdao dep = new deptdao(j);
        staffdao staf=new staffdao(j);
        List<String>depts=dep.getAllDepts();
        List<Map<String,Object>> staffs= staf.listquery(emp_id,name,dept,curr,limit);
        mv.addObject("depts",depts);
        mv.addObject("staffs",staffs);
        mv.addObject("message", message);
        return mv;
    }

    @GetMapping("/student/list")
    public ModelAndView student_list(){
        ModelAndView mv = new ModelAndView("studentList.html");
        classdao cls = new classdao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
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
    public ModelAndView assign(String emp_id,String class_,String section,String dept,String limit,String message){
        ModelAndView mv=new ModelAndView("staffAssign.html");
        classdao cls = new classdao(j);
        coursesdao crs=new coursesdao(j);
        workindao wrkd = new workindao(j);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> department=crs.Courses();
        List<Map<String,Object>> assign=crs.assignquery(emp_id, class_, section, dept, limit);
        for(Map<String,Object> mp:assign){
            List<String> emps=wrkd.allemp2(String.valueOf(mp.get("dept_name")));
            mp.put("emps", emps);
        }
        Map<String,Object> filter = new HashMap<String,Object>() {{
            put("emp_id",emp_id);
            put("class_",class_);
            put("section",section);
            put("dept",dept);
            put("limit",limit);
        }};
        mv.addObject("filter", filter);
        mv.addObject("assign", assign);
        mv.addObject("section", section_no);
        mv.addObject("class", class_no);
        mv.addObject("department",department);
        mv.addObject("message", message);
        return mv;
    }
}
