package com.AADHA.Starters.DBMSProject.controller;

import com.AADHA.Starters.DBMSProject.dao.classdao;
import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.dao.studentdao;
import com.AADHA.Starters.DBMSProject.model.staff;
import com.AADHA.Starters.DBMSProject.model.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

import java.util.List;
import java.util.Random;

@Controller
public class insert {

    @Autowired
    JdbcTemplate jdbc;
    @GetMapping("/staff/add")
    public ModelAndView insertTeacher(HttpSession session){
        ModelAndView mv = new ModelAndView();
        // if((int)session.getAttribute("authority") <=2){
        //     mv.setViewName("staffList.html");
        //     return mv;
        // }
        mv.setViewName("staffInsert.html");
        return mv;
    }

    @PostMapping("/staff/add")
    public ModelAndView insertTeacher(String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo){
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
            mv.setViewName("staffInsert.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("email", stf_email);
        if(st!=null){
            mv.addObject("message", "Email Address already exists");
            mv.setViewName("staffInsert.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar);
        if(st!=null){
            mv.addObject("message", "Aadhar No should be unique");
            mv.setViewName("staffInsert.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("PAN_no", stf_pan);
        if(st!=null){
            mv.addObject("message", "PAN No should be unique");
            mv.setViewName("staffInsert.html");
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
        mv.setViewName("staffShowUID.html");
        mv.addObject("addedStaff", stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar));
        return mv;
    }

    @GetMapping("/student/add")
    public ModelAndView tryAddStudent(){
        ModelAndView mv =  new ModelAndView("studentInsert.html");
        classdao cls = new classdao(jdbc);
        List<String> class_no = cls.Classes();
        List<String> section_no = cls.Sections();
        mv.addObject("class",class_no);
        mv.addObject("section",section_no);
        return mv;
    }

    @PostMapping("/student/add")
    public ModelAndView insertStudent(String std_name, String std_gender, String std_date, Integer std_pin,String std_mother, String std_guardian, String std_class, String std_section,String std_dob, String std_email, String std_bg, String std_phone1, String std_phone2, String std_street, String std_city,String std_state, String std_aadhar, String std_photo)
    {
        studentdao stddao = new studentdao(jdbc);
        ModelAndView mv = new ModelAndView();
        Integer Std_class = Integer.valueOf(std_class);
        Integer Std_section = Integer.valueOf(std_section);
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

        student stu;
        stu = stddao.getStudentByAttribute("Aadhar_no", std_aadhar);
        if(stu!=null){
            mv.setViewName("studentInsert.html");
            mv.addObject("message", "Aadhar Number should be unique!");
            return mv;
        }
        stddao.insertStudent(std_name,std_gender,std_date,std_pin,std_mother,std_guardian,Std_class,Std_section,std_dob,std_email,std_bg,std_phone1,std_phone2,std_street,std_city,std_state,std_aadhar,std_photo,password);
        mv.setViewName("studentShowUID.html");
        mv.addObject("addedStudent", stddao.getStudentByAttribute("Aadhar_no", std_aadhar));
        return mv;
    }
}
