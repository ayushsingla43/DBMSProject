package com.AADHA.Starters.DBMSProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class login {

    @RequestMapping("/")
    public String index(){
        return "index.html";
    }

    @GetMapping("/student/login")
    public String viewStudentLogin(){
        return "studentLogin.html";
    }

    @GetMapping("/student/home")
    public String viewStudentHome(){
        return "home.html";
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
