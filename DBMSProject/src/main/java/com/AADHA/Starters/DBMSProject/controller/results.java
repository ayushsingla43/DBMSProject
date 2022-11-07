package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.resultsdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;


@Controller
public class results {

    @Autowired
    JdbcTemplate j;


    @GetMapping("/student/results/{UID}")
    public ModelAndView resultStudent(@PathVariable("UID") String UID){
        ModelAndView mv = new ModelAndView("studentResults.html");
        studentdao stud = new studentdao(j);
        resultsdao res = new resultsdao(j);
        student stu = stud.getStudentByUID(UID);
        List<Integer> session_no = res.allSession(stu.getSRN());
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
        ModelAndView mv = new ModelAndView("studentResults.html");
        studentdao stud = new studentdao(j);
        resultsdao res = new resultsdao(j);
        student stu = stud.getStudentByUID(UID);
        List<Integer> session_nos = res.allSession(stu.getSRN());
        List<Map<String,Object>> mark = res.getMarks(stu.getSRN(),Integer.parseInt(session_no));
        Integer Total_marks=0;
        Double counter=0.0;
        for(Map<String,Object> mp:mark){
            Total_marks+=(Integer)mp.get("marks");
            counter+=1.0;
        }
        mv.addObject("stu", stu);
        mv.addObject("class_", res.getClass(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("marks", res.getMarks(stu.getSRN(), Integer.parseInt(session_no)));
        mv.addObject("percentage", Total_marks/counter);
        mv.addObject("session_nos", session_nos);
        return mv;
    }

    @GetMapping("/staff/results")
    public ModelAndView resultStaff() {
        ModelAndView mv = new ModelAndView("staffResults.html");
        classdao cls = new classdao(j);
        resultsdao res = new resultsdao(j);
        List<Integer> session_nos = res.totalSession();
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> courses = res.getAllCourses();
        String course="Marks";
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("course", courses);
        mv.addObject("session_nos", session_nos);
        mv.addObject("css", course);
        return mv;
    }
    
    @PostMapping("/staff/results")
    public ModelAndView resultstaff(String emp_id,String SRN,String class_,String section,String course, String session,String limit,String message){
        ModelAndView mv = new ModelAndView("staffResults.html");
        classdao cls = new classdao(j);
        resultsdao res = new resultsdao(j);
        List<Integer> session_nos = res.totalSession();
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> courses = res.getAllCourses();
        List<Map<String,Object>> students=res.getPrevResult(emp_id, SRN, class_, section, course, session, limit);
        Map<String,Object> filter = new HashMap<String,Object>() {{
            put("emp_id",emp_id);
            put("SRN",SRN);
            put("class_",class_);
            put("section",section);
            put("course",course);
            put("session",session);
            put("limit",limit);
        }};
        mv.addObject("filter", filter);
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("course", courses);
        mv.addObject("session_nos", session_nos);
        mv.addObject("css", course);
        mv.addObject("students", students);
        mv.addObject("message", message);
        return mv;
    }

    @PostMapping("/staff/result/edit")
    public ModelAndView editResult(String stud_SRN,String stud_course,String stud_session,String stud_marks,String fil_emp_id,String fil_SRN,String fil_class,String fil_section,String fil_course,String fil_session,String fil_limit){
        ModelAndView mv = new ModelAndView("dummy/staffResultsRedirect.html");
        resultsdao rdao = new resultsdao(j);
        Map<String,Object> filter = new HashMap<String,Object>() {{
            put("emp_id",fil_emp_id);
            put("SRN",fil_SRN);
            put("class_",fil_class);
            put("section",fil_section);
            put("course",fil_course);
            put("session",fil_session);
            put("limit",fil_limit);
        }};
        rdao.updateResult(stud_SRN, stud_session, stud_marks, stud_course);
        mv.addObject("filter", filter);
        mv.addObject("message", "student "+stud_SRN+" marks for course "+stud_course+" in session "+stud_session+" updated successfully!");
        return mv;
    }
}
