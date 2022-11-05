package com.AADHA.Starters.DBMSProject.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.coursesdao;
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
    public ModelAndView resultStaff(HttpServletRequest request) {
        Map<String,?> flashMap = RequestContextUtils.getInputFlashMap(request);
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
        if(flashMap!=null){
            mv.addObject("message", flashMap.get("message"));
        }
        return mv;
    }
    
    @PostMapping("/staff/results")
    public ModelAndView resultstaff(String emp_id,String SRN,String class_,String section,String course, String session,String limit){
        ModelAndView mv = new ModelAndView("staffResults.html");
        classdao cls = new classdao(j);
        resultsdao res = new resultsdao(j);
        List<Integer> session_nos = res.totalSession();
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        List<String> courses = res.getAllCourses();
        List<Map<String,Object>> studnets=res.getPrevResult(emp_id, SRN, class_, section, course, session, limit);
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        mv.addObject("course", courses);
        mv.addObject("session_nos", session_nos);
        mv.addObject("css", course);
        mv.addObject("students", studnets);
        return mv;
    }

    @PostMapping("/staff/result/edit")
    public ModelAndView editResult(String  stud_SRN, String stud_name, String stud_class, String stud_section, String stud_subject, String stud_marks){
        ModelAndView mv = new ModelAndView("resultEdit.html");
        mv.addObject("stud_SRN", stud_SRN);
        mv.addObject("stud_name", stud_name);
        mv.addObject("stud_class", stud_class);
        mv.addObject("stud_section", stud_section);
        mv.addObject("stud_subject", stud_subject);
        mv.addObject("stud_marks", stud_marks);
        return mv;
    }

    @PostMapping("/staff/result/edit2")
    public ModelAndView editResult2(String  stud_SRN, String stud_name, String stud_class, String stud_section, String stud_subject, String stud_marks, RedirectAttributes redirectAttributes){
        System.out.println("Inside editResult2");
        resultsdao rdao = new resultsdao(j);
        coursesdao cdao = new coursesdao(j);
        String session_no = cdao.currentsession();
        rdao.updateResult(stud_SRN, session_no, stud_marks, stud_subject);
        System.out.println("Inside editResult2");
        redirectAttributes.addFlashAttribute("message", "Result updated successfully!");
        ModelAndView mv = new ModelAndView("redirect:/staff/results");
        return mv;
    }
}
