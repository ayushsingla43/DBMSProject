package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;

@Controller
public class login {

    @Autowired
    private studentdao sdao;

    @Autowired
    private staffdao Sdao;

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/student/login")
    public String viewStudentLogin(){
        return "studentLogin.html";
    }

    @GetMapping("/student/home")
    public ModelAndView viewStudentHome(HttpServletRequest request, HttpSession session){
        Principal principal = request.getUserPrincipal();
        student stu = sdao.findByUID(principal.getName());
        session.setAttribute("student", stu);
        ModelAndView mv = new ModelAndView("/home.html");
        mv.addObject("User", stu);
        mv.addObject("authority", 1);
        return mv;
    }

    @GetMapping("/staff/login")
    public String viewStaffLogin(){
        return "staffLogin.html";
    }

    @GetMapping("/staff/home")
    public ModelAndView viewStaffHome(HttpServletRequest request, HttpSession session){
        Principal principal = request.getUserPrincipal();
        staff stf = Sdao.getStaffByUID(principal.getName());
        session.setAttribute("staff", stf);
        ModelAndView mv = new ModelAndView("/home.html");
        mv.addObject("User", stf);
        mv.addObject("authority", 2);
        return mv;
    }

}
