package com.AADHA.Starters.DBMSProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.AADHA.Starters.DBMSProject.dao.staffdao;
import com.AADHA.Starters.DBMSProject.model.staff;

@Controller
public class edit {

    @Autowired
    JdbcTemplate j;
    
    @GetMapping("/staff/edit/{UID}")
    public ModelAndView staffEdit(@PathVariable("UID") String UID){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("staffEdit.html");
        staffdao stfd = new staffdao(j);
        staff stf = stfd.getStaffByUID(UID);
        System.out.println("chghj");
        System.out.println(stf.getUID());
        mv.addObject("stf", stf);
        return mv;
    }

    @PostMapping("/staff/edit/{UID}")
    public ModelAndView staffEdit(@PathVariable("UID") String UID, String stf_name, String stf_gender, int stf_exp, int stf_pin, int stf_salary, String stf_dob, String stf_email, String stf_bg, String stf_phone1, String stf_phone2, String stf_street, String stf_city, String stf_state, String stf_aadhar, String stf_pan, String stf_photo)
    {
        System.out.println("hhu");
        ModelAndView mv = new ModelAndView();
        staffdao stfdao = new staffdao(j);
        staff st = stfdao.getStaffByAttribute("phone_1", stf_phone1);
        System.out.println(st.getUID());
        if(st!=null && !st.getUID().equals(UID)){
            mv.addObject("message", "Phone1 already exists");
            mv.setViewName("staffEdit.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("email", stf_email);
        if(st!=null && !st.getUID().equals(UID)){
            mv.addObject("message", "Email Address already exists");
            mv.setViewName("staffEdit.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("Aadhar_no", stf_aadhar);
        if(st!=null && !st.getUID().equals(UID)){
            mv.addObject("message", "Aadhar No should be unique");
            mv.setViewName("staffEdit.html");
            return mv;
        }
        st = stfdao.getStaffByAttribute("PAN_no", stf_pan);
        if(st!=null && !st.getUID().equals(UID)){
            mv.addObject("message", "PAN No should be unique");
            mv.setViewName("staffEdit.html");
            return mv;
        }
        
        stfdao.editTeacher(UID,stf_name, stf_gender, stf_exp, stf_pin,stf_salary,stf_dob, stf_email, stf_bg, stf_phone1, stf_phone2, stf_street, stf_city, stf_state,stf_aadhar, stf_pan, stf_photo);
        System.out.println("vn");
        st=stfdao.getStaffByUID(UID);
        mv.addObject("stf", st);
        mv.setViewName("staffEdit.html");
        mv.addObject("message", "edited successfully");
        return mv;
    }
}
