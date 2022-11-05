package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.deptdao;
import com.AADHA.Starters.DBMSProject.dao.getAuthority;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.List;

@Controller
public class login {
    @Autowired
    JdbcTemplate j;

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
        ModelAndView mv = new ModelAndView("/home.html");
        Principal principal = request.getUserPrincipal();
        studentdao sdao = new studentdao(j);
        student stu = sdao.getStudentByUID(principal.getName());
        deptdao dptd = new deptdao(j);
        List<String> depts = dptd.getAllDepts();
        session.setAttribute("depts", depts);
        session.setAttribute("student", stu);
        session.setAttribute("UID", stu.getUID());
        session.setAttribute("authority",1);
        mv.addObject("User", stu);
        return mv;
    }

    @GetMapping("/staff/login")
    public String viewStaffLogin(){
        return "staffLogin.html";
    }

    @GetMapping("/staff/home")
    public ModelAndView viewStaffHome(HttpServletRequest request, HttpSession session){
        ModelAndView mv = new ModelAndView("/home.html");
        staffdao Sdao = new staffdao(j);
        getAuthority gA = new getAuthority(j);
        deptdao dptd = new deptdao(j);
        Principal principal = request.getUserPrincipal();
        staff stf = Sdao.getStaffByUID(principal.getName());
        List<String> depts = dptd.getAllDepts();
        session.setAttribute("depts", depts);
        session.setAttribute("staff", stf);
        session.setAttribute("UID", stf.getUID());
        session.setAttribute("authority", gA.Autority(stf));
        mv.addObject("User", stf);
        return mv;
    }

}
