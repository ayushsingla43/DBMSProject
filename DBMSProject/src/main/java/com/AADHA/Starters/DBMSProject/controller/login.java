package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Controller
public class login {

    @Autowired
    private studentdao sdao;

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/student/login")
    public String viewStudentLogin(){
        return "studentLogin.html";
    }

    @GetMapping("/student/home")
    public ModelAndView viewStudentHome(HttpServletRequest request){
        Principal principal = request.getUserPrincipal();
        student stu = sdao.findByUID(principal.getName());
        System.out.println(stu.toString());
        ModelAndView mv = new ModelAndView("/home.html");
        mv.addObject("User", stu);
        mv.addObject("type", "student");
        return mv;
    }

    @GetMapping("/staff/login")
    public String viewStaffLogin(){
        return "staffLogin.html";
    }

    @GetMapping("/staff/home")
    public String viewStaffHome(){
        return "home.html";
    }
}
