package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class insert {

    @Autowired
    JdbcTemplate jdbc;
    @GetMapping("/staff/addTeacher")
    public ModelAndView addMyTeacher(HttpSession session){
        ModelAndView mv = new ModelAndView();
        if((int)session.getAttribute("authority") <=2){
            mv.setViewName("staffList.html");
            return mv;
        }
        mv.setViewName("addTeacher.html");
        return mv;
    }

    @PostMapping("/staff/addTeacher")
    public ModelAndView insertTeacher(String stf_name, String stf_gender, int stf_exp, int stf_pin, int
                                      stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String
                                      stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar,
                                      String stf_pan, String stf_photo){
//        System.out.println(stf_aadhar);
//        System.out.println(stf_bg);
//        System.out.println(stf_photo);
//        System.out.println(stf_city);
//        System.out.println(stf_dob);
//        System.out.println(stf_email);
//        System.out.println(stf_gender);
//        System.out.println(stf_name);
//        System.out.println(stf_pan);
//        System.out.println(stf_phone1);
//        System.out.println(stf_phone2);
//        System.out.println(stf_state);
//        System.out.println(stf_street);
//        System.out.println(stf_pin);
//        System.out.println(stf_exp);
//        System.out.println(stf_salary);
//        int stf_exp = Integer.parseInt(Stf_exp), stf_pin = Integer.parseInt(Stf_pin), stf_salary = Integer.parseInt(Stf_salary);
        ModelAndView mv = new ModelAndView();
        staffdao stfdao = new staffdao(jdbc);
        staff st;
        st = stfdao.getStaffByAttribute("phone_1", stf_phone1);
        if(st!=null){
            mv.addObject("message", "Phone1 already exists");
            mv.setViewName("addTeacher.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("email", stf_email);
        if(st!=null){
            mv.addObject("message", "Email Address already exists");
            mv.setViewName("addTeacher.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar);
        if(st!=null){
            mv.addObject("message", "Aadhar No should be unique");
            mv.setViewName("addTeacher.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("PAN_no", stf_pan);
        if(st!=null){
            mv.addObject("message", "PAN No should be unique");
            mv.setViewName("addTeacher.html");
            return mv;
        }
        Random random = new Random();
        int leftLimit = '0', rightLimit = '9';
        int length = 8;
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomLimitedint = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedint);
        }
        String password = buffer.toString();
        stfdao.insertTeacher(stf_name, stf_gender, stf_exp, stf_pin,stf_salary,stf_dob, stf_email, stf_bg,
                stf_phone1, stf_phone2, stf_street, stf_city, stf_state,stf_aadhar, stf_pan, stf_photo, password
        );
        mv.setViewName("addedStaff");
        mv.addObject("addedStaff", stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar));
        return mv;

    }
}
